package co.edu.uptc.sistema_academico.Controller;

import co.edu.uptc.sistema_academico.service.DatosInicialesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/datos-iniciales")
public class DatosInicialesController {

    private final DatosInicialesService datosInicialesService;

    public DatosInicialesController(
            DatosInicialesService datosInicialesService) {

        this.datosInicialesService = datosInicialesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String cargarDatosIniciales() {

        datosInicialesService.cargarDatosIniciales();

        return "Carga inicial completada correctamente";
    }
}