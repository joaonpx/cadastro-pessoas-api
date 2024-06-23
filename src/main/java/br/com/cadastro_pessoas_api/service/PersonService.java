package br.com.cadastro_pessoas_api.service;

import br.com.cadastro_pessoas_api.dto.LegalPersonResponseDTO;
import br.com.cadastro_pessoas_api.dto.NaturalPersonResponseDTO;
import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.dto.PersonResponseDTO;
import br.com.cadastro_pessoas_api.model.LegalPerson;
import br.com.cadastro_pessoas_api.model.NaturalPerson;
import br.com.cadastro_pessoas_api.model.Person;
import br.com.cadastro_pessoas_api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private NaturalPersonService naturalPersonService;

  @Autowired
  private LegalPersonService legalPersonService;

  public Object save(PersonRequestDTO personRequestDTO) {
    if (!personRequestDTO.isValid()) {
      throw new RuntimeException("Dados inválidos para o tipo de pessoa: " + personRequestDTO.type());
    }

    if (personRequestDTO.type().equals("PF")) {
      NaturalPerson savedNaturalPerson = naturalPersonService.save(personRequestDTO);

      return new NaturalPersonResponseDTO(savedNaturalPerson);
    } else if (personRequestDTO.type().equals("PJ")) {
      LegalPerson savedLegalPerson = legalPersonService.save(personRequestDTO);

      return new LegalPersonResponseDTO(savedLegalPerson);
    } else {
      throw new RuntimeException("Tipo de pessoa inválido!");
    }
  }

  public void delete(Long id) {
    Person targetPerson = findById(id);

    if (targetPerson.getType().equals("PF")) {
      naturalPersonService.delete(id);
    } else if (targetPerson.getType().equals("PJ")) {
      legalPersonService.delete(id);
    }
  }

  public Object getById(Long id) {
    Person targetPerson = findById(id);

    if (targetPerson.getType().equals("PF")) {
      NaturalPerson targetNaturalPerson = naturalPersonService.findById(id);

      return new NaturalPersonResponseDTO(targetNaturalPerson);
    } else if (targetPerson.getType().equals("PJ")) {
      LegalPerson targetLegalPerson = legalPersonService.findById(id);

      return new LegalPersonResponseDTO(targetLegalPerson);
    } else {
      throw new RuntimeException("Pessoa não encontrada.");
    }
  }

  private Person findById(Long id) {
    return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + id));
  }
}
