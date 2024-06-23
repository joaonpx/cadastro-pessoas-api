package br.com.cadastro_pessoas_api.repository;

import br.com.cadastro_pessoas_api.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
  Page<Person> findByType(String type, Pageable pageable);
  Optional<Person> findByName(String name);
}
