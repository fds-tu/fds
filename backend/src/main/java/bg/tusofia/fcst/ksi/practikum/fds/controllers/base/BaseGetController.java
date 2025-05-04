package bg.tusofia.fcst.ksi.practikum.fds.controllers.base;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.entities.SuccessResponseEntity;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidUrlComponentsException;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseResourceService;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.BaseMapper;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.UrlMatcher;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class BaseGetController<R extends BaseEntity<Long>, Re, S extends BaseService<R, Long, JR, PR>, JR extends JpaRepository<R, Long>, PR extends PagingAndSortingRepository<R, Long>>{
    protected final S service;
    protected final BaseMapper<R, Re> mapper;
    protected final Class<R> resourceClass;
    protected final String urlPattern;
    protected final List<BaseResourceService<Long>> checkerServices;

    public BaseGetController(S service, ModelMapper modelMapper, Class<R> resourceClass, Class<Re> responseClass, String urlPattern, List<BaseResourceService<Long>> checkerServices) {
        this.service = service;
        this.resourceClass = resourceClass;
        this.mapper = new BaseMapper<>(modelMapper, resourceClass, responseClass);
        this.urlPattern = urlPattern;
        this.checkerServices = checkerServices;
    }

    public BaseGetController(S service, ModelMapper modelMapper, Class<R> resourceClass, Class<Re> responseClass) {
        this.service = service;
        this.resourceClass = resourceClass;
        this.mapper = new BaseMapper<>(modelMapper, resourceClass, responseClass);
        this.urlPattern = null;
        this.checkerServices = null;
    }

    public ResponseEntity<?> getResourceById(@PathVariable @Valid Long id, HttpServletRequest request) {
        Re response = this.mapper.map(service.getResource(id, request, preAuthorize(request)));
        return this.generateResponse(response);
    }

    public ResponseEntity<?> getAllResources(@RequestParam(defaultValue = "0") @Min(0) @Valid Integer page, HttpServletRequest request) {
        List<Re> responses =  service.getResources(page, 20, "createdDate", request, preAuthorize(request)).stream().map(this.mapper::map).toList();
        return this.generateResponse(responses);
    }

    protected <T> SuccessResponseEntity<T> generateResponse(T data) {
        return new SuccessResponseEntity<>(data, HttpStatus.OK);
    }

    protected <T> SuccessResponseEntity<T> generateResponse(T data, HttpStatus status) {
        return new SuccessResponseEntity<>(data, status);
    }

    protected final List<Object> preAuthorize(HttpServletRequest request) {
        if(this.urlPattern == null || this.checkerServices == null || this.checkerServices.isEmpty()) {
            return List.of();
        }

        List<UrlMatcher.UrlComponent> ids = UrlMatcher.extractPathVariables(this.urlPattern, request.getRequestURI());

        if (ids.isEmpty()) {
            return List.of();
        }

        if (ids.size() != this.checkerServices.size()) {
            throw new InvalidUrlComponentsException();
        }

        ArrayList<Object> parentResources = new ArrayList<>();

        for(int i = 0; i < ids.size(); i++) {
            Object resource = this.checkerServices.get(i).getResource(ids.get(i).getIdValue(), request, parentResources);
            parentResources.add(resource);
        }

        return parentResources.stream().toList();
    }
}