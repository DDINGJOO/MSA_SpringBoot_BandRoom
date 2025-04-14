package dding.BandRoom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialClosedDay {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_room_id")
    private BandRoom bandRoom;

    private LocalDate date;

    private String reason; // 예: 정기 점검
}
