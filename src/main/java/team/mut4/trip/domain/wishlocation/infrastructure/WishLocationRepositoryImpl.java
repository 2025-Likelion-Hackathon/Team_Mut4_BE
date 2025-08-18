package team.mut4.trip.domain.wishlocation.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.domain.wishlocation.domain.WishLocationRepository;

@RequiredArgsConstructor
@Repository
public class WishLocationRepositoryImpl implements WishLocationRepository {

    private final WishLocationRepository wishLocationRepository;

    @Override
    public void save(WishLocation wishLocation) {
        wishLocationRepository.save(wishLocation);
    }

}
