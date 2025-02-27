package dding.roomaddress.controller;

import dding.roomaddress.dto.AddressRequestDto;
import dding.roomaddress.entity.Address;
import dding.roomaddress.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/{storeId}")
    public ResponseEntity<Address> createAddress(@PathVariable Long storeId, @RequestBody AddressRequestDto dto) {
        Address address = addressService.saveAddress(storeId, dto);
        return ResponseEntity.ok(address);
    }
}


