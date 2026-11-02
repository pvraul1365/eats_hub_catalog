package net.javaguides.eats_hub_catalog.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Restaurant ID is required")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Restaurant ID must be a valid UUID")
    private String restaurantId;

    @NotBlank(message = "Customer ID is required")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Customer ID must be a valid UUID")
    private String customerId;

    @NotBlank(message = "Customer name is required")
    @Size(min = 3, max = 21, message = "Customer name must be between 3 and 21 characters")
    private String customerName;

    @NotNull(message = "Customer email is required")
    @Email(message = "Customer email must be a valid email address")
    private String customerEmail;

    @NotNull(message = "Date and time is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2},\\d{2}:\\d{2}$",
            message = "DateTime must be in format YYYY-MM-DD,HH:mm (example: 2025-06-16,15:30)")
    private String dateTime; // example 2025-06-16,15:30

    @NotNull(message = "Party size is required")
    @Min(value = 1, message = "Party size must be at least 1")
    @Max(value = 20, message = "Party size cannot exceed 20")
    private Integer partySize;

    private String comment;

}
