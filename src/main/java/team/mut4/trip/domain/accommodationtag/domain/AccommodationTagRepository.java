package team.mut4.trip.domain.accommodationtag.domain;

import java.util.List;

public interface AccommodationTagRepository {

    List<AccommodationTag> findAll();

    List<AccommodationTag> findByIdIn(List<Long> accommodationTagIds);

}
