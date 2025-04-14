package dding.BandRoom.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    private int dayOfWeek; // 1=월 ~ 7=일

    private LocalTime startTime;
    private LocalTime endTime;

    private int price;
    private boolean isHoliday; // 공휴일용 요금 정책 여부
    private String pricePoliciesDescription;
}

