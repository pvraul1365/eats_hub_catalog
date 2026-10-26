package net.javaguides.eats_hub_catalog.service.impls;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.eats_hub_catalog.dtos.request.ReservationRequest;
import net.javaguides.eats_hub_catalog.dtos.responses.ReservationResponse;
import net.javaguides.eats_hub_catalog.enums.ReservationStatusEnum;
import net.javaguides.eats_hub_catalog.mapper.ReservationMapper;
import net.javaguides.eats_hub_catalog.repository.ReservationRepository;
import net.javaguides.eats_hub_catalog.repository.RestaurantRepository;
import net.javaguides.eats_hub_catalog.service.definitions.ReservationBusinessService;
import net.javaguides.eats_hub_catalog.service.definitions.ReservationCrudService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ReservationBusinessServiceImpl
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 25/10/2026 - 10:49
 * @since 1.25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationBusinessServiceImpl implements ReservationBusinessService {

    private final ReservationCrudService reservationCrudService;
    private final ReservationMapper reservationMapper;

    @Override
    public Mono<String> createReservation(final ReservationRequest reservation) {
        log.info("🔍 In ReservationBusinessServiceImpl createReservation");

        return Mono.just(reservation)
                .transform(reservationMapper::toMonoReservationCollection)
                .flatMap(reservationCrudService::createReservation)
                .map(createdReservation -> {
                    log.info("✅ - In ReservationBusinessServiceImpl createReservation - Completed");
                    return createdReservation.getId().toString();
                });
    }

    @Override
    public Mono<ReservationResponse> readByReservationId(final UUID reservationId) {
        log.info("🔍 In ReservationBusinessServiceImpl readByReservationId");

        return this.reservationCrudService.readById(reservationId)
                .transform(this.reservationMapper::toMonoReservationResponse)
                .doOnSuccess(response -> {;
                    if (response != null) {
                        log.info("✅ - In ReservationBusinessServiceImpl readByReservationId - Completed");
                    } else {
                        log.info("⚠️ - In ReservationBusinessServiceImpl readByReservationId - No reservation found for ID: {}", reservationId);
                    }
                });
    }

    @Override
    public Flux<ReservationResponse> readByRestaurantId(final UUID restaurantId, final ReservationStatusEnum status) {
        log.info("🔍 In ReservationBusinessServiceImpl readByRestaurantId");

        return this.reservationCrudService.readByRestaurantId(restaurantId, status)
                .transform(this.reservationMapper::toFluxReservationResponse)
                .doOnComplete(() -> log.info("✅ - In ReservationBusinessServiceImpl readByRestaurantId - Completed"));
    }

    @Override
    public Mono<ReservationResponse> updateReservation(final UUID reservationId, final ReservationRequest reservation) {
        return null;
    }

    @Override
    public Mono<Void> deleteReservation(final UUID reservationId) {
        return null;
    }
}
