package repository;

import model.SolicitacaoViagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoViagemRepository extends JpaRepository<SolicitacaoViagem, Long> {
}