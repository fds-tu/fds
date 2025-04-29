package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T error;
}
