package br.com.alyssonbezerra.medicalconsult.usuario.services;

import br.com.alyssonbezerra.medicalconsult.usuario.domain.Usuario;
import br.com.alyssonbezerra.medicalconsult.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado! ID: " + id)
        );
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = buscarUsuario(id);
        usuarioRepository.delete(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {

        Usuario usuarioExistente = buscarUsuario(id);


        usuarioExistente.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
        usuarioExistente.setCpf(usuarioAtualizado.getCpf());
        usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());




        return usuarioRepository.save(usuarioExistente);
    }
}
