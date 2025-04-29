package bg.tusofia.fcst.ksi.practikum.fds.utilities.interfaces;

@FunctionalInterface
public interface EditResourceCallback<R> {
    R call(R input);
}