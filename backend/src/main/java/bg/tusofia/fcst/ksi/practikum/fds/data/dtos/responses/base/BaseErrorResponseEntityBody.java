package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.base;

public class BaseErrorResponseEntityBody<T> extends BaseResponseEntityBody<ErrorResponse<T>> {
    public BaseErrorResponseEntityBody(T data) {
        super(false, new ErrorResponse<>(data));
    }
}
