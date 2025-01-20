package com.prueba.franquicia.domain.response;

public class FranchiseResponse {
    private Long franchiseId;
    private String franchiseName;

    public FranchiseResponse() {}

    public FranchiseResponse(Long franchiseId, String franchiseName) {
        this.franchiseId = franchiseId;
        this.franchiseName = franchiseName;
    }
}
