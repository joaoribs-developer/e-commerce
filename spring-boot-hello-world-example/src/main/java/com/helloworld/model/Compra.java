package com.helloworld.model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Garante que o valor será gerado automaticamente
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
