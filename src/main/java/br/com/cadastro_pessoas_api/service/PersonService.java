package br.com.cadastro_pessoas_api.service;

import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.dto.PersonResponseDTO;
import br.com.cadastro_pessoas_api.model.LegalPerson;
import br.com.cadastro_pessoas_api.model.NaturalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
  @Autowired
  private NaturalPersonService naturalPersonService;

  @Autowired
  private LegalPersonService legalPersonService;

  public PersonResponseDTO save(PersonRequestDTO personRequestDTO) {
    if (!personRequestDTO.isValid()) {
      throw new RuntimeException("Dados inválidos para o tipo de pessoa informado.");
    }

    if (personRequestDTO.type().equals("PF")) {
      NaturalPerson savedNaturalPerson = naturalPersonService.save(personRequestDTO);

      return new PersonResponseDTO(savedNaturalPerson.getId(), savedNaturalPerson.getName(), savedNaturalPerson.getType());
    } else if (personRequestDTO.type().equals("PJ")) {

      LegalPerson legalPerson = legalPersonService.save(personRequestDTO);
      return new PersonResponseDTO(legalPerson.getId(), legalPerson.getName(), legalPerson.getType());

    } else {
      throw new RuntimeException("Tipo de pessoa inválido!");
    }
  }
}
