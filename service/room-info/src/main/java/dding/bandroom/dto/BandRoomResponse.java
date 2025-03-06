package dding.bandroom.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BandRoomResponse {
    private String bandRoomName;
    private String bandRoomNumber;
    private long adminId;
    private String bandRoomEmail;
    private String bandRoomEmailDomain;

}
