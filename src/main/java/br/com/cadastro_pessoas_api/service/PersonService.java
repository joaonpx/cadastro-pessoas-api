package br.com.cadastro_pessoas_api.service;

import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.model.NaturalPerson;
import br.com.cadastro_pessoas_api.model.Person;
import br.com.cadastro_pessoas_api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private NaturalPersonService naturalPersonService;

  @Autowired
  private LegalPersonService legalPersonService;

  public Person save(PersonRequestDTO personRequestDTO) {
    if (!personRequestDTO.isValid()) {
      throw new RuntimeException("Dados inválidos para o tipo de pessoa: " + personRequestDTO.type());
    }

    if (personRequestDTO.type().equalsIgnoreCase("PF")) {
      return naturalPersonService.save(personRequestDTO);
    } else if (personRequestDTO.type().equalsIgnoreCase("PJ")) {
      return legalPersonService.save(personRequestDTO);
    } else {
      throw new RuntimeException("Tipo de pessoa inválido!");
    }
  }

  public void delete(Long id) {
    Person targetPerson = findById(id);

    if (targetPerson.getType().equalsIgnoreCase("PF")) {
      naturalPersonService.delete(id);
    } else if (targetPerson.getType().equalsIgnoreCase("PJ")) {
      legalPersonService.delete(id);
    }
  }

  public Person getById(Long id) {
    Person targetPerson = findById(id);

    if (targetPerson.getType().equalsIgnoreCase("PF")) {
      return naturalPersonService.findById(id);
    } else if (targetPerson.getType().equalsIgnoreCase("PJ")) {
      return legalPersonService.findById(id);
    } else {
      throw new RuntimeException("Pessoa não encontrada.");
    }
  }

  public Person getByName(String name) {
    Person targetPerson = findByName(name);

    if (targetPerson.getType().equalsIgnoreCase("PF")) {
      return naturalPersonService.findByName(name);
    } else if (targetPerson.getType().equalsIgnoreCase("PJ")) {
      return legalPersonService.findByName(name);
    } else {
      throw new RuntimeException("Pessoa não encontrada.");
    }
  }

  public Person getByDocument(String type, String document) {
    if (type.equalsIgnoreCase("PF")) {
      return naturalPersonService.findByCpf(document);
    } else if (type.equalsIgnoreCase("PJ")) {
      return legalPersonService.findByCnpj("document");
    } else {
      throw new RuntimeException("Tipo de pessoa inválido!");
    }
  }

  public Page<Person> getAll(String type, Pageable pageable) {
    if (type == null || type.isEmpty()) {
      return personRepository.findAll(pageable);
    } else if (type.equalsIgnoreCase("PF") || type.equalsIgnoreCase("PJ")) {
      return personRepository.findByType(type, pageable);
    } else {
      throw new RuntimeException("Tipo de pessoa inválido!");
    }
  }

  public Page<Person> getAllSorted(Pageable pageable) {
    return personRepository.findAll(pageable);
  }

  private Person findById(Long id) {
    return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada."));
  }

  private Person findByName(String name) {
    return personRepository.findByName(name).orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));
  }
}
