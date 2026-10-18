package net.javaguides.eats_hub_catalog.service.impls;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.eats_hub_catalog.collections.ReservationCollection;
import net.javaguides.eats_hub_catalog.enums.ReservationStatusEnum;
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
    public Mono<ReservationCollection> createReservation(ReservationCollection reservationCollection) {
        return null;
    }

    @Override
    public Flux<ReservationCollection> readByRestaurantId(String restaurantId, ReservationStatusEnum status) {
        return null;
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
