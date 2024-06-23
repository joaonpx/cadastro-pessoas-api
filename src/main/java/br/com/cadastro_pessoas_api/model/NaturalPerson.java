package br.com.cadastro_pessoas_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "TBL_NATURAL_PERSONS")
@Getter
@Setter
@ToString
@PrimaryKeyJoinColumn(name = "person_id")
@NoArgsConstructor
public class NaturalPerson extends Person {
  private String cpf;

  @Column(name = "date_of_birth")
  LocalDate dateOfBirth;
}