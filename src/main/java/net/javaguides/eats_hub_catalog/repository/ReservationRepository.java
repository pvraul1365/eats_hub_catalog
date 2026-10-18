package net.javaguides.eats_hub_catalog.repository;

import java.util.UUID;
import net.javaguides.eats_hub_catalog.collections.ReservationCollection;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * ReservationRepository
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 18/10/2026 - 10:38
 * @since 1.25
 */
public interface ReservationRepository extends ReactiveMongoRepository<ReservationCollection, UUID> {

    Flux<ReservationCollection> findByRestaurantId(String restaurantId);

    Flux<ReservationCollection> findByRestaurantIdAndStatus(String restaurantId, String status);
}
