package net.javaguides.eats_hub_catalog.clients;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * PlannerMSClient
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 19/10/2026 - 11:00
 * @since 1.25
 */
@Component
@Slf4j
public class PlannerMSClient {

    private final String UNAVAILABLE_RESTAURANT_ID = "dfcbe98d-392b-4b93-9a49-27005223d15d";

    public Mono<Boolean> verifyAvailability(final String date, final String time, final String restaurantId) {
        log.info("🔎 - Verifying availability for restaurantId: {}", restaurantId);

        return Mono.fromCallable(() -> !UNAVAILABLE_RESTAURANT_ID.equals(restaurantId))
                .delayElement(this.getRandomDuration())
                .doOnNext(available -> {
                    if (available) {
                        log.info("✅ - Restaurant is available for date: {}, time: {}, restaurantId: {}", date, time, restaurantId);
                    } else {
                        log.warn("❌ - Restaurant is NOT available for date: {}, time: {}, restaurantId: {}", date, time, restaurantId);
                    }
                });
    }

    private Duration getRandomDuration() {
        final var randomInt = ThreadLocalRandom.current().nextInt(20, 1000);

        return Duration.ofMillis(randomInt);
    }

}
