package br.com.alyssonbezerra.medicalconsult.consulta.service;

import br.com.alyssonbezerra.medicalconsult.consulta.domain.Consulta;
import br.com.alyssonbezerra.medicalconsult.consulta.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    public Consulta cadastrarConsulta(Consulta consulta) {
        return repository.save(consulta);  // Mantém a data da consulta como foi passada
    }

    public List<Consulta> listarConsultas() {
        return repository.findAll();
    }

    public Consulta buscarConsulta(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada! ID: " + id));
    }

    public void deletarConsulta(Long id) {
        buscarConsulta(id);  // Verifica se a consulta existe
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir, porque há entidades relacionadas.");
        }
    }

    public Consulta atualizarConsulta(Consulta consulta) {
        Consulta consultaAtualizada = buscarConsulta(consulta.getIdConsulta());
        atualizarDados(consultaAtualizada, consulta);
        return repository.save(consultaAtualizada);
    }

    private void atualizarDados(Consulta consultaAtualizada, Consulta consulta) {
        consultaAtualizada.setDataConsulta(consulta.getDataConsulta());
        consultaAtualizada.setProfissional(consulta.getProfissional());
        consultaAtualizada.setEspecialidade(consulta.getEspecialidade());
    }
}
