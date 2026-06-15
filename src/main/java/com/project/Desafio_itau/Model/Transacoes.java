package com.project.Desafio_itau.Model;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacoes")
public class Transacoes {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double valor;

    private OffsetDateTime dataHora;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
