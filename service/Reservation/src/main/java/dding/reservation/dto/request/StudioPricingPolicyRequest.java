package dding.reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudioPricingPolicyRequest {
    private int dayOfWeek;
    private String startTime; // HH:mm
    private String endTime;
    private int price;
    private boolean isHoliday;
    private String pricePoliciesDescription;
}

