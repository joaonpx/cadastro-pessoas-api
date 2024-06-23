package br.com.cadastro_pessoas_api.controller;

import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PersonController {
  @Autowired
  private PersonService personService;

  @PostMapping
  public ResponseEntity<Object> addPerson(@RequestBody @Valid PersonRequestDTO person) {
    return ResponseEntity.ok(personService.save(person));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePerson(@PathVariable Long id) {
    personService.delete(id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getPersonById(@PathVariable Long id) {
    return ResponseEntity.ok(personService.getById(id));
  }
}