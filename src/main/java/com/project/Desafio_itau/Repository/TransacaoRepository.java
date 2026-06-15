package com.project.Desafio_itau.Repository;

import com.project.Desafio_itau.Model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface TransacaoRepository  extends JpaRepository<Transacoes, UUID> {
    List<Transacoes> findByDataHoraGreaterThanEqual(
            OffsetDateTime dataHora
    );
}
