package dding.BandRoom.service;

import dding.BandRoom.config.RecurrencePattern;
import dding.BandRoom.entity.BandRoom;
import dding.BandRoom.repository.BandRoom.BandRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BandRoomAvailabilityService {

    private final BandRoomRepository bandRoomRepository;

    public boolean isAvailable(String bandRoomId, LocalDate date) {
        BandRoom bandRoom = bandRoomRepository.findById(bandRoomId)
                .orElseThrow(() -> new RuntimeException("BandRoom not found"));

        // 1. 특별 휴무일 확인
        boolean isClosedSpecial = bandRoom.getSpecialClosedDays().stream()
                .anyMatch(closed -> closed.getDate().equals(date));
        if (isClosedSpecial) return false;

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayValue = dayOfWeek.getValue(); // MON=1 ~ SUN=7
        int weekOfYear = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        boolean isOddWeek = weekOfYear % 2 == 1;

        // 2. 정기 스케줄 중에서 해당 날짜와 매칭되는 운영 시간 있는지 확인
        return bandRoom.getScheduleRules().stream()
                .anyMatch(rule ->
                        rule.getDayOfWeek() == dayValue &&
                                Boolean.FALSE.equals(rule.getIsClosed()) &&
                                (
                                        rule.getRecurrencePattern() == RecurrencePattern.WEEKLY ||
                                                (rule.getRecurrencePattern() == RecurrencePattern.BIWEEKLY &&
                                                        rule.getIsOddWeek() != null &&
                                                        rule.getIsOddWeek().equals(isOddWeek)) ||
                                                rule.getRecurrencePattern() == RecurrencePattern.MONTHLY
                                )
                );
    }

    public boolean isAvailable(BandRoom bandRoom, LocalDate date) {
        // 1. 특별 휴무일 검사
        boolean isClosedSpecial = bandRoom.getSpecialClosedDays().stream()
                .anyMatch(closed -> closed.getDate().equals(date));
        if (isClosedSpecial) return false;

        // 2. 정기 운영 스케줄 검사
        int dayValue = date.getDayOfWeek().getValue(); // 월=1 ~ 일=7
        int weekOfYear = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        boolean isOddWeek = (weekOfYear % 2 == 1);

        return bandRoom.getScheduleRules().stream()
                .anyMatch(rule ->
                        rule.getDayOfWeek() == dayValue &&
                                Boolean.FALSE.equals(rule.getIsClosed()) &&
                                (
                                        rule.getRecurrencePattern() == RecurrencePattern.WEEKLY ||
                                                (rule.getRecurrencePattern() == RecurrencePattern.BIWEEKLY &&
                                                        rule.getIsOddWeek() != null &&
                                                        rule.getIsOddWeek().equals(isOddWeek)) ||
                                                rule.getRecurrencePattern() == RecurrencePattern.MONTHLY
                                )
                );
    }

    public List<BandRoom> findAvailableRooms(LocalDate date) {
        return bandRoomRepository.findAll().stream()
                .filter(room -> isAvailable(room, date))
                .toList();
    }
}
