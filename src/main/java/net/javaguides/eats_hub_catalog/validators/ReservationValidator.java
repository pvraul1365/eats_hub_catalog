package net.javaguides.eats_hub_catalog.validators;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.eats_hub_catalog.clients.PlannerMSClient;
import net.javaguides.eats_hub_catalog.collections.ReservationCollection;
import net.javaguides.eats_hub_catalog.collections.RestaurantCollection;
import net.javaguides.eats_hub_catalog.exception.BusinessException;
import net.javaguides.eats_hub_catalog.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * ReservationValidator
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 19/10/2026 - 17:07
 * @since 1.25
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ReservationValidator {

    private final RestaurantRepository restaurantRepository;
    private final PlannerMSClient plannerMSClient;

    public<T>Mono<Void> applyValidations(T input, List<BusinessValidator<T>> validations) {
        // Implement your validation logic here
        log.info("🔍 - Validating reservation input: {}", input);

        if (Objects.isNull(validations)) {
            return Mono.empty();
        }

        return validations.stream()
                .reduce(
                  Mono.empty(),
                        (chain, validator) -> chain.then(validator.validate(input)),
                        Mono::then
                );
    }

    public BusinessValidator<ReservationCollection> validateRestaurantClosed() {
        return reservation -> {
            final var restaurantId = reservation.getRestaurantId();

            return this.restaurantRepository.findById(restaurantId)
                    .switchIfEmpty(Mono.error(new BusinessException("❌ Restaurant not found with id: " + restaurantId)))
                    .flatMap(restaurant -> {
                        if (isRestaurantClosed(restaurant, reservation.getTime())) {
                            return Mono.error(new BusinessException("❌ Restaurant is closed at the requested time: " + reservation.getTime()));
                        }

                        return Mono.empty();
                    });
        };
    }

    public BusinessValidator<ReservationCollection> validateAvalaibility() {
        log.info("🔍 - Validating restaurant availability for reservation");

        return reservation -> {
            return this.plannerMSClient.verifyAvailability(reservation.getDate(), reservation.getTime(), reservation.getRestaurantId().toString())
                    .flatMap(isAvailable -> {
                        if (!isAvailable) {
                            return Mono.error(new BusinessException("❌ Restaurant is not available at the requested date and time: " + reservation.getDate() + " " + reservation.getTime()));
                        }
                        return Mono.empty();
                    });
        };
    }

    public BusinessValidator<ReservationCollection> validateRestaurantIDBeforeUpdate() {
        log.info("🔍 - Validating restaurant ID before updating reservation");

        return reservation -> {
            return this.restaurantRepository.findById(reservation.getRestaurantId())
                    .switchIfEmpty(Mono.error(new BusinessException("❌ Restaurant not found with id: " + reservation.getRestaurantId())))
                    .flatMap(restaurant -> {
                        if (!restaurant.getId().equals(reservation.getRestaurantId())) {
                            return Mono.error(new BusinessException("❌ Restaurant ID does not match the reservation's restaurant ID"));
                        }
                        return Mono.empty();
                    });
        };
    }

    private boolean isRestaurantClosed(RestaurantCollection restaurant, String reservationTime) {
        log.info("🔍 - Checking if restaurant is closed for restaurantId: {} at time: {}", restaurant.getId(), reservationTime);
        try {
            if (Objects.isNull(restaurant.getCloseAt()) || Objects.isNull(reservationTime)) {
                return true;
            }

            LocalTime closeLocalTime = LocalTime.parse(restaurant.getCloseAt(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime reservationLocalTime = LocalTime.parse(reservationTime, DateTimeFormatter.ofPattern("HH:mm"));

            return reservationLocalTime.isAfter(closeLocalTime);



        } catch (Exception e) {
            log.error("Error checking if restaurant is closed", e);
            return true;
        }
    }

}
