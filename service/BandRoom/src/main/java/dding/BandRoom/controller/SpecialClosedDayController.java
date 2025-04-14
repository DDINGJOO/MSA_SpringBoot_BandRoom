package dding.BandRoom.controller;

import dding.BandRoom.dto.request.SpecialClosedDay.SpecialClosedDayRequest;
import dding.BandRoom.dto.response.SpecialClosedDay.SpecialClosedDayResponse;
import dding.BandRoom.service.SpecialClosedDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/band-rooms/{bandRoomId}/closed-days")
@RequiredArgsConstructor
public class SpecialClosedDayController {

    private final SpecialClosedDayService service;

    @PostMapping
    public ResponseEntity<String> create(@PathVariable String bandRoomId, @RequestBody SpecialClosedDayRequest request) {
        return ResponseEntity.ok(service.create(bandRoomId, request));
    }

    @GetMapping
    public ResponseEntity<List<SpecialClosedDayResponse>> list(@PathVariable String bandRoomId) {
        return ResponseEntity.ok(service.getByBandRoom(bandRoomId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
