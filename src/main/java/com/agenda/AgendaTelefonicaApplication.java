package com.agenda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgendaTelefonicaApplication {

	private static final Logger log = LoggerFactory.getLogger(AgendaTelefonicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AgendaTelefonicaApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(AbonatRepository repository) {
		return (args) -> {
			// save a couple of abonats
			repository.save(new Abonat("Turtoi", "Adrian", 1810715100185L, new NrFix("0722.17.74.17"),
					new NrMobil("021.0722.17.74.17")));
			repository.save(new Abonat("Costica", "Cornel", 1810715340185L, new NrFix("0722.17.74.17"),
					new NrMobil("021.0722.17.74.17")));

			// fetch all abonats
			log.info("Abonats found with findAll():");
			log.info("-------------------------------");
			for (Abonat abonat : repository.findAll()) {
				log.info(abonat.toString());
			}
			log.info("");

			// fetch an individual abonat by ID
			Abonat abonat = repository.findOne(1810715100185L);
			log.info("Abonat found with findOne(1L):");
			log.info("--------------------------------");
			log.info(abonat.toString()); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			log.info("");

			// fetch abonats by last name
			log.info("Abonat found with findByLastNameStartsWithIgnoreCase('Turtoi'):");
			log.info("--------------------------------------------");
			for (Abonat bauer : repository.findByNumeStartsWithIgnoreCase("Turtoi")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}

}
