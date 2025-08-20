package team.mut4.trip.global.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomAccommodationNicknameGenerator {

    private final List<String> adjectives = List.of(
            "아늑한", "편안한", "고급스러운", "럭셔리한", "포근한", "깨끗한", "조용한", "산뜻한", "넓은", "환한"
    );

    private final List<String> nouns = List.of(
            "호텔", "펜션", "게스트하우스", "리조트", "하우스", "빌라", "민박", "모텔", "캐빈", "스테이"
    );

    private final Random random = new Random();

    public String accommodationGenerate() {
        String adjective = adjectives.get(random.nextInt(adjectives.size()));
        String noun = nouns.get(random.nextInt(nouns.size()));
        return adjective + " " + noun;
    }

}
