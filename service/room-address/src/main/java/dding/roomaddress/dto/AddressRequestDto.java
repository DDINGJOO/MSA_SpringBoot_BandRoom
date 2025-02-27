package dding.roomaddress.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AddressRequestDto {
    private String addressName;
    private String roadAddressName;
    private String city;
    private String district;
    private String town;
    private String zipCode;
    private Double latitude;
    private Double longitude;
}
