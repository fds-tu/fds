package bg.tusofia.fcst.ksi.practikum.fds.services.base;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface BaseResourceService<ID> {
    Object getResource(ID id, HttpServletRequest request, List<Object> parentResources);
}
