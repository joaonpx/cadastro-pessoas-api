package br.com.cadastro_pessoas_api.controller;

import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.model.NaturalPerson;
import br.com.cadastro_pessoas_api.model.Person;
import br.com.cadastro_pessoas_api.service.LegalPersonService;
import br.com.cadastro_pessoas_api.service.NaturalPersonService;
import br.com.cadastro_pessoas_api.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/pessoas")
public class PersonController {
  @Autowired
  private PersonService personService;

  @Autowired
  private NaturalPersonService naturalPersonService;

  @PostMapping
  public ResponseEntity<Person> addPerson(@RequestBody @Valid PersonRequestDTO person, UriComponentsBuilder uriComponentsBuilder) {
    Person savedPerson = personService.save(person);

    var uri = uriComponentsBuilder.path("/pessoas/{id}").buildAndExpand(savedPerson.getId()).toUri();

    return ResponseEntity.created(uri).body(savedPerson);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePerson(@PathVariable Long id) {
    personService.delete(id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
    return ResponseEntity.ok(personService.getById(id));
  }

  @GetMapping("/nome")
  public ResponseEntity<Person> getPersonByName(@RequestBody Map<String, String> params) {
    return ResponseEntity.ok(personService.getByName(params.get("name")));
  }

  @GetMapping("/documento")
  public ResponseEntity<Person> getPersonByDocument(@RequestBody Map<String, String> params) {
    return ResponseEntity.ok(personService.getByDocument(params.get("type"), params.get("document")));
  }

  @GetMapping
  public ResponseEntity<Page<Person>> getAllPersons(@RequestParam(name = "type", required = false) String type, @PageableDefault(size = 5, page = 0) Pageable pageable) {
    return ResponseEntity.ok(personService.getAll(type, pageable));
  }

  @GetMapping("/nome/asc")
  public ResponseEntity<Page<Person>> getAllPersonsSortedFromAToZ(@PageableDefault(size = 5, page = 0, sort = "name") Pageable pageable) {
    return ResponseEntity.ok(personService.getAllSorted(pageable));
  }

  @GetMapping("/nome/desc")
  public ResponseEntity<Page<Person>> getAllPersonsSortedFromZToA(@PageableDefault(size = 5, page = 0, sort = "nome", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(personService.getAllSorted(pageable));
  }

  @GetMapping("/pf/ddn/asc")
  public ResponseEntity<Page<NaturalPerson>> getAllPersonsSortedByDateOfBirthAsc(@PageableDefault(size = 5, page = 0, sort = "dateOfBirth") Pageable pageable) {
    return ResponseEntity.ok(naturalPersonService.getAllSortedByDateOfBirth(pageable));
  }

  @GetMapping("/pf/ddn/desc")
  public ResponseEntity<Page<NaturalPerson>> getAllPersonsSortedByDateOfBirthDesc(@PageableDefault(size = 5, page = 0, sort = "dateOfBirth", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(naturalPersonService.getAllSortedByDateOfBirth(pageable));
  }
}