package dding.roomaddress.service;



import dding.roomaddress.dto.KakaoAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpHeaders;
@Service
@RequiredArgsConstructor
public class KakaoApiService {

    private final WebClient webClient;

    @Value("${kakao.api.url}")
    private String kakaoApiUrl;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public KakaoAddressResponse.Document searchAddress(String query) {
        KakaoAddressResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(kakaoApiUrl)
                        .queryParam("query", query)
                        .build())
                .header("Authorization", "KakaoAK " + kakaoApiKey)
                .retrieve()
                .bodyToMono(KakaoAddressResponse.class)
                .block();

        if (response == null || response.getDocuments().isEmpty()) {
            throw new IllegalArgumentException("주소를 찾을 수 없습니다.");
        }

        return response.getDocuments().get(0);
    }
}
