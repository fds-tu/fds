package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.entities;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.base.BaseSuccessResponseEntityBody;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class SuccessResponseEntity<T> extends ResponseEntity<BaseSuccessResponseEntityBody<T>> {

    public SuccessResponseEntity(HttpStatusCode status) {
        super(status);
    }

    public SuccessResponseEntity(T body, HttpStatusCode status) {
        super(new BaseSuccessResponseEntityBody<>(body), status);
    }

    public SuccessResponseEntity(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(headers, status);
    }

    public SuccessResponseEntity(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(new BaseSuccessResponseEntityBody<>(body), headers, rawStatus);
    }

    public SuccessResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatusCode statusCode) {
        super(new BaseSuccessResponseEntityBody<>(body), headers, statusCode);
    }
}
