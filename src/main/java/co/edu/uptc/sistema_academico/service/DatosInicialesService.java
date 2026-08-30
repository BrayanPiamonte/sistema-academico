package co.edu.uptc.sistema_academico.service;

import co.edu.uptc.sistema_academico.entity.Estudiante;
import co.edu.uptc.sistema_academico.entity.Materia;
import co.edu.uptc.sistema_academico.repository.EstudianteRepository;
import co.edu.uptc.sistema_academico.repository.MateriaRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class DatosInicialesService {

    private static final int MIN_ESTUDIANTES = 1000;
    private static final int MIN_MATERIAS = 20;

    private final EstudianteRepository estudianteRepository;
    private final MateriaRepository materiaRepository;

    private final Faker faker = new Faker(new Locale("es"));

    public DatosInicialesService(
            EstudianteRepository estudianteRepository,
            MateriaRepository materiaRepository) {

        this.estudianteRepository = estudianteRepository;
        this.materiaRepository = materiaRepository;
    }

    @Transactional
    public void cargarDatosIniciales() {

        generarMaterias();
        generarEstudiantes();
    }

    private void generarMaterias() {

        long existentes = materiaRepository.count();

        int faltantes = (int) (MIN_MATERIAS - existentes);

        if (faltantes <= 0) {
            return;
        }

        List<Materia> materias = new ArrayList<>();

        for (int i = 0; i < faltantes; i++) {

            Materia materia = new Materia();

            materia.setNombre(
                    faker.educator().course()
            );

            materia.setCodigo(
                    "MAT" + (existentes + i + 1)
            );

            materia.setCreditos(
                    faker.number().numberBetween(1, 5)
            );

            materia.setSemestre(
                    faker.number().numberBetween(1, 10)
            );

            materias.add(materia);
        }

        materiaRepository.saveAll(materias);
    }

    private void generarEstudiantes() {

        long existentes = estudianteRepository.count();

        int faltantes = (int) (MIN_ESTUDIANTES - existentes);

        if (faltantes <= 0) {
            return;
        }

        List<Estudiante> estudiantes = new ArrayList<>();

        for (int i = 0; i < faltantes; i++) {

            Estudiante estudiante = new Estudiante();

            estudiante.setNombre(
                    faker.name().firstName()
            );

            estudiante.setApellido(
                    faker.name().lastName()
            );

            estudiante.setCorreo(
                    faker.internet().emailAddress()
            );

            estudiante.setFechaNacimiento(
                    faker.date()
                            .birthday(18, 30)
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
            );

            estudiante.setPrograma(
                    faker.options().option(
                            "Ingeniería de Sistemas",
                            "Ingeniería Industrial",
                            "Ingeniería Electrónica",
                            "Ingeniería Civil",
                            "Administración de Empresas"
                    )
            );

            estudiantes.add(estudiante);
        }

        estudianteRepository.saveAll(estudiantes);
    }
}