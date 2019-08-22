package projetointegrador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

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

        encodePassword(usuario);

        if (StringUtils.isEmpty(usuario.getSenha())) {
            usuario.setSenha(usuarioExistente.get().getSenha());
        }

        usuario.setConfirmacaoSenha(usuario.getSenha());

        try {
            usuarioRepository.saveAndFlush(usuario);
            LOGGER.info("usuario salvo com sucesso");

        } catch (Exception e) {
            MDC.put("user", usuarioLogado.getNome());
            LOGGER.error(e.getMessage());
        }
    }

    private void requiredPassword(Usuario usuario) {
        if (StringUtils.isEmpty(usuario.getSenha()) && usuario.isNew()) {
            throw new RequiredPasswordException("Senha é obrigatória para novo usuário");
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

    private void encodePassword(Usuario usuario) {
        if (usuario.isNew() || !StringUtils.isEmpty(usuario.getSenha())) {
            usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
        }
    }

/*    private boolean bothAreEquals(String password,String confirmPassword) {
        return password != null && password.equals(confirmPassword);
    }

    private boolean bothAreENull(String password,String confirmPassword) {
        return StringUtils.isEmpty(password) && StringUtils.isEmpty(confirmPassword);
    }*/


}
