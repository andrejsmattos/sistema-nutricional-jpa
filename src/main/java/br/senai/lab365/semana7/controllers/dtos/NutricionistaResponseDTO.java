package br.senai.lab365.semana7.controllers.dtos;

import lombok.Data;

@Data
public class NutricionistaResponseDTO extends FuncionarioResponseDTO {

    private String crn;
    private String especialidade;
}
