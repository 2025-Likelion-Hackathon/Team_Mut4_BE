package team.mut4.trip.global.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomNicknameGenerator {

    private final List<String> adjectives = List.of(
            "맛있는", "달콤한", "매콤한", "짭짤한", "고소한", "시원한", "풍미있는", "감칠맛나는", "바삭한", "부드러운"
    );

    private final List<String> nouns = List.of(
            "밥", "김치", "치킨", "피자", "떡볶이", "라면", "빵", "커피", "햄버거", "초콜릿"
    );

    private final Random random = new Random();

    public String foodGenerate() {
        String adjective = adjectives.get(random.nextInt(adjectives.size()));
        String noun = nouns.get(random.nextInt(nouns.size()));
        return adjective + " " + noun;
    }
}
