package dding.reservation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class StudioPricingPolicyResponse {
    private String id;
    private int dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private int price;
    private boolean isHoliday;
    private String pricePoliciesDescription;
}
