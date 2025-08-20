package team.mut4.trip.domain.accommodationtag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTag;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTagRepository;
import team.mut4.trip.domain.accommodationtag.dto.response.AccommodationTagInfoResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccommodationTagService {

    private final AccommodationTagRepository accommodationTagRepository;

    @Transactional(readOnly = true)
    public List<AccommodationTagInfoResponse> getAllAccommodationTags() {
        List<AccommodationTag> accommodationTags = accommodationTagRepository.findAll();

        return accommodationTags.stream()
                .map(AccommodationTagInfoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AccommodationTag> getAccommodationTagsByIds(List<Long> accommodationTagIds) {
        return accommodationTagRepository.findByIdIn(accommodationTagIds);
    }

}
