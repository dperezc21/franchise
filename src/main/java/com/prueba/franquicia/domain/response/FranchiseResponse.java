package com.prueba.franquicia.domain.response;

public class FranchiseResponse {
    private Long franchiseId;
    private String franchiseName;

    public FranchiseResponse() {}

    public FranchiseResponse(Long franchiseId, String franchiseName) {
        this.franchiseId = franchiseId;
        this.franchiseName = franchiseName;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }

    public Long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(Long franchiseId) {
        this.franchiseId = franchiseId;
    }
}
