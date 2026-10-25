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

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public Mono<String> createReservation(ReservationRequest reservation) {
        return null;
    }

    @Override
    public Mono<ReservationResponse> readByReservationId(UUID reservationId) {
        return null;
    }

    @Override
    public Flux<ReservationResponse> readByRestaurantId(UUID restaurantId, ReservationStatusEnum status) {
        return null;
    }

    @Override
    public Mono<ReservationResponse> updateReservation(UUID reservationId, ReservationRequest reservation) {
        return null;
    }

    @Override
    public Mono<Void> deleteReservation(UUID reservationId) {
        return null;
    }
}
