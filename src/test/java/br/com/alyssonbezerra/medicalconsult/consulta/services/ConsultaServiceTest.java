package br.com.alyssonbezerra.medicalconsult.consulta.services;

import br.com.alyssonbezerra.medicalconsult.consulta.domain.Consulta;
import br.com.alyssonbezerra.medicalconsult.consulta.repository.ConsultaRepository;
import br.com.alyssonbezerra.medicalconsult.consulta.service.ConsultaService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

// Indica que o Mockito será utilizado para criar mocks no contexto dos testes
@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    // Injeta a instância real de ConsultaService, mas InjectaMocks para as dependências
    @InjectMocks
    private ConsultaService consultaService;

    // Cria um mock para o repositório ConsultaRepository
    @Mock
    private ConsultaRepository consultaRepository;

    // Testa o metodo de cadastro de consultas
    @Test
    void cadastrarConsulta(){

        // Cria uma nova consulta e define a especialidade
        Consulta consulta = new Consulta();
        consulta.setEspecialidade("Consulta de rotina");

        // Configura o mock para retornar a consulta ao salvar
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        // Executa o metodo de serviço que realiza o cadastro da consulta
        var resultado = consultaService.cadastrarConsulta(consulta);

        // Verifica se o resultado não é nulo e a especialidade é a esperada
        assertNotNull(resultado);
        assertEquals("Consulta de rotina", resultado.getEspecialidade());

        // Verifica se o metodo save foi chamado uma vez com a consulta
        verify(consultaRepository, times(1)).save(consulta);
    }
    // Testa o metodo que lista todas as consultas
    @Test
    void listarConsultas(){

        // Cria duas consultas para simular uma lista
        Consulta consulta1 = new Consulta();
        consulta1.setEspecialidade("Consulta de rotina");
        Consulta consulta2 = new Consulta();
        consulta2.setEspecialidade("Consulta de emergência");

        // Adiciona as consultas à lista
        List<Consulta> consultas = new ArrayList<>();
        consultas.add(consulta1);
        consultas.add(consulta2);

        // Configura o mock para retornar a lista de consultas
        when(consultaRepository.findAll()).thenReturn(consultas);

        // Executa o metodo de serviço que lista as consultas
        var resultado = consultaService.listarConsultas();

        // Verifica se o resultado não é nulo e o tamanho da lista é o esperado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Consulta de rotina", resultado.get(0).getEspecialidade());
        assertEquals("Consulta de emergência", resultado.get(1).getEspecialidade());
    }

    // Testa o metodo de busca de consulta por ID
    @Test
    void buscarConsultaPorId(){

        // Cria uma consulta com um ID e especialidade
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(1L); // ID adicionado
        consulta.setEspecialidade("Consulta de rotina");

        // Configura o mock para retornar a consulta quando o ID for buscado
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consulta));

        // Executa o metodo de serviço para buscar a consulta pelo ID
        var resultado = consultaService.buscarConsulta(1L);

        // Verifica se o resultado não é nulo e a especialidade é a esperada
        assertNotNull(resultado);
        assertEquals("Consulta de rotina", resultado.getEspecialidade());

        // Verifica se o metodo findById foi chamado uma vez com o ID 1
        verify(consultaRepository, times(1)).findById(1L);
    }

    // Testa o metodo de exclusão de consulta por ID
    @Test
    void deletarConsultaPorId(){

        // Configura o mock para não fazer nada ao excluir uma consulta por ID
        doNothing().when(consultaRepository).deleteById(anyLong());

        // Executa o metodo de serviço que exclui a consulta
        consultaService.deletarConsulta(1L);

        // Verifica se o metodo deleteById foi chamado uma vez com o ID 1
        verify(consultaRepository, times(1)).deleteById(1L);
    }

    // Testa o metodo de atualização de uma consulta
    @Test
    void atualizarConsultaPorId(){

        // Cria uma consulta existente e uma consulta atualizada com novas informações
        Consulta consultaExistente = new Consulta();
        consultaExistente.setEspecialidade("Consulta de rotina");
        Consulta consultaAtualizada = new Consulta();
        consultaAtualizada.setEspecialidade("Consulta de acompanhamento");
        // Configura o mock para retornar a consulta existente ao buscar pelo ID e a consulta atualizada ao salvar
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consultaExistente));
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consultaAtualizada);

        // Executa o metodo de serviço para atualizar a consulta
        var resultado = consultaService.atualizarConsulta(consultaAtualizada);

        // Verifica se o resultado não é nulo e a especialidade foi atualizada corretamente
        assertNotNull(resultado);
        assertEquals("Consulta de acompanhamento", resultado.getEspecialidade());

        // Verifica se o metodo findById foi chamado uma vez e o save foi chamado com a consulta atualizada
        verify(consultaRepository, times(1)).findById(1L);
        verify(consultaRepository, times(1)).save(consultaAtualizada);
    }
}
