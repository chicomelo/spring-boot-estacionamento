package br.com.estacionamento.Estacionamento.model.vo;

import br.com.estacionamento.Estacionamento.model.Role;

public class UsuarioVO {

    private final Long id;
    private final String nome;
    private final String email;
    private final Role role;

    public UsuarioVO(Long id, String nome, String email, Role role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
