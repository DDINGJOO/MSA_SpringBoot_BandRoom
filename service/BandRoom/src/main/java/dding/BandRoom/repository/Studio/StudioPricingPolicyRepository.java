package dding.BandRoom.repository.Studio;


import dding.BandRoom.entity.StudioPricingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioPricingPolicyRepository  extends JpaRepository<StudioPricingPolicy,String> {
    List<StudioPricingPolicy> findByStudioId(String studioId);
}
