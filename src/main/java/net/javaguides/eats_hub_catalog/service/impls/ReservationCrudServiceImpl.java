package net.javaguides.eats_hub_catalog.service.impls;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.eats_hub_catalog.collections.ReservationCollection;
import net.javaguides.eats_hub_catalog.enums.ReservationStatusEnum;
import net.javaguides.eats_hub_catalog.exception.ResourceNotFoundException;
import net.javaguides.eats_hub_catalog.repository.ReservationRepository;
import net.javaguides.eats_hub_catalog.repository.RestaurantRepository;
import net.javaguides.eats_hub_catalog.service.definitions.ReservationCrudService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ReservationCrudServiceImpl
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 18/10/2026 - 10:49
 * @since 1.25
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationCrudServiceImpl implements ReservationCrudService {

    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Mono<ReservationCollection> createReservation(ReservationCollection reservation) {

        return this.restaurantRepository.findById(reservation.getRestaurantId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("❌ Restaurant not found with id: " + reservation.getRestaurantId())))
                .flatMap(restaurant -> {
                    if (Objects.isNull(reservation.getStatus())) {
                        reservation.setStatus(ReservationStatusEnum.PENDING);
                    }

                    log.info("✅ - Creating reservation for restaurant: {}", restaurant.getName());
                    return this.reservationRepository.save(reservation);
                });
    }

    @Override
    public Mono<ReservationCollection> readById(UUID reservationId) {
        return this.reservationRepository.findById(reservationId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("❌ Reservation not found with id: " + reservationId)));
    }

    @Override
    public Flux<ReservationCollection> readByRestaurantId(UUID restaurantId, ReservationStatusEnum status) {
        return this.restaurantRepository.findById(restaurantId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("❌ Restaurant not found with id: " + restaurantId)))
                .flatMapMany(restaurant -> {
                    if (Objects.isNull(status)) {
                        log.info("📤 - Reading all reservations for restaurant: {}", restaurant.getName());
                        return this.reservationRepository.findByRestaurantId(restaurantId.toString());
                    }

                    log.info("📤 - Reading reservations for restaurant: {} with status: {}", restaurant.getName(), status);
                    return this.reservationRepository.findByRestaurantIdAndStatus(restaurantId.toString(), status);
                });
    }

    @Override
    public Mono<ReservationCollection> updateReservation(UUID reservationId, ReservationCollection reservation) {
        return null;
    }

    @Override
    public Mono<Void> deleteReservation(UUID reservationId) {
        return null;
    }
}
