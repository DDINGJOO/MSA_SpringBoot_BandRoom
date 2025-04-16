package dding.BandRoom.dto.response.BandRoom;


import dding.BandRoom.entity.BandRoom;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Data
@Builder
public class BandRoomSimpleResponse {
    private String id;
    private String name;
    private String roadAddress;
    private String thumbnailUrl;
    private String shortDescription;
    private List<String> keywords;


    public static BandRoomSimpleResponse fromEntity(BandRoom room) {
        List<String> keywords = room.getKeywords() != null ? new ArrayList<>(room.getKeywords()) : Collections.emptyList();
        return BandRoomSimpleResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .keywords(room.getKeywords())
                .roadAddress(room.getRoadAddress())
                .thumbnailUrl(room.getThumbnailUrl())
                .build();
    }
}
