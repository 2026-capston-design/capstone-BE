package backend.capstone.global.exception;

import java.util.List;
import org.springframework.validation.FieldError;

public record ErrorResponse(
    String code,
    String message,
    List<FieldErrorDetail> fieldErrors
) {

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message, List.of());
    }

    public static ErrorResponse of(String code, String message,
        List<FieldErrorDetail> fieldErrors) {
        return new ErrorResponse(code, message, fieldErrors);
    }

    public record FieldErrorDetail(
        String field,
        String reason
    ) {

        public FieldErrorDetail(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }

    }

}
