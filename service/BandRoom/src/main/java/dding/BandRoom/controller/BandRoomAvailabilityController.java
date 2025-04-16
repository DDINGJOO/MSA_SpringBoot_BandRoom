package dding.BandRoom.controller;

import dding.BandRoom.dto.response.BandRoom.BandRoomResponse;
import dding.BandRoom.entity.BandRoom;
import dding.BandRoom.entity.BandRoomScheduleRule;
import dding.BandRoom.service.BandRoomAvailabilityService;
import dding.BandRoom.service.BandRoomScheduleRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/band-rooms")
public class BandRoomAvailabilityController {

    private final BandRoomAvailabilityService availabilityService;
    private final BandRoomScheduleRuleService scheduleRuleService;

    @GetMapping("/{bandRoomId}/availability")
    public ResponseEntity<Map<String, Object>> checkAvailability(
            @PathVariable String bandRoomId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        boolean available = availabilityService.isAvailable(bandRoomId, date);

        Map<String, Object> response = new HashMap<>();
        response.put("bandRoomId", bandRoomId);
        response.put("date", date);
        response.put("available", available);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/available")
    //GET /api/band-rooms/available?date=2025-04-22&page=0&size=10
    public ResponseEntity<Page<BandRoomResponse>> getAvailableBandRooms(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        List<BandRoom> availableRooms = availabilityService.findAvailableRooms(date);

        List<BandRoomResponse> responses = availableRooms.stream()
                .map(room -> BandRoomResponse.builder()
                        .id(room.getId())
                        .name(room.getName())
                        .shortDescription(room.getShortDescription())
                        .thumbnailUrl(room.getThumbnailUrl())
                        .latitude(room.getLatitude())
                        .longitude(room.getLongitude())
                        .roadAddress(room.getRoadAddress())
                        .detailAddress(room.getDetailAddress())
                        .displayAddress(room.getDisplayAddress())
                        .parkingAvailable(room.getParkingAvailable())
                        .build())
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), responses.size());
        Page<BandRoomResponse> page = new PageImpl<>(responses.subList(start, end), pageable, responses.size());

        return ResponseEntity.ok(page);
    }

    /**
     * 현재 날짜와 시각을 전달받아 해당 밴드룸의 스케줄 규칙에 따른 영업 여부를 반환합니다.
     *
     * @param bandRoomId   밴드룸 ID
     * @param currentDate  현재 날짜 (ISO 형식: yyyy-MM-dd)
     * @param currentTime  현재 시각 (ISO 형식: HH:mm[:ss])
     * @return JSON 형태로 "bandRoomId"와 "isOpen" 값을 반환
     */
    @GetMapping("/available/{bandRoomId}")
    public ResponseEntity<Map<String, Object>> getScheduleOpenStatus(
            @PathVariable String bandRoomId,
            @RequestParam("currentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate,
            @RequestParam("currentTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime currentTime) {

        boolean isOpen = scheduleRuleService.isBandRoomOpen(bandRoomId,currentDate,currentTime);
        // 결과를 JSON으로 구성합니다.
        Map<String, Object> response = new HashMap<>();
        response.put("bandRoomId", bandRoomId);
        response.put("isOpen", isOpen);

        return ResponseEntity.ok(response);
    }
}


