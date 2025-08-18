package team.mut4.trip.domain.location.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import team.mut4.trip.global.exception.ExceptionCode;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum LocationExceptionCode implements ExceptionCode {
    LOCATION_NOT_FOUND(NOT_FOUND, "지역을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;

    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }

}
