package bg.tusofia.fcst.ksi.practikum.fds.utilities.interfaces;

import java.util.List;

@FunctionalInterface
public interface GetResourcesByIdsCallback<R> {
    List<R> call(List<Long> ids);

}
