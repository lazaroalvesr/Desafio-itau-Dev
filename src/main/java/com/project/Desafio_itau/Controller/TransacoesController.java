package com.project.Desafio_itau.Controller;

import com.project.Desafio_itau.DTO.CriarTransacaoDTO;
import com.project.Desafio_itau.DTO.PegarEstatisticasDTO;
import com.project.Desafio_itau.Service.TransacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacoesController {

    @Autowired
    private TransacoesService transacoesService;

    @PostMapping("/criar")
    public ResponseEntity<String> CriarTransacao(@RequestBody CriarTransacaoDTO criarTransacaoDTO) {
        transacoesService.criarTransacao(criarTransacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("A transação foi aceita e registrada com sucesso!");
    }

    @DeleteMapping("")
    public ResponseEntity<String> DeletarTranscacos(){
        transacoesService.ApagarTransacoes();
        return ResponseEntity.status(HttpStatus.OK).body("Todas as informações foram apagadas com sucesso");
    }

    @GetMapping("/estatisticas")
    public PegarEstatisticasDTO GetEstatisticas(){
       return transacoesService.pegarEstatisticas();
    }
}
