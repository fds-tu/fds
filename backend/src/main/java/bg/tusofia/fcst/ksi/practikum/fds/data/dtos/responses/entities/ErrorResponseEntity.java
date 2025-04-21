package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.entities;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.base.BaseErrorResponseEntityBody;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ErrorResponseEntity<T> extends ResponseEntity<BaseErrorResponseEntityBody<T>> {
    public ErrorResponseEntity(HttpStatusCode status) {
        super(status);
    }

    public ErrorResponseEntity(T body, HttpStatusCode status) {
        super(new BaseErrorResponseEntityBody<>(body), status);
    }

    public ErrorResponseEntity(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(headers, status);
    }

    public ErrorResponseEntity(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(new BaseErrorResponseEntityBody<>(body), headers, rawStatus);
    }

    public ErrorResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatusCode statusCode) {
        super(new BaseErrorResponseEntityBody<>(body), headers, statusCode);
    }
}
