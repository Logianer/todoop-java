package de.dhsn_ooe.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.dhsn_ooe.todo.Model.AbstractTodoList;
import de.dhsn_ooe.todo.Model.TodoListRepository;
import de.dhsn_ooe.todo.Model.TodoListType;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(TodoListRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new AbstractTodoList(TodoListType.NOTE, "First List")));
      log.info("Preloading " + repository.save(new AbstractTodoList(TodoListType.NOTE, "Project X")));
    };
  }
}
