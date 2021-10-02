package com.ms.apiibge.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe para objetos do tipo ProjecaoDto
 *
 * @author Arthur Edson
 * @version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode
public class ProjecaoDto {

    private Long populacao;
    private PeriodoMedioDto periodoMedio;
}
