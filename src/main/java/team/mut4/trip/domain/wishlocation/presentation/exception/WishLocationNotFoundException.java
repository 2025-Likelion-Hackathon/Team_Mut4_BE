package team.mut4.trip.domain.wishlocation.presentation.exception;

import team.mut4.trip.global.exception.CustomException;

public class WishLocationNotFoundException extends CustomException {

    public WishLocationNotFoundException() {
        super(WishLocationExceptionCode.WISH_LOCATION_NOT_FOUND);
    }

}
