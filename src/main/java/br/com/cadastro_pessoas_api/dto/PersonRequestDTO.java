package br.com.cadastro_pessoas_api.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PersonRequestDTO(
        @NotNull(message = "Nome é obrigatório!")
        String name,

        @NotNull(message = "Tipo é obrigatório!")
        @Pattern(regexp = "^(PF|PJ)$", message = "Tipo deve ser PF ou PJ!")
        String type,

        String cpf,
        LocalDate dateOfBirth,
        String cnpj
) {
  public boolean isValid() {
    if (type.equalsIgnoreCase("PF")) {
      return cpf != null
              && dateOfBirth != null
              && !cpf.isEmpty()
              && !dateOfBirth.toString().isEmpty()
              && cnpj == null;
    } else if (type.equalsIgnoreCase("PJ")) {
      return cpf == null
              && dateOfBirth == null
              && cnpj != null
              && !cnpj.isEmpty();
    }
    return false;
  }
}
