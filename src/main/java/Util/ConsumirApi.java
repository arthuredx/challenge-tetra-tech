package Util;

import com.ms.apiibge.dto.PopulacaoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Classe para consultas de API's externas.
 *
 * @author Arthur Edson
 */
public class ConsumirApi {

    /**
     * Método para buscar dados na API pública do IBGE.
     *
     * @return PopulacaoDto
     * @author Arthur Edson
     */
    public static PopulacaoDto buscarInformacoesIbge() {

        RestTemplate template = new RestTemplate();
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("servicodados.ibge.gov.br")
                .path("api/v1/projecoes/populacao")
                .build();


        ResponseEntity<PopulacaoDto> entity = template.getForEntity(uri.toUriString(), PopulacaoDto.class);

        return entity.getBody();
    }

    /**
     * Método para buscar dados na API pública do IBGE utilizando como parâmetro a localidade
     *
     * @param localidade Identificador da localidade. Use o código BR ou 0 para referir-se ao Brasil.
     * @return PopulacaoDto
     * @author Arthur Edson
     */
    public static PopulacaoDto buscarInformacoesIbgePorLocalidade(String localidade) {

        RestTemplate template = new RestTemplate();
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("servicodados.ibge.gov.br")
                .path("api/v1/projecoes/populacao/" + localidade)
                .build();


        ResponseEntity<PopulacaoDto> entity = template.getForEntity(uri.toUriString(), PopulacaoDto.class);

        return entity.getBody();
    }
}
