package repository;

import model.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
}