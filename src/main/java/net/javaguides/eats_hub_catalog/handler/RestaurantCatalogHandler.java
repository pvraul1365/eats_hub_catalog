package net.javaguides.eats_hub_catalog.handler;

import com.mongodb.internal.connection.Server;
import java.util.Arrays;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import net.javaguides.eats_hub_catalog.dtos.responses.RestaurantResponse;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import net.javaguides.eats_hub_catalog.service.definitions.RestaurantBusinessService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

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
        final var restaurantsFlux = this.restaurantBusinessService.readAll();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(restaurantsFlux, RestaurantResponse.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getRestaurantByName(ServerRequest serverRequest) {
        final var restaurantName = serverRequest.pathVariable("name");
        final var monoResponse = this.restaurantBusinessService.readByName(restaurantName);

        return monoResponse
                .flatMap(restaurantResponse -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(restaurantResponse))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getRestaurantByCousinType(ServerRequest serverRequest) {
        final var cousinType = serverRequest.queryParam("cousinType");
        final var fluxResponse = this.restaurantBusinessService.readByCuisineType(cousinType.orElse(null));

        if (Objects.isNull(cousinType)) {
            return ServerResponse.badRequest().bodyValue("cousinType query parameter is required");
        }

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, RestaurantResponse.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getRestaurantBetweenPrice(ServerRequest serverRequest) {

        final var prices = serverRequest.queryParam("prices"); // LOW,MEDIUM
        if (Objects.isNull(prices)) {
            return ServerResponse.badRequest().bodyValue("prices query parameter is required");
        }
        final var priceList = Arrays.stream(prices.get().split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .map(PriceEnum::valueOf)
                .toList();

        final var fluxResponse = this.restaurantBusinessService.readByPriceRangeIn(priceList);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, RestaurantResponse.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getRestaurantByCity(ServerRequest serverRequest) {
        final var city = serverRequest.queryParam("city");
        if (Objects.isNull(city)) {
            return ServerResponse.badRequest().bodyValue("city query parameter is required");
        }

        final var fluxResponse = this.restaurantBusinessService.readByCity(city.orElse(null));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, RestaurantResponse.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
