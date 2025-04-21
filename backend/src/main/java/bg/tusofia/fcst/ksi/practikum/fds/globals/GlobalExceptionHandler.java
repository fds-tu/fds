package bg.tusofia.fcst.ksi.practikum.fds.globals;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.entities.ErrorResponseEntity;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.ExceptionDto;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.FdsRestException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseEntity<ExceptionDto> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getObjectName();
            errors.put(fieldName, errorMessage);
        });

        return this.handleException(new InvalidResourceException("Body", (Serializable) errors, true));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseEntity<ExceptionDto> handleException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach((error) -> {
            String fieldName = error.getPropertyPath().toString();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });

        return this.handleException(new InvalidResourceException("Body", (Serializable) errors, true));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorResponseEntity<ExceptionDto> handleException(Exception ex) {
        return this.handleException(new FdsRestException("Exception", ex.getMessage(), HttpStatus.BAD_REQUEST, null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseEntity<ExceptionDto> handleException(RuntimeException ex) {
        return this.handleException(new FdsRestException("RuntimeException", ex.getMessage(), HttpStatus.BAD_REQUEST, null));
    }

    @ExceptionHandler(FdsRestException.class)
    public ErrorResponseEntity<ExceptionDto> handleException(FdsRestException ex) {
        return new ErrorResponseEntity<>(modelMapper.map(ex, ExceptionDto.class), ex.getStatusCode());
    }
}