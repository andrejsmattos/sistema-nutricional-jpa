package br.senai.lab365.semana7.controllers.dtos;

import lombok.Data;

@Data
public class EnderecoResponseDTO {

    private Long id;
    private String logradouro;
    private String estado;
    private String cidade;
    private String numero;
    private String cep;
}
