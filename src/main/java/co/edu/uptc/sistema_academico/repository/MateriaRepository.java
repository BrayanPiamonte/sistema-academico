package co.edu.uptc.sistema_academico.repository;

import co.edu.uptc.sistema_academico.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
}