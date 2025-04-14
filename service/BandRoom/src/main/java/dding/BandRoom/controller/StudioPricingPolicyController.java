package dding.BandRoom.controller;

import dding.BandRoom.dto.request.studio.StudioPricingPolicyRequest;
import dding.BandRoom.dto.response.studio.StudioPricingPolicyResponse;
import dding.BandRoom.service.StudioPricingPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studios/{studioId}/pricing")
@RequiredArgsConstructor
public class StudioPricingPolicyController {

    private final StudioPricingPolicyService pricingPolicyService;

    @PostMapping
    public ResponseEntity<String> create(@PathVariable String studioId,
                                         @RequestBody StudioPricingPolicyRequest request) {
        return ResponseEntity.ok(pricingPolicyService.create(studioId, request));
    }

    @GetMapping
    public ResponseEntity<List<StudioPricingPolicyResponse>> list(@PathVariable String studioId) {
        return ResponseEntity.ok(pricingPolicyService.getAll(studioId));
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<Void> delete(@PathVariable String policyId) {
        pricingPolicyService.delete(policyId);
        return ResponseEntity.noContent().build();
    }
}
