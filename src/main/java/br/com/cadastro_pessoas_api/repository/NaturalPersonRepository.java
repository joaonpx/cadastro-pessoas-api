package br.com.cadastro_pessoas_api.repository;

import br.com.cadastro_pessoas_api.model.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, Long> {
  Optional<NaturalPerson> findByCpf(String cpf);
  Optional<NaturalPerson> findByName(String name);
}
