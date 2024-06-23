package br.com.cadastro_pessoas_api.repository;

import br.com.cadastro_pessoas_api.model.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, Long> {
}
