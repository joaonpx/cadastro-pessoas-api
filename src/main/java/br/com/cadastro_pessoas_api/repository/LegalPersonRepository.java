package br.com.cadastro_pessoas_api.repository;

import br.com.cadastro_pessoas_api.model.LegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegalPersonRepository extends JpaRepository<LegalPerson, Long> {
}
