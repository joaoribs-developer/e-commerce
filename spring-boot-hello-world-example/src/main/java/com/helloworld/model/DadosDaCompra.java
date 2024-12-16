package com.helloworld.model;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity(name = "Dados_Da_Compra")
public class DadosDaCompra {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "compraid")
    private Compra compra;
    @Column(name = "formapagamento")
    private String formaPagamento;
    private LocalDateTime dataEntrega;
    @Column(name = "enderecoid")
    private Integer enderecoId;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Integer getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Integer enderecoId) {
        this.enderecoId = enderecoId;
    }
}
