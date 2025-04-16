package dding.BandRoom.dto.response.BandRoom;

import dding.BandRoom.entity.BandRoom;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BandRoomMarkerResponse{


    private String id;
    private String name;
    private String roadAddress;
    private String thumbnailUrl;
    private String shortDescription;

    private Double latitude;
    private Double longitude;
    private String color;



}
