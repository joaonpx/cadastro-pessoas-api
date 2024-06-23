package br.com.cadastro_pessoas_api.controller;

import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.dto.PersonResponseDTO;
import br.com.cadastro_pessoas_api.model.LegalPerson;
import br.com.cadastro_pessoas_api.model.NaturalPerson;
import br.com.cadastro_pessoas_api.model.Person;
import br.com.cadastro_pessoas_api.service.LegalPersonService;
import br.com.cadastro_pessoas_api.service.NaturalPersonService;
import br.com.cadastro_pessoas_api.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/pessoas")
public class PersonController {
  @Autowired
  private PersonService personService;

  @PostMapping
  public PersonResponseDTO addPerson(@RequestBody @Valid PersonRequestDTO person) {
    return personService.save(person);
  }
}