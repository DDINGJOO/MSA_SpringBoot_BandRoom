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

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BandRoomScheduleRuleService {

    private final BandRoomRepository bandRoomRepository;
    private final BandRoomScheduleRuleRepository ruleRepository;

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
}
