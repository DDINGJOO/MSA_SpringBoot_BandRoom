package dding.BandRoom.controller;

import dding.BandRoom.dto.request.BandRoom.BandRoomRequest;
import dding.BandRoom.dto.response.BandRoom.BandRoomResponse;

import dding.BandRoom.service.BandRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/band-rooms")
@RequiredArgsConstructor
public class BandRoomController {

    private final BandRoomService bandRoomService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody BandRoomRequest request) {
        return ResponseEntity.ok(bandRoomService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BandRoomResponse> get(@PathVariable String id) {
        return ResponseEntity.ok(bandRoomService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody BandRoomRequest request) {
        bandRoomService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bandRoomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
