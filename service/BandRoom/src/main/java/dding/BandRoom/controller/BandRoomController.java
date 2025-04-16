package dding.BandRoom.controller;

import dding.BandRoom.dto.request.BandRoom.BandRoomRequest;
import dding.BandRoom.dto.request.BandRoomSearchRequest;
import dding.BandRoom.dto.response.BandRoom.BandRoomMarkerResponse;
import dding.BandRoom.dto.response.BandRoom.BandRoomResponse;

import dding.BandRoom.dto.response.BandRoom.BandRoomSimpleResponse;
import dding.BandRoom.service.BandRoomService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/band-rooms")
@RequiredArgsConstructor
/*
{
  "id": "br-001",
  "name": "소울하모니 합주실",
  "shortDescription": "감성 넘치는 인디 밴드 공간",
  "detailedDescription": "다양한 악기와 고품질 음향 장비를 갖춘 소울하모니 합주실은 인디 밴드, 아마추어 뮤지션들을 위한 최고의 공간입니다. 소규모 공연 및 녹음도 가능합니다.",
  "phone": "02-1234-5678",
  "parkingAvailable": true,
  "parkingDescription": "건물 뒤편 지하주차장 이용 가능 (무료)",
  "displayAddress": "서울시 마포구 홍익로 23, 지하 1층",
  "keywords": ["인디", "녹음가능", "앙상블", "야간영업"],
  "homepageUrls": ["https://soulsoundroom.com", "https://instagram.com/soulsoundroom"],
  "notes": "금연 / 음식 반입 가능 / 무료 와이파이",
  "thumbnailUrl": "https://cdn.example.com/images/br-001-thumbnail.jpg",
  "roadAddress": "서울특별시 마포구 홍익로 23",
  "detailAddress": "지하 1층 101호",
  "latitude": 37.550123,
  "longitude": 126.923456
}

 */
public class BandRoomController {

    private final BandRoomService bandRoomService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody BandRoomRequest request) {
        return ResponseEntity.ok(bandRoomService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BandRoomResponse> get(@PathVariable String id) {
        return ResponseEntity.ok(bandRoomService.get(id));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Void> update(@PathVariable(name= "id")String id, @RequestBody BandRoomRequest request) {
//        if(bandRoomService.checkMy(request.getUserId(), id))
//        {
//            bandRoomService.update(id, request);
//            return ResponseEntity.noContent().build();
//        }
//        return null;
//
//    }

    @GetMapping
    public ResponseEntity<Page<BandRoomSimpleResponse>> getBandRooms(
            BandRoomSearchRequest req,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        Page<BandRoomSimpleResponse> rooms = bandRoomService.searchBandRooms(req, pageable);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/marker")
    public ResponseEntity<List<BandRoomMarkerResponse>> getBandRoomsMarker()
    {
        List<BandRoomMarkerResponse> responses = bandRoomService.getMarkers();
        return ResponseEntity.ok(responses);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bandRoomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
