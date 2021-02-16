package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import umm3601.todos.ToDos;
import umm3601.todos.ToDosDatabase;

/**
 * Tests umm3601.todo.ToDosDatabase filterToDosByContains and listToDos with _contains_ query
 * (search in body) parameters.
 */
public class FilterToDosByContainsFormDB {

  @Test
  public void filterToDosByContains() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos[] allToDos = db.listToDos(new HashMap<>());

    ToDos[] containsDolorToDos = db.filterToDosByBody(allToDos, "dolor");
    for (ToDos todo : containsDolorToDos) {
      assertTrue(todo.body.contains("dolor"));
    }
  }

  @Test
  public void listToDosWithStatusFilter() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("contains", Arrays.asList(new String[] { "qui" }));
    ToDos[] containsQuiToDos = db.listToDos(queryParams);
    for (ToDos todo : containsQuiToDos) {
      assertTrue(todo.body.contains("qui"));
    }


    queryParams.put("contains", Arrays.asList(new String[] { "non" }));
    ToDos[] containsNonToDos = db.listToDos(queryParams);
    for (ToDos todo : containsNonToDos) {
      assertTrue(todo.body.contains("non"));
    }
  }
}

