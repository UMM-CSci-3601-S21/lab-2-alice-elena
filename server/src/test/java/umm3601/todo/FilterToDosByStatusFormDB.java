package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Locale.Category;

import org.junit.jupiter.api.Test;

import umm3601.todos.ToDos;
import umm3601.todos.ToDosDatabase;

/**
 * Tests umm3601.todo.ToDosDatabase filterToDosByStatus and listToDos with _status_ query
 * parameters
 */
public class FilterToDosByStatusFormDB {

  @Test
  public void filterToDosByStatus() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos[] allToDos = db.listToDos(new HashMap<>());

    ToDos[] statusCompleteToDos = db.filterToDosByOwner(allToDos, "complete");
    for (ToDos todo : statusCompleteToDos) {
      assertEquals("complete", todo.status,"Incorrect status for listed todos.");
    }
  }

  @Test
  public void listToDosWithStatusFilter() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("status", Arrays.asList(new String[] { "incomplete" }));
    ToDos[] statusIncompleteToDos = db.listToDos(queryParams);
    for (ToDos todo : statusIncompleteToDos) {
      assertEquals(false, todo.status,"Incorrect status for listed todos.");
    }

    queryParams.put("status", Arrays.asList(new String[] { "complete" }));
    ToDos[] statusCompleteToDos = db.listToDos(queryParams);
    for (ToDos todo : statusCompleteToDos) {
      assertEquals(true, todo.status, "Incorrect status for listed todos.");
    }
  }
}

