package dding.BandRoom.dto.request.BandRoom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BandRoomSearchRequest {
    private String name;              // 합주실 이름
    private Boolean isOpen;           // 영업 여부
    private Boolean available;        // 예약 가능 여부
    private Double latitude;          // 위치 필터
    private Double longitude;
    private String keyword;           // 태그 키워드
    private int page = 0;
    private int size = 10;
}
