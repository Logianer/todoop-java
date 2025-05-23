package de.dhsn_ooe.todo.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.dhsn_ooe.todo.Exception.TodoListNotFoundException;
import de.dhsn_ooe.todo.Model.TodoNoteRepository;
import de.dhsn_ooe.todo.Model.TodoNote;

@RestController
public class TodoNotesController {
	private final TodoNoteRepository repository;

  TodoNotesController(TodoNoteRepository repository) {
    this.repository = repository;
  }


  @GetMapping("/note")
  Iterable<TodoNote> all() {
    return repository.findAll();
  }

  @PostMapping("/note")
  TodoNote newTodoNote(@RequestBody TodoNote newList) {
      return repository.save(newList);
  }

  @GetMapping("/note/{id}")
  TodoNote one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new TodoListNotFoundException(id));
  }

  @PutMapping("/note/{id}")
  TodoNote updateTodoNote(@RequestBody TodoNote newTodoList, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(TodoList -> {
        TodoList.setTitle(newTodoList.getTitle());
        TodoList.setHtmlContent(newTodoList.getHtmlContent());
        return repository.save(TodoList);
      })
      .orElseGet(() -> {
        return repository.save(newTodoList);
      });
  }

  @DeleteMapping("/note/{id}")
  void deleteTodoNote(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
