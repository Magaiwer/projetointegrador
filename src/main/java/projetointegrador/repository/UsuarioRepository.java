package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("from Usuario u where lower(u.login) = lower(:login) and lower(u.senha) = lower(:senha) and u.ativo = true")
    Optional<Usuario> findUsuarioByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);

    Optional<Usuario> findByLoginAndAtivoTrue(String login);

}
