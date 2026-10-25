package net.javaguides.eats_hub_catalog.service.definitions;

import java.util.UUID;
import net.javaguides.eats_hub_catalog.dtos.request.ReservationRequest;
import net.javaguides.eats_hub_catalog.dtos.responses.ReservationResponse;
import net.javaguides.eats_hub_catalog.enums.ReservationStatusEnum;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ReservationBusinessService
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 25/10/2026 - 10:39
 * @since 1.25
 */
public interface ReservationBusinessService {

    Mono<String> createReservation(ReservationRequest reservation);

    Mono<ReservationResponse> readByReservationId(UUID reservationId);

    Flux<ReservationResponse> readByRestaurantId(UUID restaurantId, ReservationStatusEnum status);

    Mono<ReservationResponse> updateReservation(UUID reservationId, ReservationRequest reservation);

    Mono<Void> deleteReservation(UUID reservationId);
}
