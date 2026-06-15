package com.project.Desafio_itau.DTO;

public record PegarEstatisticasDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {
}
