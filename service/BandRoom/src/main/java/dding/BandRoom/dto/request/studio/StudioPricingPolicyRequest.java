package dding.BandRoom.dto.request.studio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}

