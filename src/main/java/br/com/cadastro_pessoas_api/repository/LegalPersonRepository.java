package br.com.cadastro_pessoas_api.repository;

import br.com.cadastro_pessoas_api.model.LegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LegalPersonRepository extends JpaRepository<LegalPerson, Long> {
  Optional<LegalPerson> findByCnpj(String cnpj);
  Optional<LegalPerson> findByName(String name);
}
