package net.javaguides.eats_hub_catalog.handler;

import lombok.RequiredArgsConstructor;
import net.javaguides.eats_hub_catalog.service.definitions.RestaurantBusinessService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * RestaurantCatalogHandler
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 28/10/2026 - 09:07
 * @since 1.25
 */
@Component
@RequiredArgsConstructor
public class RestaurantCatalogHandler {

    private final RestaurantBusinessService restaurantBusinessService;

    public Mono<ServerResponse> getAllRestaurants(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getRestaurantByName(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getRestaurantByCousinType(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getRestaurantBetweenPrice(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getRestaurantByCity(ServerRequest serverRequest) {
        return null;
    }

}
