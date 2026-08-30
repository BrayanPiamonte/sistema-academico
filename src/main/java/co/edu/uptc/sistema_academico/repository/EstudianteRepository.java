package co.edu.uptc.sistema_academico.repository;

import co.edu.uptc.sistema_academico.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EstudianteRepository
        extends JpaRepository<Estudiante, Long>,
                JpaSpecificationExecutor<Estudiante> {
}