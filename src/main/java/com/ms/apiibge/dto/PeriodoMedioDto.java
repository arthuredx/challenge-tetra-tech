package com.ms.apiibge.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe para objetos do tipo PeriodoMedioDto
 *
 * @author Arthur Edson
 * @version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode
public class PeriodoMedioDto {

    private Long incrementoPopulacional;
    private Long nascimento;
    private Long obito;

}
