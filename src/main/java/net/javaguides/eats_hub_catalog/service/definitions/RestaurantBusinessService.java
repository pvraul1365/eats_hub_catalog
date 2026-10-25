package net.javaguides.eats_hub_catalog.service.definitions;

import java.util.List;
import net.javaguides.eats_hub_catalog.dtos.responses.RestaurantResponse;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * RestaurantBusinessService
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 25/10/2026 - 10:44
 * @since 1.25
 */
public interface RestaurantBusinessService {

    Flux<RestaurantResponse> readAll();

    Flux<RestaurantResponse> readByCuisineType(String cuisine);

    Mono<RestaurantResponse> readByName(String name);

    Flux<RestaurantResponse> readByPriceRangeIn(List<PriceEnum> priceRanges);

    Flux<RestaurantResponse> readByCity(String city);
}
