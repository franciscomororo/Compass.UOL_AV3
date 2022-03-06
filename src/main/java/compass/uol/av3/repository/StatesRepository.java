package compass.uol.av3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import compass.uol.av3.modelo.States;

@Repository
public interface StatesRepository extends JpaRepository<States, Long> {

	
}
