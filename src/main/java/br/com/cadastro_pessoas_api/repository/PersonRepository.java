package br.com.cadastro_pessoas_api.repository;

import br.com.cadastro_pessoas_api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
