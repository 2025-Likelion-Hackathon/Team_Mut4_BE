package team.mut4.trip.domain.food.presentation.exception;

import team.mut4.trip.global.exception.CustomException;

public class FoodNotFoundException extends CustomException {

    public FoodNotFoundException() {
        super(FoodExceptionCode.FOOD_NOT_FOUND);
    }

}
