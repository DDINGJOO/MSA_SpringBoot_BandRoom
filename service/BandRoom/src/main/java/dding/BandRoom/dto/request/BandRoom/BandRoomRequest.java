package dding.BandRoom.dto.request.BandRoom;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BandRoomRequest {

    private String name;
    private String shortDescription;
    private String detailedDescription;

    private String phone;
    private Boolean parkingAvailable;
    private String displayAddress;

    private List<String> keywords;
    private List<String> homepageUrls;

    private String notes;

    private String thumbnailUrl;

    private String roadAddress;
    private String detailAddress;

    private Double latitude;
    private Double longitude;
}
