package team.mut4.trip.domain.food.domain;

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
public class Food extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String roadAddress;

    private String phone;

    private String placeUrl;

    private double latitude;

    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @Builder
    private Food(
            String name,
            String address,
            String roadAddress,
            String phone,
            String placeUrl,
            double latitude,
            double longitude,
            Location location
    ) {
        this.name = name;
        this.address = address;
        this.roadAddress = roadAddress;
        this.phone = phone;
        this.placeUrl = placeUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

}

