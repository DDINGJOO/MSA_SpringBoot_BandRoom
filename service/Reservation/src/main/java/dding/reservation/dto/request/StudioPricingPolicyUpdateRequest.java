package dding.reservation.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter

public class StudioPricingPolicyUpdateRequest {
    private int dayOfWeek;
    private String startTime; // HH:mm
    private String endTime;
    private int price;
    private boolean isHoliday;
    private String pricePoliciesDescription;
    private String studioId;
}
