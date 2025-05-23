package de.dhsn_ooe.todo.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.dhsn_ooe.todo.Exception.TodoListNotFoundException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoCheckListRepository;

@RestController
public class TodoCheckListController {
	private final TodoCheckListRepository repository;

  TodoCheckListController(TodoCheckListRepository repository) {
    this.repository = repository;
  }


  @GetMapping("/list")
  Iterable<TodoCheckList> all() {
    return repository.findAll();
  }

  @PostMapping("/list")
  TodoCheckList newTodoCheckList(@RequestBody TodoCheckList newList) {
      return repository.save(newList);
  }

  @GetMapping("/list/{id}")
  TodoCheckList one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new TodoListNotFoundException(id));
  }

  @PutMapping("/list/{id}")
  TodoCheckList updateTodoCheckList(@RequestBody TodoCheckList newTodoList, @PathVariable Long id) {
    
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
  void deleteTodoCheckList(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
