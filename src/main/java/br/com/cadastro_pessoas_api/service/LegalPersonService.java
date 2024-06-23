package br.com.cadastro_pessoas_api.service;

import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.model.LegalPerson;
import br.com.cadastro_pessoas_api.repository.LegalPersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalPersonService {
  @Autowired
  private LegalPersonRepository legalPersonRepository;

  public LegalPerson save(PersonRequestDTO personRequestDTO) {
    LegalPerson legalPerson = new LegalPerson();
    BeanUtils.copyProperties(personRequestDTO, legalPerson);

    legalPerson.setCnpj(personRequestDTO.cnpj());

    return legalPersonRepository.save(legalPerson);
  }
}
