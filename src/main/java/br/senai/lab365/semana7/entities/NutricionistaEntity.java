package br.senai.lab365.semana7.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Nutricionista")
public class NutricionistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CRN;
    private String especialidade;

}
