package br.senai.lab365.semana7.repositories;

import br.senai.lab365.semana7.entities.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
}
