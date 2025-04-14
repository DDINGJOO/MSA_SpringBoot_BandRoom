package dding.BandRoom.dto.response.studio;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudioPricingPolicyResponse {
    private String id;
    private int dayOfWeek;
    private String startTime;
    private String endTime;
    private int price;
    private boolean isHoliday;
    private String pricePoliciesDescription;
}
