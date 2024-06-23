package br.com.cadastro_pessoas_api.service;

import br.com.cadastro_pessoas_api.dto.PersonRequestDTO;
import br.com.cadastro_pessoas_api.model.NaturalPerson;
import br.com.cadastro_pessoas_api.repository.NaturalPersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    return naturalPersonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa física não encontrada."));
  }

  public NaturalPerson findByName(String name) {
    return naturalPersonRepository.findByName(name).orElseThrow(() -> new RuntimeException("Pessoa física não encontrada."));
  }

  public NaturalPerson findByCpf(String cpf) {
    return naturalPersonRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Pessoa física não encontrada."));
  }

  public Page<NaturalPerson> getAllSortedByDateOfBirth(Pageable pageable) {
    return naturalPersonRepository.findAll(pageable);
  }

  private NaturalPerson convertToNaturalPerson(PersonRequestDTO personRequestDTO) {
    NaturalPerson naturalPerson = new NaturalPerson();
    BeanUtils.copyProperties(personRequestDTO, naturalPerson);

    return naturalPerson;
  }
}

