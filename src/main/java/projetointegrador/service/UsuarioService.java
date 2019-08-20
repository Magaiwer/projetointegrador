package projetointegrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import projetointegrador.model.Usuario;
import projetointegrador.repository.UsuarioRepository;
import projetointegrador.service.exception.EmailJaCadastradoException;
import projetointegrador.service.exception.PasswordInvalidException;
import projetointegrador.service.exception.RequiredPasswordException;

import java.util.Optional;

@Service
public class UsuarioService {

    public static Usuario usuarioLogado;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void save(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());


        validateEmailExistes(usuarioExistente, usuario);
        requiredPassword(usuario);
        matchesPassword(usuario);

        passwordEncoder.encode(usuario.getSenha());

        try {
            usuarioRepository.saveAndFlush(usuario);

        } catch (Exception e) {
            // TODO LOGGER
            System.out.println(e.getMessage());
        }
    }

    private void requiredPassword(Usuario usuario) {
        if (StringUtils.isEmpty(usuario.getSenha())) {
            throw new RequiredPasswordException("Senha é obrigatória");
        }
    }

    private void matchesPassword(Usuario usuario) {
        if (!StringUtils.pathEquals(usuario.getSenha(), usuario.getConfirmacaoSenha())) {
            throw new PasswordInvalidException("Senha e confirmação de senha não são iguais!");
        }
    }

    private void validateEmailExistes(Optional<Usuario> usuarioExistente, Usuario usuario) {
        if (usuarioExistente.isPresent() && usuario.isNew()) {
            throw new EmailJaCadastradoException("Email já cadastrado no sistema");
        }
    }


}
