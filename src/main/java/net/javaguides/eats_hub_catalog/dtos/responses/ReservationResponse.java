package net.javaguides.eats_hub_catalog.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ReservationResponse
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 24/10/2026 - 20:43
 * @since 1.25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

    private String restaurantId;
    private String customerName;
    private String dateTime; // example 2025-06-16,15:30
    private Integer partySize;

}
