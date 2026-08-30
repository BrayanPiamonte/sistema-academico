package co.edu.uptc.sistema_academico.dto;

import java.util.List;

public class EstudiantePageResponse {

    private List<EstudianteResponse> data;
    private PaginationResponse pagination;

    public EstudiantePageResponse() {
    }

    public EstudiantePageResponse(
            List<EstudianteResponse> data,
            PaginationResponse pagination) {

        this.data = data;
        this.pagination = pagination;
    }

    public List<EstudianteResponse> getData() {
        return data;
    }

    public PaginationResponse getPagination() {
        return pagination;
    }
}