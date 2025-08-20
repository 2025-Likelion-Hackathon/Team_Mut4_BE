package team.mut4.trip.domain.accommodation.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import team.mut4.trip.global.exception.ExceptionCode;

@Getter
@RequiredArgsConstructor
public enum AccommodationExceptionCode implements ExceptionCode {
    ACCOMMODATION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 숙소를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;

    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }

}
