package dding.reservation.controller;


import dding.reservation.dto.request.StudioPricingPolicyRequest;
import dding.reservation.dto.response.StudioPricingPolicyResponse;
import dding.reservation.service.StudioPricingPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studios/pricing")
@RequiredArgsConstructor
public class StudioPricingPolicyController {

    private final StudioPricingPolicyService pricingPolicyService;


    @PostMapping("/{studioId}")
    public ResponseEntity<String> create(@PathVariable String studioId,
                                         @RequestBody List<StudioPricingPolicyRequest> request) {
        return ResponseEntity.ok(pricingPolicyService.saveAll(studioId, request));
    }

    @PostMapping("/{studioId}/update")
    public ResponseEntity<String> update(@PathVariable String studioId,
                                         @RequestBody List<StudioPricingPolicyRequest> request) {
        return ResponseEntity.ok(pricingPolicyService.upDateAll(studioId, request));
    }

    @GetMapping("/{studioId}")
    public ResponseEntity<List<StudioPricingPolicyResponse>> get(@PathVariable String studioId) {
        return ResponseEntity.ok(pricingPolicyService.getAll(studioId));
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<Void> delete(@PathVariable String policyId) {
        pricingPolicyService.delete(policyId);
        return ResponseEntity.noContent().build();
    }



}
