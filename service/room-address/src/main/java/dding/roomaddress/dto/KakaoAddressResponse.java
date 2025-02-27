package dding.roomaddress.dto;

import lombok.Data;

import java.util.List;

@Data
public class KakaoAddressResponse {
    private List<Document> documents;

    @Data
    public static class Document {
        private String address_name;   // 전체 주소
        private String road_address_name; // 도로명 주소
        private String x;  // 경도 (Longitude)
        private String y;  // 위도 (Latitude)

        private Address address;

        @Data
        public static class Address {
            private String region_1depth_name; // 시/도
            private String region_2depth_name; // 구/군
            private String region_3depth_name; // 동/읍/면
        }
    }
}

