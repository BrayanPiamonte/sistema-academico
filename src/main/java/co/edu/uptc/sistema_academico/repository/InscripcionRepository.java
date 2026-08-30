package co.edu.uptc.sistema_academico.repository;

import co.edu.uptc.sistema_academico.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}