package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse<T> {
    private T error;
}
