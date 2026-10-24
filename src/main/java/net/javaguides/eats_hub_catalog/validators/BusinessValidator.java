package net.javaguides.eats_hub_catalog.validators;

import net.javaguides.eats_hub_catalog.collections.ReservationCollection;
import reactor.core.publisher.Mono;

/**
 * BusinessValidator
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 19/10/2026 - 17:04
 * @since 1.25
 */
@FunctionalInterface
public interface BusinessValidator<T> {

    Mono<Void> validate(T input);

}
