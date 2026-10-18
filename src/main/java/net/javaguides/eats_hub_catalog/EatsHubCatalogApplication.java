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

		System.out.println("=== STARTING RESERVATION INSERT TESTS ===\n");

		final var parrillaModernaID = UUID.fromString("0ee619ba-e95f-4103-99f7-ee9cdf831d90");
		final var cafeNostalgiaID = UUID.fromString("be33011c-13dd-45b9-a60e-e9adb8f4e022");


		final var sarahReservation = createTestReservation(
				parrillaModernaID,
				"Sarah Johnson",
				4,
				"2025-06-15",
				"19:30",
				"Window table preferred"
		);

		final var michaelReservation = createTestReservation(
				parrillaModernaID,
				"Michael Davis",
				2,
				"2025-06-16",
				"20:00",
				"Anniversary dinner - romantic table"
		);

		final var emmaReservation = createTestReservation(
				cafeNostalgiaID,
				"Emma Wilson",
				6,
				"2025-06-17",
				"18:00",
				"Family birthday celebration"
		);

		final var sarahReservationCreated = reservationCrudService.createReservation(sarahReservation)
				.block();

		System.out.println("Sarah reservation: " + sarahReservationCreated.getId());

		final var michaelReservationCreated = reservationCrudService.createReservation(michaelReservation)
				.block();
		System.out.println("Michael reservation: " + michaelReservationCreated.getId());

		final var emmaReservationCreated = reservationCrudService.createReservation(emmaReservation)
				.block();
		System.out.println("Emma reservation: " + emmaReservationCreated.getId());

		System.out.println("=== FINISHED RESERVATION INSERT TESTS ===");

		System.out.println("=== INIT RESERVATION UPDATE TESTS ===");

		final var michaelReservationToUpdate = reservationCrudService.readById(michaelReservationCreated.getId()).block();

		michaelReservationToUpdate.setDate("20:30");
		michaelReservationToUpdate.setPartySize(3);

		final var michaelReservationUpdated = this.reservationCrudService.updateReservation(michaelReservationToUpdate.getId(), michaelReservationToUpdate).block();

		System.out.println("michael reservation updated: " + michaelReservationUpdated.getDate());
		System.out.println("michael reservation updated: " + michaelReservationUpdated.getPartySize());

		System.out.println("=== FINISHED RESERVATION INSERT TESTS ===");


		Thread.sleep(60000);
		System.out.println("=== INIT RESERVATION DELETE TESTS ===");
		this.reservationCrudService.deleteReservation(michaelReservationCreated.getId()).block();
		System.out.println("=== FINISHED RESERVATION DELETE TESTS ===");


	}

	private ReservationCollection createTestReservation(UUID restaurantId, String customerName,
                                                        int partySize, String date, String time, String notes) {
		return ReservationCollection.builder()
				.id(UUID.randomUUID())
				.restaurantId(restaurantId)
				.customerName(customerName)
				.partySize(partySize)
				.date(date)
				.time(time)
				.notes(notes)
				.build();
	}

}
