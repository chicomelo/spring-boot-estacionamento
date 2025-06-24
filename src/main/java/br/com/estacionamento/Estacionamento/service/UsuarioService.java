package br.com.estacionamento.Estacionamento.service;

import br.com.estacionamento.Estacionamento.model.Usuario;
import br.com.estacionamento.Estacionamento.model.vo.UsuarioVO;
import br.com.estacionamento.Estacionamento.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioVO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioVO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getRole()
                ))
                .collect(Collectors.toList());
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = buscarPorId(id);

        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());

        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            existente.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        return usuarioRepository.save(existente);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
