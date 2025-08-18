package team.mut4.trip.global.util;

import lombok.Getter;

@Getter
public enum KakaoCategory {
    FOOD("FD6"),
    ACCOMMODATION("AD5");

    private final String code;

    KakaoCategory(String code) {
        this.code = code;
    }

}
