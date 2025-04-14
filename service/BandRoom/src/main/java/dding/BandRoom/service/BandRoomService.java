package dding.BandRoom.service;


import dding.BandRoom.dto.request.BandRoom.BandRoomRequest;

import dding.BandRoom.dto.response.BandRoom.BandRoomResponse;
import dding.BandRoom.entity.BandRoom;
import dding.BandRoom.repository.BandRoom.BandRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BandRoomService {

    private final BandRoomRepository bandRoomRepository;

    public String create(BandRoomRequest request) {
        BandRoom bandRoom = BandRoom.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .shortDescription(request.getShortDescription())
                .detailedDescription(request.getDetailedDescription())
                .phone(request.getPhone())
                .parkingAvailable(request.getParkingAvailable())
                .displayAddress(request.getDisplayAddress())
                .keywords(request.getKeywords())
                .homepageUrls(request.getHomepageUrls())
                .notes(request.getNotes())
                .thumbnailUrl(request.getThumbnailUrl())
                .roadAddress(request.getRoadAddress())
                .detailAddress(request.getDetailAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

        bandRoomRepository.save(bandRoom);
        return bandRoom.getId();
    }

    public BandRoomResponse get(String id) {
        BandRoom bandRoom = bandRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BandRoom not found"));

        return toResponse(bandRoom);
    }

    public void update(String id, BandRoomRequest request) {
        BandRoom bandRoom = bandRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BandRoom not found"));

        bandRoom.setName(request.getName());
        bandRoom.setShortDescription(request.getShortDescription());
        bandRoom.setDetailedDescription(request.getDetailedDescription());
        bandRoom.setPhone(request.getPhone());
        bandRoom.setParkingAvailable(request.getParkingAvailable());
        bandRoom.setDisplayAddress(request.getDisplayAddress());
        bandRoom.setKeywords(request.getKeywords());
        bandRoom.setHomepageUrls(request.getHomepageUrls());
        bandRoom.setNotes(request.getNotes());
        bandRoom.setThumbnailUrl(request.getThumbnailUrl());
        bandRoom.setRoadAddress(request.getRoadAddress());
        bandRoom.setDetailAddress(request.getDetailAddress());
        bandRoom.setLatitude(request.getLatitude());
        bandRoom.setLongitude(request.getLongitude());

        bandRoomRepository.save(bandRoom);
    }

    public void delete(String id) {
        bandRoomRepository.deleteById(id);
    }

    private BandRoomResponse toResponse(BandRoom bandRoom) {
        return BandRoomResponse.builder()
                .id(bandRoom.getId())
                .name(bandRoom.getName())
                .shortDescription(bandRoom.getShortDescription())
                .detailedDescription(bandRoom.getDetailedDescription())
                .phone(bandRoom.getPhone())
                .parkingAvailable(bandRoom.getParkingAvailable())
                .displayAddress(bandRoom.getDisplayAddress())
                .keywords(bandRoom.getKeywords())
                .homepageUrls(bandRoom.getHomepageUrls())
                .notes(bandRoom.getNotes())
                .thumbnailUrl(bandRoom.getThumbnailUrl())
                .roadAddress(bandRoom.getRoadAddress())
                .detailAddress(bandRoom.getDetailAddress())
                .latitude(bandRoom.getLatitude())
                .longitude(bandRoom.getLongitude())
                .build();
    }
}
