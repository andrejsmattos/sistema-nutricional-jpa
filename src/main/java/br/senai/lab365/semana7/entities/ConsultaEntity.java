package br.senai.lab365.semana7.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Nutricionista nutricionista;
    @ManyToOne
    private Paciente paciente;
    private LocalDate dataConsulta;
    private String observacoes;

}
