package bg.tusofia.fcst.ksi.practikum.fds.utilities.interfaces;

@FunctionalInterface
public interface ClearResourceCallback<R> {
    R call();
}
