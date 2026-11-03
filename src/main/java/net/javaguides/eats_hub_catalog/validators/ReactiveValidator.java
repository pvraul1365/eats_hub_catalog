package net.javaguides.eats_hub_catalog.validators;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * ReactiveValidator
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 03/11/2026 - 14:46
 * @since 1.25
 */
@Component
@RequiredArgsConstructor
public class ReactiveValidator {

    private final Validator validator;

    public <T> Mono<T> validate(T object) {
        var violations = this.validator.validate(object);

        if (!violations.isEmpty()) {
            final var errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining());

            return Mono.error(new ValidationException(errors));
        }

        return Mono.just(object);
    }
}
