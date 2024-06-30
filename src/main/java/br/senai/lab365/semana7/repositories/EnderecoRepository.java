package br.senai.lab365.semana7.repositories;

import br.senai.lab365.semana7.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
