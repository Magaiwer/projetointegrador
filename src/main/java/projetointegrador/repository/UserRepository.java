package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetointegrador.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u where lower(u.login) = lower(:login) and lower(u.password) = lower(:password) and u.active = true")
    Optional<User> findUsuarioByLoginAndSenha(@Param("login") String login, @Param("password") String password);

    Optional<User> findByLoginAndActiveTrue(String login);

    Optional<User> findByLogin(String login);

    @Query("select distinct u from User u inner join fetch u.groups g left join g.permissions where u.id =:id")
    Optional<User> findUserWithGroups(@Param("id") Long id);

    @Query("select distinct u from User u inner join fetch u.groups g where u.id =:id")
    Optional<User> findUserGroups(@Param("id") Long id);
}
