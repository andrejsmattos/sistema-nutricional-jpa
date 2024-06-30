package br.senai.lab365.semana7.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Endereço")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;
    private String estado;
    private String cidade;
    private String numero;
    private String cep;
}

