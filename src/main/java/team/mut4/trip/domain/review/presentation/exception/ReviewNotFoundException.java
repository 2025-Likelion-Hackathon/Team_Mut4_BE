package team.mut4.trip.domain.review.presentation.exception;

import team.mut4.trip.global.exception.CustomException;

public class ReviewNotFoundException extends CustomException {

    public ReviewNotFoundException() {
        super(ReviewExceptionCode.REVIEW_NOT_FOUND);
    }

}
