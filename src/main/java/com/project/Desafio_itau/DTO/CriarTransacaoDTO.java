package com.project.Desafio_itau.DTO;

import java.time.OffsetDateTime;

public record CriarTransacaoDTO(
        Double valor,
        OffsetDateTime dataHora
) {
}
