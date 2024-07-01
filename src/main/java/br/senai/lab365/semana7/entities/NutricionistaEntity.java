package br.senai.lab365.semana7.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "Nutricionista")
@EqualsAndHashCode(callSuper = true)
public class NutricionistaEntity extends FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crn;
    private String especialidade;
    private String certificacoes = "";
}
