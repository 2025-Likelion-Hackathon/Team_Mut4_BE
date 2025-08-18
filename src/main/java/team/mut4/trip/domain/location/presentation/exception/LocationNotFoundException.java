package team.mut4.trip.domain.location.presentation.exception;

import team.mut4.trip.global.exception.CustomException;

public class LocationNotFoundException extends CustomException {

    public LocationNotFoundException() {
        super(LocationExceptionCode.LOCATION_NOT_FOUND);
    }

}
