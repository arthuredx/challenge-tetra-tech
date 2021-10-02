package com.ms.apiibge.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe para objetos do tipo PopulacaoDto
 *
 * @author Arthur Edson
 * @version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode
public class PopulacaoDto {

    private String localidade;
    private String horario;
    private ProjecaoDto projecao;
}
