package co.edu.uptc.sistema_academico.dto;

import java.util.List;

public class MateriaPageResponse {

    private List<MateriaResponse> data;
    private PaginationResponse pagination;

    public MateriaPageResponse() {
    }

    public MateriaPageResponse(
            List<MateriaResponse> data,
            PaginationResponse pagination) {

        this.data = data;
        this.pagination = pagination;
    }

    public List<MateriaResponse> getData() {
        return data;
    }

    public PaginationResponse getPagination() {
        return pagination;
    }
}