package team.mut4.trip.domain.accommodation.presentation.exception;

import team.mut4.trip.global.exception.CustomException;

public class AccommodationNotFoundException extends CustomException {

    public AccommodationNotFoundException() {
        super(AccommodationExceptionCode.ACCOMMODATION_NOT_FOUND);
    }

}
