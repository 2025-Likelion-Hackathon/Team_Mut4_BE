package team.mut4.trip.domain.wishlocation.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;

public interface WishLocationJpaRepository extends JpaRepository<WishLocation, Long> {
}
