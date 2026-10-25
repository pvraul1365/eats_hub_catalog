package net.javaguides.eats_hub_catalog.mapper;

import java.util.List;
import java.util.Objects;
import net.javaguides.eats_hub_catalog.collections.RestaurantCollection;
import net.javaguides.eats_hub_catalog.dtos.Review;
import net.javaguides.eats_hub_catalog.dtos.responses.RestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * RestaurantMapper
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 25/10/2026 - 09:36
 * @since 1.25
 */
@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "globalRating", expression = "java(calculateGlobalRaing(restaurantCollection.getReviews()))")
    RestaurantResponse toRestaurantResponse(RestaurantCollection restaurantCollection);

    default Flux<RestaurantResponse> toFluxRestaurantResponse(Flux<RestaurantCollection> restaurantCollections){
        return restaurantCollections.map(this::toRestaurantResponse);
    }

    default Mono<RestaurantResponse> toMonoRestaurantResponse(RestaurantCollection restaurantCollection){
        return Mono.just(toRestaurantResponse(restaurantCollection));
    }

    default Double calculateGlobalRaing(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        return reviews.stream()
                .filter(review -> Objects.nonNull(review.getRating()))
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

}
