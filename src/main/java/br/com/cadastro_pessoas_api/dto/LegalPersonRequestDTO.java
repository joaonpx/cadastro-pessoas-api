package br.com.cadastro_pessoas_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LegalPersonRequestDTO(
        @NotNull(message = "Nome é obrigatório!")
        String name,

        @NotNull(message = "Tipo é obrigatório!")
        @Pattern(regexp = "^(PJ)$", message = "Tipo deve ser PJ")
        String type,

        @NotBlank(message = "CNPJ é obrigatório!")
        String cnpj
) {

}
