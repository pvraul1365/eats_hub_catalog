package net.javaguides.eats_hub_catalog.collections;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.eats_hub_catalog.dtos.Review;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import net.javaguides.eats_hub_catalog.records.Address;
import net.javaguides.eats_hub_catalog.records.ContactInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * RestaurantCollection
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 17/10/2026 - 11:43
 * @since 1.25
 */
@Document(collection = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantCollection {

    @Id
    private UUID id;

    @Indexed
    private String name;

    private int capacity;
    private Address address;

    @Indexed
    private String cuisineType;

    @Indexed
    private PriceEnum priceRange;
    private String openHours;
    private String logoUrl;
    private ContactInfo contactInfo;

    private List<Review> reviews;

}
