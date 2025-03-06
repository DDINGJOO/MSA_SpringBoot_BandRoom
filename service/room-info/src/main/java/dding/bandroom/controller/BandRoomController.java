package dding.bandroom.controller;

import dding.bandroom.dto.BandRoomRequestDto;
import dding.bandroom.dto.BandRoomResponse;
import dding.bandroom.entity.BandRoom;
import dding.bandroom.service.BandRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/band-rooms")
@RequiredArgsConstructor
public class BandRoomController {

    private final BandRoomService bandRoomService;

    @PostMapping
    public ResponseEntity<BandRoom> createBandRoom(@RequestBody BandRoomRequestDto dto) {
        BandRoom bandRoom = bandRoomService.createBandRoom(dto);
        return ResponseEntity.ok(bandRoom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BandRoomResponse> readBandRoom(@PathVariable(name = "id") long id){
        BandRoomResponse response = bandRoomService.readBandRoom(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void deleteBandRoom(@PathVariable (name = "id") long id)
    {
        bandRoomService.deleteBandRoom(id);
    }





}
