package net.javaguides.eats_hub_catalog.repository;

import java.util.List;
import java.util.UUID;
import net.javaguides.eats_hub_catalog.collections.RestaurantCollection;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * RestaurantRepository
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 17/10/2026 - 12:23
 * @since 1.25
 */
public interface RestaurantRepository extends ReactiveMongoRepository<RestaurantCollection, UUID> {

    Flux<RestaurantCollection> findByCuisineType(String cuisineType);

    // @Query("{'name': {$regex: '?0', $options: 'i'}}")
    Flux<RestaurantCollection> findByNameStartingWithIgnoreCase(String name);

    Flux<RestaurantCollection> findByPriceIn(List<PriceEnum> priceRange);

    Flux<RestaurantCollection> findByAddressCity(String city);
}
