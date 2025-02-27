package dding.bandroom.dto;


import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public class BandRoomCreateRequest {
    private String bandName;
    private String bandNumber;
    private String roomEmail;
    private String roomEmailDomain;
    private long adminId;


    public static BandRoomCreateRequest of(String bandName, String bandNumber,
                                           String roomEmail ,String roomEmailDomain,  long adminId)
    {
        BandRoomCreateRequest req = new BandRoomCreateRequest();
        req.bandName = bandName;
        req.adminId = adminId;
        req.bandNumber = bandNumber;
        req.roomEmail = roomEmail;
        req.roomEmailDomain = roomEmailDomain;
        return req;

    }
}
