package dding.bandroom.dto;


import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public class BandRoomRequestDto {
    private Long bandRoomId;
    private String bandRoomName;
    private String bandNumber;
    private String roomEmail;
    private String roomEmailDomain;
    private long adminId;


    public static BandRoomRequestDto of(Long bandRoomId, String bandRoomName, String bandNumber,
                                        String roomEmail , String roomEmailDomain, long adminId)
    {
        BandRoomRequestDto req = new BandRoomRequestDto();
        req.bandRoomId = bandRoomId;
        req.bandRoomName = bandRoomName;
        req.adminId = adminId;
        req.bandNumber = bandNumber;
        req.roomEmail = roomEmail;
        req.roomEmailDomain = roomEmailDomain;
        return req;

    }
}
