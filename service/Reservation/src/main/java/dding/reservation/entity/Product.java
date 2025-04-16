package dding.reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    String productId;
    String bandRoomId;

    String name;
    Long price;
    String description;
    String thumbnailUrl;
}
