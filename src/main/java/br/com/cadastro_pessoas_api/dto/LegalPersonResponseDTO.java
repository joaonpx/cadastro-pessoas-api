package br.com.cadastro_pessoas_api.dto;


import br.com.cadastro_pessoas_api.model.LegalPerson;

public record LegalPersonResponseDTO(
        Long id,
        String name,
        String type,
        String cnpj
) {
  public LegalPersonResponseDTO(LegalPerson legalPerson) {
    this(legalPerson.getId(), legalPerson.getName(), legalPerson.getType(), legalPerson.getCnpj());
  }
}
