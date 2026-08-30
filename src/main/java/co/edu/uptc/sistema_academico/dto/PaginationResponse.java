package co.edu.uptc.sistema_academico.dto;

public class PaginationResponse {

    private int pageNumber;
    private int pageSize;
    private long totalRecords;
    private int totalPages;

    public PaginationResponse() {
    }

    public PaginationResponse(
            int pageNumber,
            int pageSize,
            long totalRecords,
            int totalPages) {

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.totalPages = totalPages;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }
}