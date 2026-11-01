package net.javaguides.eats_hub_catalog;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.javaguides.eats_hub_catalog.collections.ReservationCollection;
import net.javaguides.eats_hub_catalog.collections.RestaurantCollection;
import net.javaguides.eats_hub_catalog.dtos.Review;
import net.javaguides.eats_hub_catalog.dtos.responses.ReservationResponse;
import net.javaguides.eats_hub_catalog.dtos.responses.RestaurantResponse;
import net.javaguides.eats_hub_catalog.enums.PriceEnum;
import net.javaguides.eats_hub_catalog.enums.ReservationStatusEnum;
import net.javaguides.eats_hub_catalog.mapper.ReservationMapper;
import net.javaguides.eats_hub_catalog.mapper.RestaurantMapper;
import net.javaguides.eats_hub_catalog.records.Address;
import net.javaguides.eats_hub_catalog.records.ContactInfo;
import net.javaguides.eats_hub_catalog.repository.ReservationRepository;
import net.javaguides.eats_hub_catalog.repository.RestaurantRepository;
import net.javaguides.eats_hub_catalog.service.definitions.ReservationCrudService;
import net.javaguides.eats_hub_catalog.service.definitions.RestaurantCatalogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RequiredArgsConstructor
public class EatsHubCatalogApplication implements CommandLineRunner {

	private final ReservationCrudService reservationCrudService;
	private final ReservationRepository reservationRepository;

	private final ReservationMapper reservationMapper;
	private final RestaurantMapper restaurantMapper;

	public static void main(String[] args) {
		SpringApplication.run(EatsHubCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
