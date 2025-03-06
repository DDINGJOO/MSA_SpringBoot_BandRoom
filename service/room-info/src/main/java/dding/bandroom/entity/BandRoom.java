package dding.bandroom.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bandroom")
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class BandRoom {

    @Id
    @GeneratedValue
    private long BandRoomId;
    private long adminId;

    private String BandRoomName;
    private String BandRoomNumber;

    @Nullable
    private String BandRoomEmail;
    private String BandRoomEmailDomain;


    public static BandRoom create(long BandRoomId,long adminId,
                                  String roomName, String roomNumber, String roomEmail, String BandRoomEmailDomain)
    {
        BandRoom room = new BandRoom();
        room.BandRoomId = BandRoomId;
        room.adminId = adminId;
        room.BandRoomName  = roomName;
        room.BandRoomNumber = roomNumber;
        room.BandRoomEmail = roomEmail;
        room.BandRoomEmailDomain = BandRoomEmailDomain;
        return room;
    }
}
