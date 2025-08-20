package team.mut4.trip.domain.foodreview.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import team.mut4.trip.global.exception.ExceptionCode;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum ReviewExceptionCode implements ExceptionCode {
    REVIEW_NOT_FOUND(NOT_FOUND, "해당 리뷰를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;

    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }
}
