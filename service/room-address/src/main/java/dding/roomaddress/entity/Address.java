package dding.roomaddress.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Table(name = "room_address")
@NoArgsConstructor (access =  AccessLevel.PROTECTED)
@ToString
@Entity
public class Address {

    @Id
    private Long id; // Store ID와 동일한 값

    private String addressName; // 전체 주소
    private String roadAddressName; // 도로명 주소
    private String city;  // 시/도
    private String district;  // 구/군
    private String town;  // 동/읍/면
    private String zipCode;  // 우편번호
    private Double latitude;  // 위도
    private Double longitude; // 경도
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;


    public static Address create(Long bandRoomId, String addressName, String roadAddressName, String city, String district, String town, String zipCode,
                                 Double latitude, Double longitude)
    {
        Address address = new Address();
        address.addressName = addressName;
        address.city = city;
        address.id = bandRoomId;
        address.district = district;
        address.roadAddressName = roadAddressName;
        address.town = town;
        address.zipCode =zipCode;
        address.latitude = latitude;
        address.longitude = longitude;
        address.createAt = LocalDateTime.now();

        return address;
    }
}
