package net.javaguides.eats_hub_catalog.service.definitions;

import java.util.UUID;
import net.javaguides.eats_hub_catalog.collections.ReservationCollection;
import net.javaguides.eats_hub_catalog.enums.ReservationStatusEnum;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ReservationCrudService
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 18/10/2026 - 10:44
 * @since 1.25
 */
public interface ReservationCrudService {

    Mono<ReservationCollection> createReservation(ReservationCollection reservationCollection);

    Mono<ReservationCollection> readById(UUID reservationId);

    Flux<ReservationCollection> readByRestaurantId(String restaurantId, ReservationStatusEnum status);

    Mono<ReservationCollection> updateReservation(UUID reservationId, ReservationCollection reservation);

    Mono<Void> deleteReservation(UUID reservationId);
}
