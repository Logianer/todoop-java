package de.dhsn_ooe.todo.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.dhsn_ooe.todo.Exception.TodoListNotFoundException;
import de.dhsn_ooe.todo.Model.AbstractTodoList;
import de.dhsn_ooe.todo.Model.TodoListRepository;
import de.dhsn_ooe.todo.Model.TodoListType;

@RestController
public class TodoListController {
	private final TodoListRepository repository;

  TodoListController(TodoListRepository repository) {
    this.repository = repository;
  }


  @GetMapping("/list")
  Iterable<AbstractTodoList> all() {
    return repository.findAll();
  }

  @PostMapping("/list")
  AbstractTodoList newTodoList(@RequestBody AbstractTodoList newTodoList) {
    if (newTodoList.getType() != TodoListType.NOTE) {
      newTodoList.setHtmlContent(null);
    }
    return repository.save(newTodoList);
  }

  @GetMapping("/list/{id}")
  AbstractTodoList one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new TodoListNotFoundException(id));
  }

  @PutMapping("/list/{id}")
  AbstractTodoList replaceTodoList(@RequestBody AbstractTodoList newTodoList, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(TodoList -> {
        TodoList.setTitle(newTodoList.getTitle());
        return repository.save(TodoList);
      })
      .orElseGet(() -> {
        return repository.save(newTodoList);
      });
  }

  @DeleteMapping("/list/{id}")
  void deleteTodoList(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
