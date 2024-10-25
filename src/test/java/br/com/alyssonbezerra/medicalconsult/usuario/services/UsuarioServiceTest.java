package br.com.alyssonbezerra.medicalconsult.usuario.services;

import br.com.alyssonbezerra.medicalconsult.usuario.domain.Usuario;
import br.com.alyssonbezerra.medicalconsult.usuario.repository.UsuarioRepository;
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

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void cadastrarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("Alysson");

        //configuração do comportamento do  Mock
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        //A execução de metodo a ser testado
        var resultado = usuarioService.cadastrarUsuario(usuario);

        //Validação do teste
        assertNotNull(resultado);
        assertEquals("Alysson", resultado.getNomeUsuario());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void listarUsuarios(){

        //Criando o objeto Usuarios
        Usuario usuario1 = new Usuario();
        usuario1.setNomeUsuario("Alysson");
        Usuario usuario2 = new Usuario();
        usuario2.setNomeUsuario("José");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        // Configuração do comportamento do mock para listar todos os usuários
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Execução do metodo a ser testado
        var resultado = usuarioService.listarUsuarios();

        //Validação do teste
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Alysson", resultado.get(0).getNomeUsuario());
        assertEquals("José", resultado.get(1).getNomeUsuario());
    }

    @Test
    void buscarUsuariosPorId(){

        // Criando o objeto de usuário para teste de busca
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("Alysson");

        // Configuração do comportamento do mock para buscar por ID
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        // Execução do metodo a ser testado
        var resultado = usuarioService.buscarUsuario(1L);

        // Validação do teste
        assertNotNull(resultado);
        assertEquals("Alysson", resultado.getNomeUsuario());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void deletarUsuarioPorId(){

        // Configuração do comportamento do mock para deletar o usuário por ID
        doNothing().when(usuarioRepository).deleteById(anyLong());

        // Execução do metodo a ser testado
        usuarioService.deleteUsuario(1L);

        // Verificação da execução do metodo deleteById no repositório
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void atualizarUsuarioPorId(){

        // Criando objetos de usuário para teste de atualização
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setNomeUsuario("Alysson");
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNomeUsuario("José");

        // Configuração do comportamento do mock para busca e atualização do usuário
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);

        // Execução do metodo a ser testado
        var resultado = usuarioService.atualizarUsuario(1L, usuarioAtualizado);

        // Validação do teste
        assertNotNull(resultado);
        assertEquals("José", resultado.getNomeUsuario());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(usuarioAtualizado);
    }
}
