package team.mut4.trip.domain.accommodation.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodation.domain.AccommodationRepository;
import team.mut4.trip.domain.accommodation.presentation.exception.AccommodationNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AccommodationRepositoryImpl implements AccommodationRepository {

    private final AccommodationJpaRepository accommodationJpaRepository;

    @Override
    public Accommodation save(Accommodation accommodation) {
        return accommodationJpaRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> findByNameAndAddress(String name, String address) {
        return accommodationJpaRepository.findByNameAndAddress(name, address);
    }

    @Override
    public Accommodation findByAccommodationId(Long accommodationId) {
        return accommodationJpaRepository.findById(accommodationId)
                .orElseThrow(AccommodationNotFoundException::new);
    }

}
