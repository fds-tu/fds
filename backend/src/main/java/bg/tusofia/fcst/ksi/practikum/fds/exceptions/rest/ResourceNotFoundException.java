package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends FdsRestException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(
                "ResourceNotFoundException",
                String.format("Resource %s with %s %s does not exist", resourceName, fieldName, fieldValue),
                HttpStatus.NOT_FOUND,
                null
        );
    }
}
