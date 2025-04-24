package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {
    private String name;
    private String message;
    private HttpStatus statusCode;
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Serializable data;
}
