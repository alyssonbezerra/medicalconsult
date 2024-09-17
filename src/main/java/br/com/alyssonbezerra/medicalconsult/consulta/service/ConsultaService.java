package br.com.alyssonbezerra.medicalconsult.consulta.service;

import br.com.alyssonbezerra.medicalconsult.consulta.domain.Consulta;
import br.com.alyssonbezerra.medicalconsult.consulta.repository.ConsultaRepository;
import br.com.alyssonbezerra.medicalconsult.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConsultaService {



    @Autowired
    public ConsultaRepository repository;




    public Consulta cadastarConsulta(Consulta consulta){
        consulta.setDataConsulta(null);
        return repository.save(consulta);
    }
    public List<Consulta> listarConsulta() {
        return repository.findAll();
    }

    public Consulta buscarConsulta(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado! ID: " + id)
        );
    }

    public void deleteConsulta(Long id) {
        Consulta consulta = buscarConsulta(id);
        repository.delete(consulta);
    }

    public Consulta atualizarConsulta(Long id, Consulta consulta) {
        Consulta upConsultaExistente = buscarConsulta(id);



        return repository.save(upConsultaExistente);
    }
}
