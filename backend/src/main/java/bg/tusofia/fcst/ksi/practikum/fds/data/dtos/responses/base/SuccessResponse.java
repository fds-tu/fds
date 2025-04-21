package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse<T> {
    private T data;
}
