package co.edu.uptc.sistema_academico.specification;

import co.edu.uptc.sistema_academico.entity.Estudiante;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstudianteSpecification {

    private EstudianteSpecification() {
    }

    public static Specification<Estudiante> idEquals(Long id) {
        return (root, query, cb) ->
                cb.equal(root.get("id"), id);
    }

    public static Specification<Estudiante> nombresContains(
            List<String> nombres) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            for (String nombre : nombres) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("nombre")),
                                "%" + nombre.toLowerCase() + "%"
                        )
                );
            }

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Estudiante> apellidosContains(
            List<String> apellidos) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            for (String apellido : apellidos) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("apellido")),
                                "%" + apellido.toLowerCase() + "%"
                        )
                );
            }

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Estudiante> correoContains(
            String correo) {

        return (root, query, cb) ->
                cb.like(
                        cb.lower(root.get("correo")),
                        "%" + correo.toLowerCase() + "%"
                );
    }

    public static Specification<Estudiante> programasIn(
            List<String> programas) {

        return (root, query, cb) -> {

            Expression<String> campo = root.get("programa");

            return campo.in(programas);
        };
    }

    public static Specification<Estudiante> fechaDesde(
            LocalDate desde) {

        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(
                        root.get("fechaNacimiento"),
                        desde
                );
    }

    public static Specification<Estudiante> fechaHasta(
            LocalDate hasta) {

        return (root, query, cb) ->
                cb.lessThanOrEqualTo(
                        root.get("fechaNacimiento"),
                        hasta
                );
    }

    public static Specification<Estudiante> search(String search) {

        return (root, query, cb) -> {

            String valor = "%" + search.toLowerCase() + "%";

            return cb.or(
                    cb.like(
                            cb.lower(root.get("nombre")),
                            valor
                    ),
                    cb.like(
                            cb.lower(root.get("apellido")),
                            valor
                    ),
                    cb.like(
                            cb.lower(root.get("correo")),
                            valor
                    )
            );
        };
    }
}