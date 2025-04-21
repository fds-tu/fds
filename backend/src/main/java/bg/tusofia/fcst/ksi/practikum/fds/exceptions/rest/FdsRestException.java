package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class FdsRestException extends RuntimeException implements Serializable {
    private final String name;
    private final String message;
    private final HttpStatus statusCode;
    @Nullable
    private final Serializable data;
}
