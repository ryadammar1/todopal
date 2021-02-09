package todopal.dao;

import org.springframework.data.repository.CrudRepository;

import todopal.model.Person;

public interface PersonRepository extends CrudRepository<Person, String> {
    Person findByEmail(String email);
}