package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.base;

import lombok.Data;

import java.util.Date;

@Data
public class BaseResponseEntityBody<T> {
    private final Boolean success;
    private final Date timestamp;
    private final T response;

    public BaseResponseEntityBody(Boolean success, T response) {
        this.response = response;
        this.timestamp = new Date();
        this.success = success;
    }

    public BaseResponseEntityBody(Boolean success, Date timestamp, T response) {
        this.success = success;
        this.timestamp = timestamp;
        this.response = response;
    }
}
