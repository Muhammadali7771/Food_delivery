package com.example.food_delivery;

import com.example.food_delivery.configuration.security.SessionUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class FoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApplication.class, args);
	}
	@Bean
	public AuditorAware<Integer> getAuditor(SessionUser sessionUser) {
		return new AuditorAware<>() {
			@Override
			public Optional<Integer> getCurrentAuditor() {
				return Optional.of(sessionUser.getId());
			}
		};
	}
}
