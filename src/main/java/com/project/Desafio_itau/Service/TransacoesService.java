package com.project.Desafio_itau.Service;

import com.project.Desafio_itau.DTO.CriarTransacaoDTO;
import com.project.Desafio_itau.DTO.PegarEstatisticasDTO;
import com.project.Desafio_itau.Exceptions.DataNoFuturoException;
import com.project.Desafio_itau.Exceptions.ValorNegativoException;
import com.project.Desafio_itau.Model.Transacoes;
import com.project.Desafio_itau.Repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class TransacoesService {

    private final TransacaoRepository transacaoRepository;

    public TransacoesService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public CriarTransacaoDTO criarTransacao(CriarTransacaoDTO criarTransacaoDTO) {

        OffsetDateTime dataHora = OffsetDateTime.now(ZoneOffset.UTC);
        if (criarTransacaoDTO.valor() == null || criarTransacaoDTO.dataHora() == null) {
            throw new RuntimeException("A API não compreendeu a requisição do cliente (por exemplo: um JSON inválido");
        }

        if (criarTransacaoDTO.valor() < 0) {
            throw new ValorNegativoException("A transação não foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram atendidos - por exemplo: uma transação com valor menor que 0)");
        }

        if (criarTransacaoDTO.dataHora().isAfter(dataHora)) {
            throw new DataNoFuturoException("A transação não foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram atendidos - por exemplo: uma transação com valor menor que 0)");
        }

        Transacoes transacoes = new Transacoes();
        transacoes.setValor(criarTransacaoDTO.valor());
        transacoes.setDataHora(criarTransacaoDTO.dataHora());
        transacaoRepository.save(transacoes);
        return criarTransacaoDTO;
    }

    public void ApagarTransacoes() {
        transacaoRepository.deleteAll();
    }

    public PegarEstatisticasDTO pegarEstatisticas() {

        OffsetDateTime now =
                OffsetDateTime.now(
                        ZoneId.of("America/Sao_Paulo")
                );
        OffsetDateTime sessentaSegundos =
                now.minusSeconds(60);

        List<Transacoes> transacoes =
                transacaoRepository.findAll();

        List<Transacoes> filtradas =
                transacoes.stream()
                        .filter(t ->
                                !t.getDataHora()
                                        .isBefore(sessentaSegundos)
                        )
                        .toList();

        filtradas.forEach(t ->
                System.out.println(t.getDataHora())
        );

        if (filtradas.isEmpty()) {
            return new PegarEstatisticasDTO(
                    0L,
                    0.0,
                    0.0,
                    0.0,
                    0.0
            );
        }

        DoubleSummaryStatistics stats =
                filtradas.stream()
                        .mapToDouble(Transacoes::getValor)
                        .summaryStatistics();

        return new PegarEstatisticasDTO(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }}
