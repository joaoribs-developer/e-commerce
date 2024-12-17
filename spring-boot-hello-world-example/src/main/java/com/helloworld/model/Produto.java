package com.helloworld.model;

import javax.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Garante que o valor será gerado automaticamente
    private Long id;
    private String nome;
    private String descricao;
    @Column(name = "tamanho_volume")
    private String tamanhoVolume;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanhoVolume() {
        return tamanhoVolume;
    }

    public void setTamanhoVolume(String tamanhoVolume) {
        this.tamanhoVolume = tamanhoVolume;
    }
}
