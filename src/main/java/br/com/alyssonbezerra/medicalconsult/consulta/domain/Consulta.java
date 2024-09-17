package br.com.alyssonbezerra.medicalconsult.consulta.domain;

import br.com.alyssonbezerra.medicalconsult.usuario.domain.Permissao;
import br.com.alyssonbezerra.medicalconsult.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "CONSULTAS")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONSULTA")
    private long idConsulta;
    @Column(name = "DATA_CONSULTA")
    private String dataConsulta;
    @Column(name = "PROFISSIONAL")
    private String profissional;
    @Column(name = "ESPECIALIDADE")
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Consulta(long idConsulta, String dataConsulta, String profissional, Usuario usuario) {
        this.idConsulta = idConsulta;
    }
}
