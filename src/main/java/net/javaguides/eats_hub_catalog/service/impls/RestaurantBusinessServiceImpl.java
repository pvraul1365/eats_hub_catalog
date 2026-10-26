package net.javaguides.eats_hub_catalog.service.impls;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.eats_hub_catalog.dtos.responses.RestaurantResponse;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import net.javaguides.eats_hub_catalog.mapper.RestaurantMapper;
import net.javaguides.eats_hub_catalog.repository.RestaurantRepository;
import net.javaguides.eats_hub_catalog.service.definitions.RestaurantBusinessService;
import net.javaguides.eats_hub_catalog.service.definitions.RestaurantCatalogService;
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

    private final RestaurantCatalogService restaurantCatalogService;
    private final RestaurantMapper restaurantMapper;

    @Override
    public Flux<RestaurantResponse> readAll() {
        log.info("🔍 In RestaurantBusinessServiceImpl readAll");

        return this.restaurantCatalogService.readAll()
                .transform(restaurantMapper::toFluxRestaurantResponse)
                .doOnComplete(() -> log.info("✅ - In RestaurantBusinessServiceImpl readAll - Completed"));
    }

    @Override
    public Flux<RestaurantResponse> readByCuisineType(final String cuisine) {
        log.info("🔍 In RestaurantBusinessServiceImpl readByCuisineType");

        return this.restaurantCatalogService.readByCuisineType(cuisine)
                .transform(restaurantMapper::toFluxRestaurantResponse)
                .doOnComplete(() -> log.info("✅ - In RestaurantBusinessServiceImpl readByCuisineType - Completed"));
    }

    @Override
    public Mono<RestaurantResponse> readByName(final String name) {
        log.info("🔍 In RestaurantBusinessServiceImpl readByName");

        return this.restaurantCatalogService.readByName(name)
                .transform(restaurantMapper::toMonoRestaurantResponse)
                .doOnSuccess(response -> {
                    if (Objects.nonNull(response)) {
                        log.info("✅ - In RestaurantBusinessServiceImpl readByName - Completed");
                    } else {
                        log.warn("⚠️ - In RestaurantBusinessServiceImpl readByName - No restaurant found with name: {}", name);
                    }
                });
    }

    @Override
    public Flux<RestaurantResponse> readByPriceRangeIn(final List<PriceEnum> priceRanges) {
        log.info("🔍 In RestaurantBusinessServiceImpl readByPriceRangeIn");

        return this.restaurantCatalogService.readByPriceRange(priceRanges)
                .transform(restaurantMapper::toFluxRestaurantResponse)
                .doOnComplete(() -> log.info("✅ - In RestaurantBusinessServiceImpl readByPriceRangeIn - Completed"));
    }

    @Override
    public Flux<RestaurantResponse> readByCity(final String city) {
        log.info("🔍 In RestaurantBusinessServiceImpl readByCity");

        return this.restaurantCatalogService.readByCity(city)
                .transform(restaurantMapper::toFluxRestaurantResponse)
                .doOnComplete(() -> log.info("✅ - In RestaurantBusinessServiceImpl readByCity - Completed"));
    }
}
