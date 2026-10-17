package net.javaguides.eats_hub_catalog.records;

import lombok.Builder;

/**
 * Address
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 17/10/2026 - 10:20
 * @since 1.25
 */
@Builder
public record Address (
        String street,
        String city,
        String postalCode
) {
}
