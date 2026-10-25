package net.javaguides.eats_hub_catalog.service.impls;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.eats_hub_catalog.dtos.responses.RestaurantResponse;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import net.javaguides.eats_hub_catalog.mapper.RestaurantMapper;
import net.javaguides.eats_hub_catalog.repository.RestaurantRepository;
import net.javaguides.eats_hub_catalog.service.definitions.RestaurantBusinessService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * RestaurantBusinessServiceImpl
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 25/10/2026 - 10:48
 * @since 1.25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantBusinessServiceImpl implements RestaurantBusinessService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public Flux<RestaurantResponse> readAll() {
        return null;
    }

    @Override
    public Flux<RestaurantResponse> readByCuisineType(String cuisine) {
        return null;
    }

    @Override
    public Mono<RestaurantResponse> readByName(String name) {
        return null;
    }

    @Override
    public Flux<RestaurantResponse> readByPriceRangeIn(List<PriceEnum> priceRanges) {
        return null;
    }

    @Override
    public Flux<RestaurantResponse> readByCity(String city) {
        return null;
    }
}
