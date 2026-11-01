package net.javaguides.eats_hub_catalog.routes;

import net.javaguides.eats_hub_catalog.handler.RestaurantCatalogHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * RestaurantCatalogRouter
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 01/11/2026 - 09:40
 * @since 1.25
 */
@Configuration
public class RestaurantCatalogRouter {

    private final static String BY_NAME_URL = "/{name}";

    @Bean
    public RouterFunction<ServerResponse> restaurantCatalogRoutes(RestaurantCatalogHandler restaurantCatalogHandler) {

        return route()
                .path("/restaurants", builder -> builder
                        .GET("", request -> {
                            if (request.queryParam("cousinType").isPresent()) {
                                return restaurantCatalogHandler.getRestaurantByCousinType(request);
                            } else if (request.queryParam("prices").isPresent()) {
                                return restaurantCatalogHandler.getRestaurantBetweenPrice(request);
                            } else if (request.queryParam("city").isPresent()) {
                                return restaurantCatalogHandler.getRestaurantByCity(request);
                            } else {
                                return restaurantCatalogHandler.getAllRestaurants(request);
                            }
                        })
                        .GET(BY_NAME_URL, restaurantCatalogHandler::getRestaurantByName)
                )
                .build();
    }

}
