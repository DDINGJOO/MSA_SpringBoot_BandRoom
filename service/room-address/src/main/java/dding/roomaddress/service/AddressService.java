package dding.roomaddress.service;


import dding.roomaddress.dto.AddressRequestDto;
import dding.roomaddress.dto.KakaoAddressResponse;
import dding.roomaddress.entity.Address;
import dding.roomaddress.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final KakaoApiService kakaoApiService;

    public Address saveAddress(Long bandRoomId, AddressRequestDto dto) {
        Address address;

        if (dto.getLatitude() == null || dto.getLongitude() == null) {
            // 카카오 API 호출하여 위도/경도 보완
            KakaoAddressResponse.Document addressInfo = kakaoApiService.searchAddress(dto.getAddressName());

            return addressRepository.save(Address.create(
                    bandRoomId, // Store ID를 Address의 PK로 사용
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
                    bandRoomId, // Store ID를 Address의 PK로 사용
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
}
