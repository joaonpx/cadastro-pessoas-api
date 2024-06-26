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
    return legalPersonRepository.save(convertToLegalPerson(personRequestDTO));
  }

  public void delete(Long id) {
    legalPersonRepository.delete(findById(id));
  }

  public LegalPerson findById(Long id) {
    return legalPersonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa jurídica não encontrada."));
  }

  public LegalPerson findByName(String name) {
    return legalPersonRepository.findByName(name).orElseThrow(() -> new RuntimeException("Pessoa jurídica não encontrada."));
  }

  public LegalPerson findByCnpj(String cnpj) {
    return legalPersonRepository.findByCnpj(cnpj).orElseThrow(() -> new RuntimeException("Pessoa física não encontrada."));
  }

  private LegalPerson convertToLegalPerson(PersonRequestDTO personRequestDTO) {
    LegalPerson legalPerson = new LegalPerson();
    BeanUtils.copyProperties(personRequestDTO, legalPerson);

    return legalPerson;
  }
}
