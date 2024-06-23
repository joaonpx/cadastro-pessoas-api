package br.com.cadastro_pessoas_api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "TBL_NATURAL_PERSONS")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "person_id")
@NoArgsConstructor
public class NaturalPerson extends Person {
  private String cpf;

  @Column(name = "date_of_birth")
  LocalDate dateOfBirth;
}