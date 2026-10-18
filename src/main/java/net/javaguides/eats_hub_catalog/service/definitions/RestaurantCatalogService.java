package net.javaguides.eats_hub_catalog.service.definitions;

import java.util.List;
import net.javaguides.eats_hub_catalog.collections.RestaurantCollection;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * RestaurantCatalogService
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 17/10/2026 - 17:10
 * @since 1.25
 */
public interface RestaurantCatalogService {

    Flux<RestaurantCollection> readAll();

    Flux<RestaurantCollection> readByCuisineType(String cuisineType);

    Mono<RestaurantCollection> readByName(String name);

    Flux<RestaurantCollection> readByPriceRange(List<PriceEnum> priceRange);

    Flux<RestaurantCollection> readByCity(String city);

}
