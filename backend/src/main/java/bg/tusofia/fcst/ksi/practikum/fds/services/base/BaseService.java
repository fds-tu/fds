package bg.tusofia.fcst.ksi.practikum.fds.services.base;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.BaseAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.ResourceNotFoundException;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.interfaces.*;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@MappedSuperclass
@RequiredArgsConstructor
public abstract class BaseService<R extends BaseEntity<ID>, ID, JR extends JpaRepository<R, ID>, PR extends PagingAndSortingRepository<R, ID>> implements BaseResourceService<ID> {
    protected final JR jpaRepository;
    protected final PR pagingRepository;
    protected final BaseAuthorizer<R> authorizer;
    protected final String resourceName;

    public final R getResourceById(ID id) {
        return jpaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id.toString()));
    }

    public final List<R> getResourcesByIds(List<ID> ids) {
        return ids.stream().map(this::getResourceById).toList();
    }

    public <T, RS> void registerRelations(ClearResourceCallback<R> clearResourceCallback, CreateRelationCallback<T, R, RS> createRelationCallback, GetResourcesByIdsCallback<RS> callback, R primary, List<Long> ids, SetRelationsCallback<List<T>> setRelationsCallback) {
        if (ids == null) {
            return;
        }
        R unsaved = clearResourceCallback.call();
        if(unsaved != null) {
            jpaRepository.save(unsaved);
        }

        List<RS> secondaries = callback.call(ids);
        setRelationsCallback.call(secondaries.stream().map(s -> createRelationCallback.call(primary, s)).toList());
    }

    protected R getResourceInternal(ID resourceId, List<Object> parentResources) {
        return getResourceById(resourceId);
    }

    protected List<R> getResourcesInternal(int page, int size, String sortBy, List<Object> parentResources) {
        return pagingRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy))).stream().toList();
    }

    protected R createResourceInternal(R resource, HttpServletRequest request, List<Object> parentResources) {
        save(resource);

        return resource;
    }

    protected R editResourceInternal(R resource, HttpServletRequest request, List<Object> parentResources) {
        save(resource);

        return resource;
    }

    protected void deleteResourceInternal(R resource, List<Object> parentResources) {
        jpaRepository.delete(resource);
    }


    public final R getResource(ID resourceId, HttpServletRequest request, List<Object> parentResources) {
        R resource = getResourceInternal(resourceId, parentResources);
        User user = this.authorizer.authorize(parentResources, ResourceAccessType.GET_SPECIFIC, resource, request);

        return onGetResource(resource, user);
    }

    public final List<R> getResources(int page, int size, String sortBy, HttpServletRequest request, List<Object> parentResources) {
        User user = authorizer.authorize(parentResources, ResourceAccessType.GET_ALL, null, request);
        List<R> resources = getResourcesInternal(page, size, sortBy, parentResources);
        return onGetResources(resources, user);
    }

    public final R createResource(R resource, HttpServletRequest request, List<Object> parentResources) {
        User user = authorizer.authorize(parentResources, ResourceAccessType.CREATE, resource, request);
        R savedResource = createResourceInternal(resource, request, parentResources);

        return onCreateResource(savedResource, user);
    }

    public final R editResource(ID resourceId, EditResourceCallback<R> callback, HttpServletRequest request, List<Object> parentResources) {
        R oldResource = getResourceById(resourceId);
        R newResource = callback.call(oldResource);
        User user = this.authorizer.authorize(parentResources, ResourceAccessType.EDIT_SPECIFIC, newResource, request);
        newResource = editResourceInternal(newResource, request, parentResources);

        return onUpdateResource(newResource, user);
    }

    public final R deleteResource(ID resourceId, HttpServletRequest request, List<Object> parentResources) {
        R resource = getResourceById(resourceId);
        User user = this.authorizer.authorize(parentResources, ResourceAccessType.DELETE_SPECIFIC, resource, request);

        deleteResourceInternal(resource, parentResources);

        return onDeleteResource(resource, user);
    }

    protected final void save(R resource) {
        jpaRepository.save(resource);
    }

    protected R onGetResource(R resource, User user) {
        return resource;
    }

    protected List<R> onGetResources(List<R> resources, User user) {
        return resources;
    }

    protected R onCreateResource(R resource, User user) {
        return resource;
    }

    protected R onUpdateResource(R resource, User user) {
        return resource;
    }

    protected R onDeleteResource(R resource, User user) {
        return resource;
    }
}
