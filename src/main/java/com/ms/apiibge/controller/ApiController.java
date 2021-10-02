package com.ms.apiibge.controller;

import com.ms.apiibge.dto.PopulacaoDto;
import com.ms.apiibge.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável pelo mapeamento dos endpoints.
 *
 * @author Arthur Edson
 * @version 1.0
 */
@RestController
@RequestMapping("/ibge")
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping("/buscarEstimativa")
    public ResponseEntity<PopulacaoDto> consultar() {
        Optional<PopulacaoDto> projecao = apiService.buscar();
        if (!projecao.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<PopulacaoDto>(projecao.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/buscarEstimativa/{localidade}")
    public ResponseEntity<PopulacaoDto> consultarPorLocalidade(@PathVariable(value = "localidade") String localilidade) {
        Optional<PopulacaoDto> projecao = apiService.buscarPorLocalidade(localilidade);
        if (!projecao.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<PopulacaoDto>(projecao.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/listarUltimasChamadas")
    public ResponseEntity<List<String>> listarUltimasChamadas() {

       return new ResponseEntity<List<String>>(apiService.buscaUltimasChamadas(), HttpStatus.OK);

    }

}
