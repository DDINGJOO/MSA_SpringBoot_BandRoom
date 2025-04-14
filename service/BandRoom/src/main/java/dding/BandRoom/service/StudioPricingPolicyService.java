package dding.BandRoom.service;

import dding.BandRoom.dto.request.studio.StudioPricingPolicyRequest;
import dding.BandRoom.dto.response.studio.StudioPricingPolicyResponse;
import dding.BandRoom.entity.Studio;
import dding.BandRoom.entity.StudioPricingPolicy;
import dding.BandRoom.repository.Studio.StudioPricingPolicyRepository;
import dding.BandRoom.repository.Studio.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudioPricingPolicyService {

    private final StudioRepository studioRepository;
    private final StudioPricingPolicyRepository pricingPolicyRepository;

    public String create(String studioId, StudioPricingPolicyRequest request) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new RuntimeException("Studio not found"));

        StudioPricingPolicy policy = StudioPricingPolicy.builder()
                .id(UUID.randomUUID().toString())
                .studio(studio)
                .dayOfWeek(request.getDayOfWeek())
                .startTime(LocalTime.parse(request.getStartTime()))
                .endTime(LocalTime.parse(request.getEndTime()))
                .price(request.getPrice())
                .isHoliday(request.isHoliday())
                .build();

        pricingPolicyRepository.save(policy);
        return policy.getId();
    }

    public List<StudioPricingPolicyResponse> getAll(String studioId) {
        return pricingPolicyRepository.findByStudioId(studioId).stream()
                .map(p -> StudioPricingPolicyResponse.builder()
                        .id(p.getId())
                        .dayOfWeek(p.getDayOfWeek())
                        .startTime(p.getStartTime().toString())
                        .endTime(p.getEndTime().toString())
                        .price(p.getPrice())
                        .isHoliday(p.isHoliday())
                        .build())
                .toList();
    }

    public void delete(String id) {
        pricingPolicyRepository.deleteById(id);
    }
}

