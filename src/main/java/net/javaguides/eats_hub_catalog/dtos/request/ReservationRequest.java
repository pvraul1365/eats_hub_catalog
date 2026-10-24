package net.javaguides.eats_hub_catalog.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ReservationRequest
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 24/10/2026 - 20:49
 * @since 1.25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {

    private String restaurantId;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String dateTime; // example 2025-06-16,15:30
    private Integer partySize;
    private String comment;

}
