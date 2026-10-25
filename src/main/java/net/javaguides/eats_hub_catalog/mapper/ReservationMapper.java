package net.javaguides.eats_hub_catalog.mapper;

import java.util.Objects;
import jdk.jfr.MemoryAddress;
import net.javaguides.eats_hub_catalog.collections.ReservationCollection;
import net.javaguides.eats_hub_catalog.dtos.request.ReservationRequest;
import net.javaguides.eats_hub_catalog.dtos.responses.ReservationResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ReservationMapper
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 25/10/2026 - 09:48
 * @since 1.25
 */
@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "dateTime", expression = "java(reservationCollection.getDate() + \",\" + reservationCollection.getTime())")
    ReservationResponse toReservationResponse(ReservationCollection reservationCollection);

    @Mapping(target = "notes", source = "comment", defaultValue = "")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "time", ignore = true)
    ReservationCollection toReservationCollection(ReservationRequest reservationRequest);

    default Flux<ReservationResponse> toFluxReservationResponse(Flux<ReservationCollection> reservationCollections){
        return reservationCollections.map(this::toReservationResponse);
    }

    default Mono<ReservationResponse> toMonoReservationResponse(Mono<ReservationCollection> reservationCollection){
        return reservationCollection.map(this::toReservationResponse);
    }

    default Flux<ReservationCollection> toFluxReservationCollection(Flux<ReservationRequest> reservationRequestFlux){
        return reservationRequestFlux.map(this::toReservationCollection);
    }

    default Mono<ReservationCollection> toMonoReservationCollection(Mono<ReservationRequest> reservationRequestMono){
        return reservationRequestMono.map(this::toReservationCollection);
    }

    @AfterMapping
    default void splitDateTime(ReservationRequest request, @MappingTarget ReservationCollection reservationCollection) {
        if (Objects.nonNull(request.getDateTime()) && request.getDateTime().contains(",")) {
            String[] dateTimeParts = request.getDateTime().split(",");
            if (dateTimeParts.length == 2) {
                reservationCollection.setDate(dateTimeParts[0]);
                reservationCollection.setTime(dateTimeParts[1]);
            }
        } else {
            reservationCollection.setDate(null);
            reservationCollection.setTime(null);
        }
    }
}
