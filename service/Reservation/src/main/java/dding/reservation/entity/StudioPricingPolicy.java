package dding.reservation.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "studio_pricing_policy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudioPricingPolicy {

    @Id
    private String id;

    private String studioId;
    private int dayOfWeek; // 1=월 ~ 7=일
    private LocalTime startTime;
    private LocalTime endTime;

    private int price;
    private String pricePoliciesDescription;
}
