package dding.reservation.service;



import dding.reservation.dto.request.StudioPricingPolicyRequest;
import dding.reservation.dto.request.StudioPricingPolicyUpdateRequest;
import dding.reservation.dto.response.StudioPricingPolicyResponse;
import dding.reservation.entity.StudioPricingPolicy;
import dding.reservation.repository.StudioPricingPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudioPricingPolicyService {

    private final StudioPricingPolicyRepository pricingPolicyRepository;

    @Transactional
    public String create(String studioId, StudioPricingPolicyRequest request) {
        StudioPricingPolicy policy = StudioPricingPolicy.builder()
                .id(UUID.randomUUID().toString())
                .studioId(studioId)
                .dayOfWeek(request.getDayOfWeek())
                .startTime(LocalTime.parse(request.getStartTime()))
                .endTime(LocalTime.parse(request.getEndTime()))
                .price(request.getPrice())
                .pricePoliciesDescription(request.getPricePoliciesDescription())
                .build();

        pricingPolicyRepository.save(policy);
        return policy.getId();
    }
    @Transactional
    public String upDate(String Id, StudioPricingPolicyUpdateRequest request) {

        StudioPricingPolicy policy = pricingPolicyRepository.findById(Id).orElseThrow();
        policy.setDayOfWeek(request.getDayOfWeek());
        policy.setEndTime(LocalTime.parse(request.getEndTime()));
        policy.setStartTime(LocalTime.parse(request.getStartTime()));
        policy.setDayOfWeek(request.getPrice());
        policy.setPricePoliciesDescription(request.getPricePoliciesDescription());
        pricingPolicyRepository.save(policy);
        return policy.getId();
    }

    public List<StudioPricingPolicyResponse> getAll(String studioId) {
        return pricingPolicyRepository.findByStudioId(studioId).stream()
                .map(p -> StudioPricingPolicyResponse.builder()
                        .id(p.getId())
                        .dayOfWeek(p.getDayOfWeek())
                        .startTime(p.getStartTime())
                        .endTime(p.getEndTime())
                        .price(p.getPrice())
                        .pricePoliciesDescription(p.getPricePoliciesDescription())
                        .build())
                .toList();
    }
    @Transactional
    public String saveAll(String studioId, List<StudioPricingPolicyRequest> reqs)
    {
        for(StudioPricingPolicyRequest request : reqs)
        {
            if(Objects.equals(request.getEndTime(), "24:00"))
            {
                request.setEndTime("23:59");
            }
            if(LocalTime.parse(request.getStartTime()).isAfter(LocalTime.parse(request.getEndTime())))
            {
                createRule(request, studioId, request.getDayOfWeek(), request.getStartTime(), "23:59");
                createRule(request, studioId, (request.getDayOfWeek() % 7) + 1, "00:00", request.getEndTime());
            }

            StudioPricingPolicy rule =  StudioPricingPolicy.builder()
                    .id(UUID.randomUUID().toString())
                    .studioId(studioId)
                    .dayOfWeek(request.getDayOfWeek())
                    .startTime(LocalTime.parse(request.getStartTime()))
                    .endTime(LocalTime.parse(request.getEndTime()))
                    .price(request.getPrice())
                    .pricePoliciesDescription(request.getPricePoliciesDescription())
                    .build();
            pricingPolicyRepository.save(rule);
        }
        return studioId;
    }


    @Transactional
    public String upDateAll(String studioId, List<StudioPricingPolicyRequest> reqs)
    {
        pricingPolicyRepository.deleteAllByStudioId(studioId);
        saveAll(studioId, reqs);
        return studioId;
    }

    @Transactional
    public void createRule(StudioPricingPolicyRequest req, String studioId,
                            int day, String start, String end) {
        StudioPricingPolicy rule =  StudioPricingPolicy.builder()
                .id(UUID.randomUUID().toString())
                .studioId(studioId)
                .dayOfWeek(day)
                .startTime(LocalTime.parse(start))
                .endTime(LocalTime.parse(end))
                .price(req.getPrice())
                .pricePoliciesDescription(req.getPricePoliciesDescription())
                .build();
        pricingPolicyRepository.save(rule);
    }

    public void delete(String id) {
        pricingPolicyRepository.deleteById(id);
    }
}

