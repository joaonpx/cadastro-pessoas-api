package br.com.cadastro_pessoas_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TBL_LEGAL_PERSONS")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "person_id")
public class LegalPerson extends Person {
  private String cnpj;
}
