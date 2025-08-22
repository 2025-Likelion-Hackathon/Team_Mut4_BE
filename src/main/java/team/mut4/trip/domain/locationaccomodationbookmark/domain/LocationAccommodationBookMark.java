package team.mut4.trip.domain.locationaccomodationbookmark.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.global.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class LocationAccommodationBookMark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    @Builder
    private LocationAccommodationBookMark(Location location, Accommodation accommodation) {
        this.location = location;
        this.accommodation = accommodation;
    }

}
