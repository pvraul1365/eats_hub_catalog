package net.javaguides.eats_hub_catalog;

import net.javaguides.eats_hub_catalog.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EatsHubCatalogApplication implements CommandLineRunner {

	private final RestaurantRepository restaurantRepository;

	public EatsHubCatalogApplication(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(EatsHubCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Code to run on application startup
		this.restaurantRepository.findAll()
				.doOnNext(System.out::println)
				.subscribe();
	}

}
