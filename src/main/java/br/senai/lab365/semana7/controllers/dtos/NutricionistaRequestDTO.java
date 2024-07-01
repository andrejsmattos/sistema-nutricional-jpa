package br.senai.lab365.semana7.controllers.dtos;

import lombok.Data;

@Data
public class NutricionistaRequestDTO extends FuncionarioRequestDTO {

    private String nome;
    private String crn;
    private String especialidade;
}
