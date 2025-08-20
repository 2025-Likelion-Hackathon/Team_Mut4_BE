package team.mut4.trip.domain.accommodation.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.global.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Accommodation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String categoryName;

    private String roadAddress;

    private String phone;

    private String placeUrl;

    private double latitude;

    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    private WishLocation wishLocation;

    @Builder
    private Accommodation(
            String name,
            String address,
            String categoryName,
            String roadAddress,
            String phone,
            String placeUrl,
            double latitude,
            double longitude,
            Location location,
            WishLocation wishLocation
    ) {
        this.name = name;
        this.address = address;
        this.categoryName = categoryName;
        this.roadAddress = roadAddress;
        this.phone = phone;
        this.placeUrl = placeUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.wishLocation = wishLocation;
    }

}
