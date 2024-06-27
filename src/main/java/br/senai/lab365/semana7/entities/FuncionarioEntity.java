package br.senai.lab365.semana7.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Funcionário")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    private int tempoExperiencia;
    @ManyToOne
    private Endereco endereco;

}
