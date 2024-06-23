package br.com.cadastro_pessoas_api.dto;

public record PersonResponseDTO(
        Long id,
        String name,
        String type
) {
    public PersonResponseDTO(PersonRequestDTO personRequestDTO) {
        this(personRequestDTO.id(), personRequestDTO.name(), personRequestDTO.type());
    }
}
