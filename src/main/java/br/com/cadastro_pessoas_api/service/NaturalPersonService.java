package br.com.cadastro_pessoas_api.service;

import br.com.cadastro_pessoas_api.dto.NaturalPersonRequestDTO;
import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.model.NaturalPerson;
import br.com.cadastro_pessoas_api.repository.NaturalPersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaturalPersonService {
  @Autowired
  private NaturalPersonRepository naturalPersonRepository;

  public NaturalPerson save(PersonRequestDTO personRequestDTO) {
    NaturalPerson naturalPerson = new NaturalPerson();
    BeanUtils.copyProperties(personRequestDTO, naturalPerson);

    naturalPerson.setCpf(personRequestDTO.cpf());
    naturalPerson.setDateOfBirth(personRequestDTO.dateOfBirth());

    return naturalPersonRepository.save(naturalPerson);
  }

}

