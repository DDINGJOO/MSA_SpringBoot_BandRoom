package dding.BandRoom.controller;

import dding.BandRoom.dto.request.studio.StudioRequest;
import dding.BandRoom.dto.response.studio.StudioResponse;
import dding.BandRoom.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/band-rooms/{bandRoomId}/studios")
@RequiredArgsConstructor
public class StudioController {

    private final StudioService studioService;

    @PostMapping
    public ResponseEntity<String> create(@PathVariable String bandRoomId,
                                         @RequestBody StudioRequest request) {
        return ResponseEntity.ok(studioService.createStudio(bandRoomId, request));
    }

    @GetMapping("/{studioId}")
    public ResponseEntity<StudioResponse> get(@PathVariable String studioId) {
        return ResponseEntity.ok(studioService.getStudio(studioId));
    }

    @DeleteMapping("/{studioId}")
    public ResponseEntity<Void> delete(@PathVariable String studioId) {
        studioService.deleteStudio(studioId);
        return ResponseEntity.noContent().build();
    }
}
