package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@ToString
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class studio {

    @Id
    String  BandRoomID;

}
