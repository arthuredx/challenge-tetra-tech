package com.ms.apiibge.service;

import Util.ConsumirApi;
import com.ms.apiibge.dto.PopulacaoDto;
import lombok.extern.java.Log;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Classe responsável pela lógica negocial.
 *
 * @author Arthur Edson
 * @version 1.0
 */
@Service
@Log
public class ApiService {

    private final String fileName = "/logs/log-api-ibge.txt";

    /**
     * Método para buscar dados na API pública do IBGE e logar os dados em arquivo txt.
     *
     * @return Optional<PopulacaoDto>
     * @author Arthur Edson
     */
    public Optional<PopulacaoDto> buscar() {

        PopulacaoDto populacao = ConsumirApi.buscarInformacoesIbge();

        Optional<PopulacaoDto> opt = Optional.ofNullable(populacao);

        if (opt.isPresent()) {
            logaDados(populacao);
        }

        return opt;
    }

    /**
     * Método para incluir arquivo de Log ou popular.
     *
     * @param populacao Objeto de retorno da API do IBGE
     * @author Arthur Edson
     */
    private void logaDados(PopulacaoDto populacao) {
        File file = new File(fileName);
        LocalDateTime dataChamada = LocalDateTime.now();
        String dados = "{" + "Data Hora Chamada: " + dataChamada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " Data Hora Projeção: " + populacao.getHorario() + " População Estimada: " + populacao.getProjecao().getPopulacao().toString() + "}";
        if (!file.exists()) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(fileName);
            } catch (IOException e) {
                log.warning("Ocorreu algum erro ao gravar o arquivo");
                e.printStackTrace();
            }

            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dados);
            printWriter.close();
        } else {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(fileName, true));
                writer.append("\n" + dados);
                writer.close();
            } catch (IOException e) {
                log.warning("Ocorreu algum erro ao reescrever o arquivo");
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para buscar dados na API pública do IBGE e logar dados em arquivo txt.
     *
     * @param localidade Identificador da localidade. Use o código BR ou 0 para referir-se ao Brasil.
     * @author Arthur Edson
     */
    public Optional<PopulacaoDto> buscarPorLocalidade(String localidade) {

        PopulacaoDto populacao = null;

        if (!Objects.isNull(localidade.trim())) {
            populacao = ConsumirApi.buscarInformacoesIbgePorLocalidade(localidade);
        }

        Optional<PopulacaoDto> opt = Optional.ofNullable(populacao);

        if (opt.isPresent()) {
            logaDados(populacao);
        }

        return opt;
    }

    /**
     * Método utilizado para buscar as últimas chamadas que foram logadas.
     *
     * @author Arthur Edson
     */
    public List<String> buscaUltimasChamadas() {

        List<String> lines = readLastLine(new File(fileName), 10);

        return lines;

    }

    /**
     * Método Apache Commons IO ReversedLinesFileReader para ler as últimas linhas do arquivo de Log.
     *
     * @author Arthur Edson
     */
    public List<String> readLastLine(File file, int numLastLineToRead) {

        List<String> result = new ArrayList<>();

        try (ReversedLinesFileReader reader = new ReversedLinesFileReader(file, StandardCharsets.UTF_8)) {

            String line = "";
            while ((line = reader.readLine()) != null && result.size() < numLastLineToRead) {
                result.add(line);
            }

        } catch (IOException e) {
            log.warning("Ocorreu algum erro ao ler o arquivo");
            e.printStackTrace();
        }

        return result;

    }

}
