package dding.BandRoom.controller;


import dding.BandRoom.dto.request.Schedule.BandRoomScheduleRuleRequest;
import dding.BandRoom.dto.response.Schedule.BandRoomScheduleRuleResponse;
import dding.BandRoom.service.BandRoomScheduleRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/band-rooms/{bandRoomId}/schedule-rules")
@RequiredArgsConstructor
public class BandRoomScheduleRuleController {

    private final BandRoomScheduleRuleService ruleService;

    @PostMapping
    public ResponseEntity<String> create(
            @PathVariable String bandRoomId,
            @RequestBody BandRoomScheduleRuleRequest request
    ) {
        return ResponseEntity.ok(ruleService.create(bandRoomId, request));
    }

    @GetMapping
    public ResponseEntity<List<BandRoomScheduleRuleResponse>> getRules(
            @PathVariable String bandRoomId
    ) {
        return ResponseEntity.ok(ruleService.getByBandRoom(bandRoomId));
    }

    @DeleteMapping("/{ruleId}")
    public ResponseEntity<Void> delete(@PathVariable String ruleId) {
        ruleService.delete(ruleId);
        return ResponseEntity.noContent().build();
    }
}
