package dding.BandRoom.service;

import dding.BandRoom.config.RecurrencePattern;
import dding.BandRoom.dto.request.Schedule.BandRoomScheduleRuleRequest;
import dding.BandRoom.dto.response.Schedule.BandRoomScheduleRuleResponse;
import dding.BandRoom.entity.BandRoom;
import dding.BandRoom.entity.BandRoomScheduleRule;
import dding.BandRoom.repository.BandRoom.BandRoomRepository;
import dding.BandRoom.repository.BandRoom.BandRoomScheduleRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BandRoomScheduleRuleService {

    private final BandRoomRepository bandRoomRepository;
    private final BandRoomScheduleRuleRepository ruleRepository;
    private final BandRoomScheduleRuleRepository bandRoomScheduleRuleRepository;

    public String create(String bandRoomId, BandRoomScheduleRuleRequest request) {
        BandRoom bandRoom = bandRoomRepository.findById(bandRoomId)
                .orElseThrow(() -> new RuntimeException("BandRoom not found"));

        BandRoomScheduleRule rule = BandRoomScheduleRule.builder()
                .id(UUID.randomUUID().toString())
                .bandRoom(bandRoom)
                .dayOfWeek(request.getDayOfWeek())
                .startTime(LocalTime.parse(request.getStartTime()))
                .endTime(LocalTime.parse(request.getEndTime()))
                .recurrencePattern(RecurrencePattern.valueOf(request.getRecurrencePattern()))
                .isClosed(request.getIsClosed())
                .isOddWeek(request.getIsOddWeek())
                .build();

        ruleRepository.save(rule);
        return rule.getId();
    }

    public String saveAll(String bandRoomId, List<BandRoomScheduleRuleRequest> reqs)
    {
        BandRoom bandRoom = bandRoomRepository.findById(bandRoomId)
                .orElseThrow(() -> new RuntimeException("BandRoom not found"));
        for(BandRoomScheduleRuleRequest request : reqs)
        {
            if(Objects.equals(request.getEndTime(), "24:00"))
            {
                request.setEndTime("23:59");
            }
            if(LocalTime.parse(request.getStartTime()).isAfter(LocalTime.parse(request.getEndTime())))
            {
                createRule(request, bandRoom, request.getDayOfWeek(), request.getStartTime(), "23:59");
                createRule(request, bandRoom, (request.getDayOfWeek() % 7) + 1, "00:00", request.getEndTime());
            }

            BandRoomScheduleRule rule =  BandRoomScheduleRule.builder()
                    .id(UUID.randomUUID().toString())
                    .bandRoom(bandRoom)
                    .dayOfWeek(request.getDayOfWeek())
                    .startTime(LocalTime.parse(request.getStartTime()))
                    .endTime(LocalTime.parse(request.getEndTime()))
                    .recurrencePattern(RecurrencePattern.valueOf(request.getRecurrencePattern()))
                    .isClosed(request.getIsClosed())
                    .isOddWeek(request.getIsOddWeek())
                    .build();
            ruleRepository.save(rule);
        }
        return bandRoomId;
    }
    public List<BandRoomScheduleRuleResponse> getByBandRoom(String bandRoomId) {
        return ruleRepository.findByBandRoomId(bandRoomId).stream()
                .map(rule -> BandRoomScheduleRuleResponse.builder()
                        .id(rule.getId())
                        .dayOfWeek(rule.getDayOfWeek())
                        .startTime(rule.getStartTime().toString())
                        .endTime(rule.getEndTime().toString())
                        .recurrencePattern(rule.getRecurrencePattern().name())
                        .isClosed(rule.getIsClosed())
                        .isOddWeek(rule.getIsOddWeek())
                        .build())
                .toList();
    }

    public void delete(String ruleId) {
        ruleRepository.deleteById(ruleId);
    }

    public void deleteAll(String bandRoomId) {
        ruleRepository.deleteAllByBandRoomId(bandRoomId);
    }

    private void createRule(BandRoomScheduleRuleRequest req, BandRoom bandRoom,
                                            int day, String start, String end) {
        BandRoomScheduleRule rule = BandRoomScheduleRule.builder()
                .id(UUID.randomUUID().toString())
                .bandRoom(bandRoom)
                .dayOfWeek(day)
                .startTime(LocalTime.parse(start))
                .endTime(LocalTime.parse(end))
                .recurrencePattern(RecurrencePattern.valueOf(req.getRecurrencePattern()))
                .isClosed(req.getIsClosed())
                .isOddWeek(req.getIsOddWeek())
                .build();
        ruleRepository.save(rule);
    }

    public boolean isBandRoomOpen(String BandRoomId, LocalDate currentDate, LocalTime currentTime)
    {
        List<BandRoomScheduleRule> rules = bandRoomScheduleRuleRepository.findByBandRoomId(BandRoomId).stream().toList();
        return isBandRoomOpen(currentDate,currentTime, rules);
    }


    /**
     * 현재 날짜와 시각, 그리고 스케줄 규칙 리스트를 받아
     * 현재 해당되는 규칙들 중 하나라도 isClosed가 true이면 false를,
     * 그렇지 않고 적용되는 규칙이 있으면 true를 반환합니다.
     *
     * @param currentDate   현재 날짜
     * @param currentTime   현재 시각
     * @param scheduleRules 스케줄 규칙 목록
     * @return 영업 중이면 true, 아니면 false
     */
    private  boolean isBandRoomOpen(LocalDate currentDate, LocalTime currentTime, List<BandRoomScheduleRule> scheduleRules) {
        // 현재 요일 (1:월요일 ~ 7:일요일)
        int currentDayOfWeek = currentDate.getDayOfWeek().getValue();
        // 현재가 해당 월의 몇 번째 주인지 계산 (예: 1, 2, 3, ...)
        int weekOfMonth = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth());
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        boolean applicableRuleFound = false;

        for (BandRoomScheduleRule rule : scheduleRules) {
            // 1. 요일이 일치하지 않으면 건너뜁니다.
            if (rule.getDayOfWeek() != currentDayOfWeek) {
                continue;
            }

            // 2. 반복 주기 체크 (BIWEEKLY인 경우 홀/짝 주차 비교)
            if (rule.getRecurrencePattern() == RecurrencePattern.BIWEEKLY) {
                boolean ruleIsOddWeek = rule.isOddWeek();
                boolean currentWeekIsOdd = (weekOfMonth % 2 == 1);  // 홀수 주이면 true
                if (ruleIsOddWeek != currentWeekIsOdd) {
                    continue;
                }
            }
            // (WEEKLY인 경우에는 별도의 주차 체크 없이 그대로 적용,
            //  MONTHLY 등 다른 패턴에 대해서는 별도의 로직을 추가할 수 있음)

            // 3. 영업시간 체크: startTime, endTime을 LocalTime으로 파싱 후 현재 시각과 비교

            LocalTime start = rule.getStartTime();
            LocalTime end = rule.getEndTime();
            // 현재 시각이 영업 시작 전이거나 영업 종료 후이면 적용 대상 아님
            if (currentTime.isBefore(start) || currentTime.isAfter(end)) {
                continue;
            }

            // 해당 규칙은 현재 날짜 및 시간에 적용됨
            applicableRuleFound = true;

            // 4. 만약 적용 규칙 중 isClosed가 true이면 즉시 false 반환 (영업 중이 아님)
            if (rule.isClosed()) {
                return false;
            }
        }

        // 적용 가능한 규칙이 있다면(그리고 모두 open 상태이면) true,
        // 아무 규칙도 적용되지 않으면(영업시간 외이므로) false를 반환
        return applicableRuleFound;
    }
}
