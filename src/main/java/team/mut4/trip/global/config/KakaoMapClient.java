package team.mut4.trip.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import team.mut4.trip.domain.location.dto.response.KakaoResponse;
import team.mut4.trip.global.util.KakaoUrlBuilder;

import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class KakaoMapClient {

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    private final RestTemplate restTemplate;

    public String getAddress(double longitude, double latitude) {
        String url = KakaoUrlBuilder.buildCoordToAddressUrl(longitude, latitude);

        return callKakaoApi(url, KakaoResponse.class, body -> {
            if (!body.documents().isEmpty()) {
                var doc = body.documents().get(0);
                if (doc.road_address() != null) return doc.road_address().address_name();
                if (doc.address() != null) return doc.address().address_name();
            }
            return "";
        }, "카카오 주소 변환 API 호출 중 오류 발생");
    }

    private <T, R> R callKakaoApi(String url, Class<T> responseType, Function<T, R> mapper, String errorMsg) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
            T body = response.getBody();
            return (body != null) ? mapper.apply(body) : null;
        } catch (RestClientException e) {
            throw new RuntimeException(errorMsg, e);
        }
    }

}
