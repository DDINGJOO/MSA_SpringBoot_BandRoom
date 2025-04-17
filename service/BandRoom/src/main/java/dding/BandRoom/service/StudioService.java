package dding.BandRoom.service;

import dding.BandRoom.dto.request.studio.StudioRequest;
import dding.BandRoom.dto.response.studio.StudioResponse;
import dding.BandRoom.entity.BandRoom;
import dding.BandRoom.entity.Studio;
import dding.BandRoom.repository.BandRoom.BandRoomRepository;
import dding.BandRoom.repository.Studio.StudioRepository;
import jakarta.persistence.Temporal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;
    private final BandRoomRepository bandRoomRepository;

    public String createStudio(String bandRoomId, StudioRequest request) {
        BandRoom bandRoom = bandRoomRepository.findById(bandRoomId)
                .orElseThrow(() -> new RuntimeException("BandRoom not found"));

        Studio studio = Studio.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .defaultPrice(request.getDefaultPrice())
                .pricePoliciesDescription(request.getPricePoliciesDescription())
                .imageUrls(request.getImageUrls())
                .bandRoom(bandRoom)
                .isAvailable(false)
                .build();

        studioRepository.save(studio);
        return studio.getId();
    }

    @Transactional
    public EntityResponse<?> setAvailable(String studioId)
    {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new RuntimeException("Studio not found"));
        if(studio.isAvailable())
        {
            studio.setAvailable(false);
        }
        else
        {
            studio.setAvailable(true);
        }
        studioRepository.save(studio);


    }

    public StudioResponse getStudio(String studioId) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new RuntimeException("Studio not found"));

        return StudioResponse.builder()
                .id(studio.getId())
                .name(studio.getName())
                .description(studio.getDescription())
                .pricePoliciesDescription(studio.getPricePoliciesDescription())
                .defaultPrice(studio.getDefaultPrice())
                .imageUrls(studio.getImageUrls())
                .build();
    }

    public void deleteStudio(String studioId) {
        studioRepository.deleteById(studioId);
    }
}
