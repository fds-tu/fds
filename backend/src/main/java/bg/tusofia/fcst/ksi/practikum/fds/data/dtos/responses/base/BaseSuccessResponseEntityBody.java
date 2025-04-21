package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.base;

public class BaseSuccessResponseEntityBody<T> extends BaseResponseEntityBody<SuccessResponse<T>> {
    public BaseSuccessResponseEntityBody(T data) {
        super(true, new SuccessResponse<>(data));
    }
}
