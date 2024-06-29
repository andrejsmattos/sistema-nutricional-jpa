package br.senai.lab365.semana7.controllers.dtos;

import br.senai.lab365.semana7.entities.EnderecoEntity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteResponseDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;
    private String email;
    private Long enderecoId;
}
