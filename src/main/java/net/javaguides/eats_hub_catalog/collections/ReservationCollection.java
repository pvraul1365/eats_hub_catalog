package net.javaguides.eats_hub_catalog.collections;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.eats_hub_catalog.enums.ReservationStatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ReservationCollection
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 17/10/2026 - 11:41
 * @since 1.25
 */
@Document(collection = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationCollection {

    @Id
    private UUID id;

    @Indexed
    private UUID restaurantId;

    private String customerId;
    private String customerName;
    private String customerEmail;
    private String date;
    private String time;
    private Integer partySize;

    @Indexed
    private ReservationStatusEnum status;

    private String notes;

}
