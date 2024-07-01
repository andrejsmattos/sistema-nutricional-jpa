package br.senai.lab365.semana7.repositories;

import br.senai.lab365.semana7.entities.NutricionistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutricionistaRepository extends JpaRepository<NutricionistaEntity, Long> {
    boolean existsByNome(String nome);
}
