package br.senai.lab365.semana7.controllers.dtos;

import br.senai.lab365.semana7.entities.EnderecoEntity;
import jakarta.persistence.ManyToOne;

public class FuncionarioRequestDTO {

    private String matricula;
    private int tempoExperiencia;
    private Long enderecoId;
}
