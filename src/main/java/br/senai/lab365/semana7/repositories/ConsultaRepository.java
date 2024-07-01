package br.senai.lab365.semana7.repositories;

import br.senai.lab365.semana7.entities.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

}
