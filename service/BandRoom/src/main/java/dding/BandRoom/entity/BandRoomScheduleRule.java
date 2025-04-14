package dding.BandRoom.entity;

import dding.BandRoom.config.RecurrencePattern;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BandRoomScheduleRule {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_room_id")
    private BandRoom bandRoom;

    private int dayOfWeek; // 월=1 ~ 일=7

    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private RecurrencePattern recurrencePattern;

    private Boolean isClosed;
    private Boolean isOddWeek;
}
