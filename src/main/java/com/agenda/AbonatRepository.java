package com.agenda;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbonatRepository extends JpaRepository<Abonat, Long> {

	List<Abonat> findByNumeStartsWithIgnoreCase(String nume);
}

