package dding.roomaddress.controller;

import dding.roomaddress.dto.AddressLatitudeAndLongitudeDto;
import dding.roomaddress.dto.AddressRequestDto;
import dding.roomaddress.dto.AddressResponseDto;
import dding.roomaddress.entity.Address;
import dding.roomaddress.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/band-room/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/{storeId}")
    public ResponseEntity<Address> createAddress(@PathVariable Long storeId, @RequestBody AddressRequestDto dto) {
        Address address = addressService.saveAddress(storeId, dto);
        return ResponseEntity.ok(address);
    }

    // 00구 00동으로 search
    @GetMapping("/search")
    public ResponseEntity<List<Address>> getAddressesByDistrictAndTown(@RequestParam String district, @RequestParam String town) {
        return ResponseEntity.ok(addressService.findByDistrictAndTown(district, town));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> readAddressById(@PathVariable(name = "id") Long bandId)
    {
        return ResponseEntity.ok(addressService.readAddress(bandId));
    }

    @GetMapping("/LatitudeAndLongitude/{id}")
    public ResponseEntity<AddressLatitudeAndLongitudeDto> getAddressLatitudeAndLongitudeDto(@PathVariable(name = "id") Long bandId)
    {
        return ResponseEntity.ok(addressService.readAddressLatitudeAndLongitude(bandId));
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable(name = "id") Long bandId)
    {
        addressService.deleteAddress(bandId);
    }
}


