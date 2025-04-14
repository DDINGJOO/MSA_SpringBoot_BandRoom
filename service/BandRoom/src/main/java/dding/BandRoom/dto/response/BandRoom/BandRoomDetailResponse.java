package dding.BandRoom.dto.response.BandRoom;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BandRoomDetailResponse {
    private String id;
    private String name;
    private String shortDescription;
    private String detailedDescription;

    private String phone;
    private Boolean parkingAvailable;
    private String addressDescription;
    private List<String> keywords;
    private List<String> homepageUrls;
    private String notes;
    private String thumbnailUrls;
    private String roadAddress;
    private String detailAddress;
    private Double latitude;
    private Double longitude;
}
