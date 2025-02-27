package dding.bandroom.controller;

import dding.bandroom.dto.BandRoomRequestDto;
import dding.bandroom.entity.BandRoom;
import dding.bandroom.service.BandRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bind/bandrooms")
@RequiredArgsConstructor
public class BandRoomController {

    private final BandRoomService bandRoomService;

    @PostMapping
    public ResponseEntity<BandRoom> createBandRoom(@RequestBody BandRoomRequestDto dto) {
        BandRoom bandRoom = bandRoomService.createBandRoom(dto);
        return ResponseEntity.ok(bandRoom);
    }
}
