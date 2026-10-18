package net.javaguides.eats_hub_catalog.service.impls;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.eats_hub_catalog.collections.RestaurantCollection;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import net.javaguides.eats_hub_catalog.records.Address;
import net.javaguides.eats_hub_catalog.repository.RestaurantRepository;
import net.javaguides.eats_hub_catalog.service.definitions.RestaurantCatalogService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * RestaurantCatalogServiceImpl
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 17/10/2026 - 17:14
 * @since 1.25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantCatalogServiceImpl implements RestaurantCatalogService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Flux<RestaurantCollection> readAll() {
        return this.restaurantRepository.findAll()
                .doOnSubscribe(subscription -> log.info("📤 - Reading all restaurants"))
                .doOnNext(restaurant -> log.info("✅ - Found restaurant: {}", restaurant.getName()))
                .doOnComplete(() -> log.info("✅✅ - Completed reading all restaurants"))
                .onErrorResume(throwable -> {
                    log.error("❌ - Error reading all restaurants", throwable);
                    return Flux.empty();
                });
    }

    @Override
    public Flux<RestaurantCollection> readByCuisineType(final String cuisineType) {
        return this.restaurantRepository.findByCuisineType(cuisineType)
                .doOnSubscribe(subscription -> log.info("📤 - Reading restaurants by cuisine type: {}", cuisineType))
                .doOnNext(restaurant -> log.info("✅ - Found restaurant: {}", restaurant.getName()))
                .doOnComplete(() -> log.info("✅✅ - Completed reading restaurants by cuisine type: {}", cuisineType))
                .distinct().onErrorResume(throwable -> {
                    log.error("❌ - Error reading restaurants by cuisine type: {}", cuisineType, throwable);
                    return Flux.empty();
                });
    }

    @Override
    public Mono<RestaurantCollection> readByName(final String name) {
        return this.restaurantRepository.findByNameStartingWithIgnoreCase(name)
                .doOnSubscribe(subscription -> log.info("📤 - Reading restaurants by start with name: {}", name))
                .doOnNext(restaurant -> log.info("✅ - Found restaurant: {}", restaurant.getName()))
                .onErrorResume(throwable -> {
                    log.error("❌ - Error reading restaurants by name: {}", name, throwable);
                    return Mono.empty();
                });
    }

    @Override
    public Flux<RestaurantCollection> readByPriceRange(final List<PriceEnum> priceRange) {
        return this.restaurantRepository.findByPriceRangeIn(priceRange)
                .doOnSubscribe(subscription -> log.info("📤 - Reading restaurants by price range: {}", priceRange))
                .doOnNext(restaurant -> log.info("✅ - Found restaurant: {}", restaurant.getName()))
                .doOnComplete(() -> log.info("✅✅ - Completed reading restaurants by price range: {}", priceRange))
                .switchIfEmpty(Flux.empty().cast(RestaurantCollection.class)
                        .doOnSubscribe(subscription -> log.info("⚠️ - No restaurants found for price range: {}", priceRange)));
    }

    @Override
    public Flux<RestaurantCollection> readByCity(final String city) {
        return this.restaurantRepository.findAll()
                .map(restaurant -> restaurant.getAddress())
                .filter(Objects::nonNull)
                .map(Address::city)
                .filter(Objects::nonNull)
                .distinct()
                .collectList()
                .flatMapMany(cities -> {

                    if (cities.isEmpty()) {
                        log.info("⚠️ - No cities found in the restaurant collection.");
                        return Flux.empty();
                    }

                    log.info("ℹ️ - Found cities in the restaurant collection: {}. Init search in city {}", cities, city);

                    return this.restaurantRepository.findByAddressCity(city)
                            .doOnNext(restaurant -> log.info("✅ - Found restaurant: {}", restaurant.getName()));
                })
                .doOnComplete(() -> log.info("✅✅ - Completed reading restaurants by city: {}", city))
                .onErrorResume(throwable -> {
                    log.error("❌ - Error reading restaurants by city: {}", city, throwable);
                    return Flux.empty();
                });
    }
    
}
