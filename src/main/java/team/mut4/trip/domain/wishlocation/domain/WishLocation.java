package team.mut4.trip.domain.wishlocation.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.global.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WishLocation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;

    private double longitude;

    private String wishAddress;

    @Builder
    private WishLocation(
            double latitude,
            double longitude,
            String wishAddress
    ) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.wishAddress = wishAddress;
    }

}
