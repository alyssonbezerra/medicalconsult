package br.com.alyssonbezerra.medicalconsult.usuario.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USAURIO")
    private long idUsuario;
    @Column(name = "NOME_USAURIO")
    private String nomeUsuario;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;
    //@Colum(name = "DATA_NASCIMENTO")
    //prvate Permissao permissao;

    public Usuario(){

    }

}
