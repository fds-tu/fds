package bg.tusofia.fcst.ksi.practikum.fds.authorizers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.BaseAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Delivery;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.RestaurantAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.enums.delivery.DeliveryStatus;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.DeliveryAlreadyTakenException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceAccessTypeException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceCreationException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceDeletionException;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceAuthorizer extends RestaurantOwnerAuthorizer<Product> {
    public ProductServiceAuthorizer(AuthService authService) {
        super(authService);
    }

    @Override
    public User authorize(List<Object> parentResources, ResourceAccessType accessType, Product resource, HttpServletRequest request) {
        Restaurant restaurant = (Restaurant) parentResources.getFirst();
        return this.verifyRestaurantOwner(parentResources, accessType, restaurant, request);
    }

    @Component
    public static class RestaurantDeliveryServiceAuthorizer extends BaseAuthorizer<Delivery> {
        public RestaurantDeliveryServiceAuthorizer(AuthService authService) {
            super(authService);
        }

        @Override
        public User authorize(List<Object> parentResources, ResourceAccessType accessType, Delivery resource, HttpServletRequest request) {
            User currentUser = super.authorize(parentResources, accessType, resource, request);
            Restaurant restaurant = (Restaurant) parentResources.getFirst();
            boolean isRestaurantCourier = restaurant.getRoles().stream().anyMatch(r -> r.getId().equals(currentUser.getId()) && r.getRestaurantAccessType().equals(RestaurantAccessType.COURIER));
            boolean isRestaurantAdmin = restaurant.getRoles().stream().anyMatch(r -> r.getId().equals(currentUser.getId()) && r.getRestaurantAccessType().equals(RestaurantAccessType.ADMIN));
            boolean isCurrentCustomer = resource != null && resource.getCustomer().getId().equals(currentUser.getId());
            boolean isCurrentCourier = resource != null && resource.getCourier().getId().equals(currentUser.getId());

            List<DeliveryStatus> validDeliveryStatusAdmin = List.of(DeliveryStatus.PREPARED, DeliveryStatus.AWAITING_DELIVERY, DeliveryStatus.ON_DELIVERY);
            List<DeliveryStatus> validDeliveryStatusCourier = List.of(DeliveryStatus.DELIVERED);

            if(
                (accessType.equals(ResourceAccessType.GET_SPECIFIC) && !(isRestaurantCourier || isCurrentCourier || isCurrentCustomer ||  isRestaurantAdmin))
                || (accessType.equals(ResourceAccessType.GET_ALL) && !(isRestaurantAdmin || isRestaurantCourier))
                || (accessType.equals(ResourceAccessType.EDIT_SPECIFIC) && !(isCurrentCourier || isRestaurantAdmin))
                || (accessType.equals(ResourceAccessType.EDIT_SPECIFIC) && isCurrentCourier && !validDeliveryStatusCourier.contains(resource.getDeliveryStatus()))
                || (accessType.equals(ResourceAccessType.EDIT_SPECIFIC) && isRestaurantAdmin && !validDeliveryStatusAdmin.contains(resource.getDeliveryStatus()))
                || (accessType.equals(ResourceAccessType.PATCH_SPECIFIC) && !isRestaurantCourier)
                || (accessType.equals(ResourceAccessType.GET_MINE) && !isRestaurantCourier)
            ) {
                throw new InvalidResourceAccessTypeException("Delivery", accessType);
            }

            if(accessType.equals(ResourceAccessType.PATCH_SPECIFIC) && resource.getCourier() != null) {
                throw new DeliveryAlreadyTakenException();
            }

            if(accessType.equals(ResourceAccessType.DELETE_SPECIFIC)) {
                throw new InvalidResourceDeletionException("Delivery");
            }

            if(accessType.equals(ResourceAccessType.CREATE) && (isRestaurantCourier || isRestaurantAdmin)) {
                throw new InvalidResourceCreationException("Delivery");
            }

            return currentUser;
        }
    }
}
