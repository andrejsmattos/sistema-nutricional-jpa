package br.senai.lab365.semana7.controllers.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultaRequestDTO {

    private Long nutricionistaId;
    private Long pacienteId;
    private LocalDate dataConsulta;
    private String observacoes;
}
