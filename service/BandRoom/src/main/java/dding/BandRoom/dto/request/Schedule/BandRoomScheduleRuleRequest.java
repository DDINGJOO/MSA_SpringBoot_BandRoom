package dding.BandRoom.dto.request.Schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BandRoomScheduleRuleRequest {
    private int dayOfWeek;
    private String startTime; // "HH:mm"
    private String endTime;
    private String recurrencePattern; // WEEKLY, BIWEEKLY, MONTHLY
    private Boolean isClosed;
    private Boolean isOddWeek;
}
