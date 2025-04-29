package bg.tusofia.fcst.ksi.practikum.fds.globals;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.ExceptionDto;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.entities.ErrorResponseEntity;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.FdsRestException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Pair;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
            String errorMessage = error.getDefaultMessage();
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
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponseEntity<ExceptionDto> handleException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        if (cause instanceof JsonMappingException jsonMappingException) {
            Map<String, String> errors = new HashMap<>();

            jsonMappingException.getPath().stream()
                    .map(ref -> Pair.of(ref.getFieldName(), String.format("%s is invalid", ref.getFieldName()) ))
                    .forEach(pair -> errors.put(pair.getLeft(), pair.getRight()));


            return this.handleException(new InvalidResourceException("Body", (Serializable) errors, true));
        }

        return this.handleException(new InvalidResourceException("Body", null, true));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponseEntity<ExceptionDto> handleException(DataIntegrityViolationException ex) {
        return this.handleException(new FdsRestException("DataIntegrityViolationException", "Could not execute SQL statement", HttpStatus.BAD_REQUEST, null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorResponseEntity<ExceptionDto> handleException(Exception ex) {
        return this.handleException(new FdsRestException("Exception", ex.getMessage(), HttpStatus.BAD_REQUEST, null));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseEntity<ExceptionDto> handleException(RuntimeException ex) {
        return this.handleException(new FdsRestException("RuntimeException", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null));
    }

    @ExceptionHandler(FdsRestException.class)
    public ErrorResponseEntity<ExceptionDto> handleException(FdsRestException ex) {
        return new ErrorResponseEntity<>(modelMapper.map(ex, ExceptionDto.class), ex.getStatusCode());
    }
}