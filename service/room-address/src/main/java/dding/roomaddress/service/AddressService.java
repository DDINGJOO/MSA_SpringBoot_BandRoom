package dding.roomaddress.service;


import dding.roomaddress.dto.AddressLatitudeAndLongitudeDto;
import dding.roomaddress.dto.AddressRequestDto;
import dding.roomaddress.dto.AddressResponseDto;
import dding.roomaddress.dto.KakaoAddressResponse;
import dding.roomaddress.entity.Address;
import dding.roomaddress.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final KakaoApiService kakaoApiService;

    public Address saveAddress(Long bandRoomId, AddressRequestDto dto) {

        if (dto.getLatitude() == null || dto.getLongitude() == null) {
            // 카카오 API 호출하여 위도/경도 보완
            KakaoAddressResponse.Document addressInfo = kakaoApiService.searchAddress(dto.getAddressName());

            return addressRepository.save(Address.create(
                    bandRoomId, // Room ID를 Address의 PK로 사용
                    addressInfo.getAddress_name(),
                    addressInfo.getRoad_address_name(),
                    addressInfo.getAddress().getRegion_1depth_name(),
                    addressInfo.getAddress().getRegion_2depth_name(),
                    addressInfo.getAddress().getRegion_3depth_name(),
                    dto.getZipCode(),
                    Double.parseDouble(addressInfo.getY()), // 경도
                    Double.parseDouble(addressInfo.getX())  // 위도
            ));
        } else {
            return addressRepository.save(Address.create(
                    bandRoomId, // Room ID를 Address의 PK로 사용
                    dto.getAddressName(),
                    dto.getRoadAddressName(),
                    dto.getCity(),
                    dto.getDistrict(),
                    dto.getTown(),
                    dto.getZipCode(),
                    dto.getLatitude(),
                    dto.getLongitude()
            ));
        }

    }

    @Transactional
    public void deleteAddress(Long bandRoomId)
    {
        addressRepository.deleteById(bandRoomId);
    }
    public AddressResponseDto readAddress(Long bandRoomId)
    {
        Address address = addressRepository.findById(bandRoomId).orElseThrow();

        return new AddressResponseDto(address.getAddressName(), address.getRoadAddressName(), address.getCity(), address.getDistrict(),
                address.getTown(), address.getZipCode());
    }

    public AddressLatitudeAndLongitudeDto readAddressLatitudeAndLongitude(Long bandRoomId)
    {
        Address address = addressRepository.findById(bandRoomId).orElseThrow();
        return new AddressLatitudeAndLongitudeDto(address.getLatitude(), address.getLongitude());
    }


    public List<Address> findByDistrictAndTown(String district, String town) {
        return addressRepository.findByDistrictAndTown(district, town);
    }
/*
public class AddressResponseDto {
    private String addressName; // 전체 주소
    private String roadAddressName; // 도로명 주소
    private String city;  // 시/도
    private String district;  // 구/군
    private String town;  // 동/읍/면
    private String zipCode;  // 우편번호
    private Double latitude;  // 위도
    private Double longitude; // 경도
}

 */

}
