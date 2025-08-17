package team.mut4.trip.global.util;

public class KakaoUrlBuilder {

    private static final String BASE_URL = "https://dapi.kakao.com";

    public static String buildCoordToAddressUrl(double longitude, double latitude) {
        return String.format("%s/v2/local/geo/coord2address.json?x=%f&y=%f",
                BASE_URL, longitude, latitude
        );
    }

}
