package com.helloworld.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import javax.persistence.JoinColumn;
import javax.persistence.Column;



@Entity
public class Compra {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "estoqueid")
    private Estoque estoque;
    @Column(name = "userid")
    private Integer userId;
    private String status;
    @Column(name = "data_compra")
    private LocalDateTime dataCompra;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }
}
