package dding.BandRoom.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BandRoomSearchRequest {
    private String name;
    private String keyword;
    private Boolean isOpen;
    private String roadAddress;
}
