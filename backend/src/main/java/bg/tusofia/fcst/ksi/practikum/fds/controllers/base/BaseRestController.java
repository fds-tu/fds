package bg.tusofia.fcst.ksi.practikum.fds.controllers.base;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseResourceService;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@MappedSuperclass
public abstract class BaseRestController<R extends BaseEntity<Long>, C, E, Re, S extends BaseService<R, Long, JR, PR>, JR extends JpaRepository<R, Long>, PR extends PagingAndSortingRepository<R, Long>> extends BaseGetController<R, Re, S, JR, PR>{

    public BaseRestController(S service, ModelMapper modelMapper, Class<R> resourceClass, Class<Re> responseClass) {
        super(service, modelMapper, resourceClass, responseClass);
    }

    public BaseRestController(S service, ModelMapper modelMapper,  Class<R> resourceClass, Class<Re> responseClass, String urlPattern, List<BaseResourceService<Long>> checkerServices) {
        super(service, modelMapper, resourceClass, responseClass, urlPattern, checkerServices);
    }

    public ResponseEntity<?> createResource(@RequestBody C createResourceDto, HttpServletRequest request) {
        R resource = this.mapFromCreateDto(createResourceDto);
        Re response = this.mapper.map(service.createResource(resource, request, preAuthorize(request)));

        return this.generateResponse(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> editResource(@PathVariable Long id, @RequestBody @Valid E editResourceDto, HttpServletRequest request) {
        service.editResource(id, (resource) -> this.mapFromEditDto(resource, editResourceDto), request, preAuthorize(request));

        return this.generateResponse(null, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteResource(@PathVariable @Valid Long id, HttpServletRequest request) {
        service.deleteResource(id, request, preAuthorize(request));
        return this.generateResponse(null, HttpStatus.NO_CONTENT);
    }

    protected R mapFromCreateDto(C createResourceDto) {
        return this.mapper.map(createResourceDto, this.resourceClass);
    }

    protected R mapFromEditDto(R resource, E editResourceDto) {
        return this.mapper.map(resource, editResourceDto);
    }
}
