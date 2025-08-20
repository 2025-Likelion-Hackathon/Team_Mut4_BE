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
import team.mut4.trip.domain.location.dto.response.KakaoPlaceSearchResponse;
import team.mut4.trip.domain.location.dto.response.KakaoResponse;
import team.mut4.trip.domain.location.dto.response.MapInfoResponse;
import team.mut4.trip.global.util.KakaoCategory;
import team.mut4.trip.global.util.KakaoUrlBuilder;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class KakaoMapClient {

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    private final RestTemplate restTemplate;

    public List<MapInfoResponse> searchNearbyRestaurants(double longitude, double latitude, int radius) {
        return searchNearby(longitude, latitude, radius, KakaoCategory.FOOD);
    }

    public List<MapInfoResponse> searchNearbyAccommodations(double longitude, double latitude, int radius) {
        return searchNearby(longitude, latitude, radius, KakaoCategory.ACCOMMODATION);
    }

    public List<MapInfoResponse> searchKeywordByRestaurants(String keyword, double longitude, double latitude, int radius) {
        return searchNearbyByKeyword(keyword, longitude, latitude, radius);
    }

    public List<MapInfoResponse> searchKeywordByAccommodations(String keyword, double longitude, double latitude, int radius) {
        return searchNearbyByKeyword(keyword, longitude, latitude, radius);
    }

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

    private List<MapInfoResponse> searchNearby(double longitude, double latitude, int radius, KakaoCategory category) {
        String url = KakaoUrlBuilder.buildCategorySearchUrl(category, longitude, latitude, radius);

        return callKakaoApi(url, KakaoPlaceSearchResponse.class, body ->
                        body.documents().stream()
                                .map(doc -> MapInfoResponse.builder()
                                        .placeName(doc.place_name())
                                        .addressName(doc.address_name())
                                        .roadAddressName(doc.road_address_name())
                                        .phone(doc.phone())
                                        .placeUrl(doc.place_url())
                                        .longitude(Double.parseDouble(doc.x()))
                                        .latitude(Double.parseDouble(doc.y()))
                                        .build())
                                .toList()
                , "카카오 장소 검색 API 호출 중 오류 발생");
    }

    private List<MapInfoResponse> searchNearbyByKeyword(String keyword, double longitude, double latitude, int radius) {
        String url = KakaoUrlBuilder.buildKeywordSearchUrl(keyword, longitude, latitude, radius);

        return callKakaoApi(url, KakaoPlaceSearchResponse.class, body ->
                        body.documents().stream()
                                .map(doc -> MapInfoResponse.builder()
                                        .placeName(doc.place_name())
                                        .addressName(doc.address_name())
                                        .categoryName(doc.category_name())
                                        .roadAddressName(doc.road_address_name())
                                        .phone(doc.phone())
                                        .placeUrl(doc.place_url())
                                        .longitude(Double.parseDouble(doc.x()))
                                        .latitude(Double.parseDouble(doc.y()))
                                        .build())
                                .toList()
                , "카카오 키워드 검색 API 호출 중 오류 발생");
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
