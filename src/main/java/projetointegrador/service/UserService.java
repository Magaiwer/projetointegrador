package projetointegrador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import projetointegrador.model.Group;
import projetointegrador.model.User;
import projetointegrador.repository.GroupRepository;
import projetointegrador.repository.UserRepository;
import projetointegrador.service.exception.EmailJaCadastradoException;
import projetointegrador.service.exception.PasswordInvalidException;
import projetointegrador.service.exception.RequiredPasswordException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public static User userLogged;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void save(User user) {
        Optional<User> userExisting= userRepository.findByLogin(user.getLogin());

        validateEmailExistes(userExisting, user);

        requiredPassword(user);
        matchesPassword(user);

        encodePassword(user);

        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(userExisting.get().getPassword());
        }

        user.setConfimationPassword(user.getPassword());

        userRepository.saveAndFlush(user);

    }

    private void requiredPassword(User user) {
        if (StringUtils.isEmpty(user.getPassword()) && user.isNew()) {
            throw new RequiredPasswordException("Senha é obrigatória para novo usuário");
        }
    }

    private void matchesPassword(User user) {
        if (!StringUtils.pathEquals(user.getPassword(), user.getConfimationPassword())) {
            throw new PasswordInvalidException("Senha e confirmação de senha não são iguais!");
        }
    }

    private void validateEmailExistes(Optional<User> usuarioExistente, User user) {
        if (usuarioExistente.isPresent() && user.isNew()) {
            throw new EmailJaCadastradoException("Email já cadastrado no sistema");
        }
    }

    private void encodePassword(User user) {
        if (user.isNew() || !StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        }
    }

    public static void setUserLogged(User userLogado) {
        UserService.userLogged = userLogado;
    }

    private void fillGroups () {
        List<Group> groupList = groupRepository.findAllWithPermissions();
    }

}
