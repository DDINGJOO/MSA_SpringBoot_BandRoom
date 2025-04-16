package dding.BandRoom.service;


import dding.BandRoom.dto.request.BandRoom.BandRoomRequest;

import dding.BandRoom.dto.request.BandRoomSearchRequest;
import dding.BandRoom.dto.response.BandRoom.BandRoomMarkerResponse;
import dding.BandRoom.dto.response.BandRoom.BandRoomResponse;
import dding.BandRoom.dto.response.BandRoom.BandRoomSimpleResponse;
import dding.BandRoom.entity.BandRoom;
import dding.BandRoom.repository.BandRoom.BandRoomRepository;
import dding.BandRoom.repository.BandRoom.BandRoomSpecification;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BandRoomService {

    private final BandRoomRepository bandRoomRepository;

    @Transactional
    public String create(BandRoomRequest request) {
        BandRoom bandRoom = buildBandRoom(request);
        bandRoomRepository.save(bandRoom);
        return bandRoom.getId();
    }
    @Transactional(readOnly = true)
    public BandRoomResponse get(String id) {
        BandRoom bandRoom = bandRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BandRoom not found"));
        return toResponse(bandRoom);
    }

    @Transactional(readOnly = true)
    public List<BandRoomMarkerResponse> getMarkers() {
        List<BandRoom> bandRooms= bandRoomRepository.findAll();
        List<BandRoomMarkerResponse> responses = new ArrayList<>();
        for(BandRoom bandroom : bandRooms)
        {
            responses.add(
                    BandRoomMarkerResponse.builder()
                            .latitude(bandroom.getLatitude())
                            .longitude(bandroom.getLongitude())
                            .id(bandroom.getId())
                            .roadAddress(bandroom.getRoadAddress())
                            .shortDescription(bandroom.getShortDescription())
                            .name(bandroom.getName())
                            .thumbnailUrl(bandroom.getThumbnailUrl())
                            .color("BLUE")
                            .build()
            );
        }

        return responses;



    }


//    public boolean checkMy(String userId,String bandRoomId)
//    {
//        BandRoom bandRoom = bandRoomRepository.findById(bandRoomId).orElseThrow();
//        return(Objects.equals(userId, bandRoom.getUserName()));
//    }


    @Transactional(readOnly = true)
    public Page<BandRoomSimpleResponse> searchBandRooms(BandRoomSearchRequest req, Pageable pageable) {
        Specification<BandRoom> spec = BandRoomSpecification.searchWithFilters(
                req.getName(),
                req.getKeyword(),
                req.getRoadAddress()
        );

        return bandRoomRepository.findAll(spec, pageable)
                .map(BandRoomSimpleResponse::fromEntity);
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
                .keywords(new ArrayList<>(bandRoom.getKeywords()))
                .homepageUrls(new ArrayList<>(bandRoom.getHomepageUrls()))
                .notes(bandRoom.getNotes())
                .thumbnailUrl(bandRoom.getThumbnailUrl())
                .roadAddress(bandRoom.getRoadAddress())
                .detailAddress(bandRoom.getDetailAddress())
                .latitude(bandRoom.getLatitude())
                .longitude(bandRoom.getLongitude())
                .build();
    }

    private BandRoom buildBandRoom(BandRoomRequest request) {
        return BandRoom.builder()
                .id(request.getId() != null ? request.getId() : UUID.randomUUID().toString())
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
                .parkingDescription(request.getParkingDescription())
                .build();
    }

}
