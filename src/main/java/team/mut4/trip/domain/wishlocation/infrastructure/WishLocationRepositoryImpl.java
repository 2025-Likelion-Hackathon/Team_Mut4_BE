package team.mut4.trip.domain.wishlocation.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.domain.wishlocation.domain.WishLocationRepository;
import team.mut4.trip.domain.wishlocation.presentation.exception.WishLocationNotFoundException;

@RequiredArgsConstructor
@Repository
public class WishLocationRepositoryImpl implements WishLocationRepository {

    private final WishLocationJpaRepository wishLocationJpaRepository;

    @Override
    public void save(WishLocation wishLocation) {
        wishLocationJpaRepository.save(wishLocation);
    }

    @Override
    public WishLocation findByWishLocationId(Long wishLocationId) {
        return wishLocationJpaRepository.findById(wishLocationId)
                .orElseThrow(WishLocationNotFoundException::new);
    }

}
