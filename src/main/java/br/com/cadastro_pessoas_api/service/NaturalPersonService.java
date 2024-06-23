package br.com.cadastro_pessoas_api.service;

import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.model.NaturalPerson;
import br.com.cadastro_pessoas_api.repository.NaturalPersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NaturalPersonService {
  @Autowired
  private NaturalPersonRepository naturalPersonRepository;

  public NaturalPerson save(PersonRequestDTO personRequestDTO) {
    return naturalPersonRepository.save(convertToNaturalPerson(personRequestDTO));
  }

  public void delete(Long id) {
      naturalPersonRepository.delete(findById(id));
  }

  public NaturalPerson findById(Long id) {
    return naturalPersonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa Física não encontrada com o ID: " + id));
  }

  private NaturalPerson convertToNaturalPerson(PersonRequestDTO personRequestDTO) {
    NaturalPerson naturalPerson = new NaturalPerson();
    BeanUtils.copyProperties(personRequestDTO, naturalPerson);

    naturalPerson.setCpf(personRequestDTO.cpf());
    naturalPerson.setDateOfBirth(personRequestDTO.dateOfBirth());

    return naturalPerson;
  }
}

