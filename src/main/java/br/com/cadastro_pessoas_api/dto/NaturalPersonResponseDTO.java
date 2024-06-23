package br.com.cadastro_pessoas_api.dto;

import br.com.cadastro_pessoas_api.model.NaturalPerson;

import java.time.LocalDate;

public record NaturalPersonResponseDTO(
        Long id,
        String name,
        String type,
        String cpf,
        LocalDate dateOfBirth
) {
  public NaturalPersonResponseDTO(NaturalPerson naturalPerson) {
    this(naturalPerson.getId(), naturalPerson.getName(), naturalPerson.getType(), naturalPerson.getCpf(), naturalPerson.getDateOfBirth());
  }
}
