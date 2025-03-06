package dding.roomaddress.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@AllArgsConstructor
public class AddressResponseDto {
    private String addressName; // 전체 주소
    private String roadAddressName; // 도로명 주소
    private String city;  // 시/도
    private String district;  // 구/군
    private String town;  // 동/읍/면
    private String zipCode;  // 우편번호
}
