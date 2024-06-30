package br.senai.lab365.semana7.controllers.dtos;

import lombok.Data;

@Data
public class FuncionarioRequestDTO {

    private String matricula;
    private int tempoExperiencia;
    private Long enderecoId;
}
