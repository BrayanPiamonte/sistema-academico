package co.edu.uptc.sistema_academico.Controller;

import co.edu.uptc.sistema_academico.dto.MateriaPageResponse;
import co.edu.uptc.sistema_academico.entity.Materia;
import co.edu.uptc.sistema_academico.service.MateriaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import co.edu.uptc.sistema_academico.dto.MateriaSortField;
import co.edu.uptc.sistema_academico.dto.SortDirection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/materias")
@Tag(
    name = "Materias",
    description = "Operaciones CRUD para la gestión de materias académicas"
)
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }
   
@GetMapping
@Operation(
        summary = "Consultar materias",
        description = "Obtiene las materias mediante paginación y ordenamiento."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Consulta realizada correctamente"
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Parámetros de consulta inválidos"
        )
})
public MateriaPageResponse obtenerMaterias(

        @RequestParam(defaultValue = "1")
        @Min(1)
        int pageNumber,

        @RequestParam(defaultValue = "20")
        @Min(1)
        @Max(100)
        int pageSize,

        @RequestParam
        MateriaSortField sortBy,

        @RequestParam
        SortDirection sortDirection) {

    return materiaService.buscarMaterias(
            pageNumber,
            pageSize,
            sortBy,
            sortDirection
    );
    }


    @Operation(
        summary = "Consultar materia por ID",
        description = "Obtiene una materia específica a partir de su identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Materia encontrada"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No existe una materia con el ID indicado"
        )
    })
    @GetMapping("/{id}")
    public Materia obtenerPorId(@PathVariable Long id) {
        return materiaService.obtenerPorId(id);
    }

    @Operation(
        summary = "Crear materia",
        description = "Registra una nueva materia en el sistema académico."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Materia creada correctamente"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Los datos enviados no cumplen las validaciones"
        )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Materia crear(@Valid @RequestBody Materia materia) {
        return materiaService.guardar(materia);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar materia",
        description = "Reemplaza completamente los datos de una materia existente."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Materia actualizada correctamente"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Los datos enviados no son válidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No existe la materia indicada"
        )
    })
    public Materia actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Materia materia) {

        return materiaService.actualizar(id, materia);
    }

@Operation(
    summary = "Actualizar parcialmente una materia",
    description = "Modifica únicamente los campos enviados de la materia."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Materia actualizada correctamente"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No existe la materia indicada"
    )
})
@PatchMapping("/{id}")
public Materia actualizarParcialmente(
        @PathVariable Long id,
        @RequestBody Materia materia) {

    return materiaService.actualizarParcialmente(id, materia);
}

    @Operation(
    summary = "Eliminar materia",
    description = "Elimina una materia existente a partir de su identificador."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "204",
        description = "Materia eliminada correctamente"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No existe la materia indicada"
    )
})
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void eliminar(@PathVariable Long id) {
    materiaService.eliminar(id);
}
}