package dding.BandRoom.dto.request.studio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudioRequest {
    private String name;
    private String description;
    private Integer defaultPrice;
    private String pricePoliciesDescription;
    private List<String> imageUrls;
}
