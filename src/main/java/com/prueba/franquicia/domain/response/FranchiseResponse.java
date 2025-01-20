package com.prueba.franquicia.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseResponse {
    private Long franchiseId;
    private String franchiseName;
}
