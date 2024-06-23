package br.com.cadastro_pessoas_api.dto;

import br.com.cadastro_pessoas_api.model.NaturalPerson;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record NaturalPersonRequestDTO(
        @NotNull(message = "Nome é obrigatório!")
        String name,

        @NotNull(message = "Tipo é obrigatório!")
        @Pattern(regexp = "^(PF)$", message = "Tipo deve ser PF!")
        String type,

        @NotNull(message = "CPF é obrigatório!")
        String cpf,

        @NotNull(message = "Data de nascimento é obrigatório!")
        @Past(message = "A data de nascimento deve estar no passado!")
        LocalDate dateOfBirth
) {
}
