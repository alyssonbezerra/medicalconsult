package br.com.alyssonbezerra.medicalconsult.usuario.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Permission;
import java.util.Date;

@Data
@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private long idUsuario;
    @Column(name = "NOME_USUARIO")
    private String nomeUsuario;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;
    @Column(name = "PERMISAO")
    private Permissao permissao;

    public Usuario(long idUsuario, String nomeUsuario, String email, String cpf, String telefone, Date dataNascimento, Permissao permissao){
       this.idUsuario = idUsuario;
       this.nomeUsuario = nomeUsuario;
       this.email = email;
       this.cpf = cpf;
       this.telefone = telefone;
       this.dataNascimento = dataNascimento;
       this.permissao = permissao;
    }
    public Usuario(){

    }

}
