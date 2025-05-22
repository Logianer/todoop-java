package de.dhsn_ooe.todo.Model;

import org.springframework.data.repository.CrudRepository;

public interface TodoListRepository extends CrudRepository<AbstractTodoList, Long> {

}