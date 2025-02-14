package br.com.devjoaopedro.helpdesk.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Departamentos disponíveis no sistema. Valores permitidos: TECHNOLOGY, ACCOUNTING, LEGAL, COMMERCIAL, FINANCIAL")
public enum Department {

    @Schema(description = "Setor de tecnologia (TECHNOLOGY)")
    TECHNOLOGY,

    @Schema(description = "Setor de contabilidade (ACCOUNTING)")
    ACCOUNTING,

    @Schema(description = "Setor jurídico (LEGAL)")
    LEGAL,

    @Schema(description = "Setor comercial (COMMERCIAL)")
    COMMERCIAL,

    @Schema(description = "Setor financeiro (FINANCIAL)")
    FINANCIAL
}
