package dding.roomaddress.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class AddressLatitudeAndLongitudeDto {
    private Double latitude;  // 위도
    private Double longitude; // 경도
}
