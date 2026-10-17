package net.javaguides.eats_hub_catalog.repository;

import java.util.UUID;
import net.javaguides.eats_hub_catalog.collections.RestaurantCollection;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

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
}
