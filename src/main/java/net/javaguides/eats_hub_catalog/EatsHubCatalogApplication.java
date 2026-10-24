package net.javaguides.eats_hub_catalog;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.javaguides.eats_hub_catalog.collections.ReservationCollection;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import net.javaguides.eats_hub_catalog.repository.ReservationRepository;
import net.javaguides.eats_hub_catalog.repository.RestaurantRepository;
import net.javaguides.eats_hub_catalog.service.definitions.ReservationCrudService;
import net.javaguides.eats_hub_catalog.service.definitions.RestaurantCatalogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class EatsHubCatalogApplication implements CommandLineRunner {

	private final ReservationCrudService reservationCrudService;
	private final ReservationRepository reservationRepository;

	public static void main(String[] args) {
		SpringApplication.run(EatsHubCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		final var parrillaModernaID = "0ee619ba-e95f-4103-99f7-ee9cdf831d90";
		final var unavailableID = "dfcbe98d-392b-4b93-9a49-27005223d15d";

/*
        final var michaelReservation = createTestReservation(
				parrillaModernaID,
                "Michael Davis",
                2,
                "2025-06-16",
                "19:00",
                "Anniversary dinner - romantic table"
        );

        final var michaelReservationCreated = reservationCrudService.createReservation(michaelReservation)
        .block();

        System.out.println("michaelReservationCreated: " + michaelReservationCreated.getId());
*/

		final var michaelReservationToUpdate = reservationCrudService.readById(UUID.fromString("d85fa34f-881c-480f-a008-064973f339b5")).block();

		michaelReservationToUpdate.setTime("15:30");
		michaelReservationToUpdate.setPartySize(3);

		final var michaelReservationUpdated = this.reservationCrudService.updateReservation(UUID.fromString("d85fa34f-881c-480f-a008-064973f339b5"), michaelReservationToUpdate).block();

		System.out.println("michael reservation updated: " + michaelReservationUpdated.getDate());
		System.out.println("michael reservation updated: " + michaelReservationUpdated.getPartySize());


	}

	private ReservationCollection createTestReservation(String restaurantId, String customerName,
														int partySize, String date, String time, String notes) {
		return ReservationCollection.builder()
				.id(UUID.randomUUID())
				.restaurantId(UUID.fromString(restaurantId))
				.customerName(customerName)
				.partySize(partySize)
				.date(date)
				.time(time)
				.notes(notes)
				.build();
	}
}
