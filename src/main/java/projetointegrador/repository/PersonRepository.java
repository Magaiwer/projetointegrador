package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
