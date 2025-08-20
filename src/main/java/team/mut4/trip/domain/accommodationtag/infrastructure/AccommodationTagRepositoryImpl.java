package team.mut4.trip.domain.accommodationtag.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTag;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTagRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AccommodationTagRepositoryImpl implements AccommodationTagRepository {

    private final AccommodationTagJpaRepository accommodationTagJpaRepository;

    @Override
    public List<AccommodationTag> findAll() {
        return accommodationTagJpaRepository.findAll();
    }

    @Override
    public List<AccommodationTag> findByIdIn(List<Long> accommodationTagIds) {
        return accommodationTagJpaRepository.findByIdIn(accommodationTagIds);
    }

}
