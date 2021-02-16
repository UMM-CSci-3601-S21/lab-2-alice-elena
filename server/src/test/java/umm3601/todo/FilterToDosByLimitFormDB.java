package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import umm3601.todos.ToDos;
import umm3601.todos.ToDosDatabase;

/**
 * Tests umm3601.todo.ToDosDatabase filterToDosByLimit and listToDos with _limit_ query
 * parameters
 */
public class FilterToDosByLimitFormDB {

  @Test
  public void filterToDosByLimit() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos[] allToDos = db.listToDos(new HashMap<>());

    ToDos[] limit7ToDos = db.filterToDosByLimit(allToDos, 7);
    assertEquals(7, limit7ToDos.length, "Incorrect limit for returned todos.");

    ToDos[] limit24ToDos = db.filterToDosByLimit(allToDos, 24);
    assertEquals(24, limit24ToDos.length, "Incorrect limit for returned todos.");
  }

  @Test
  public void listToDosWithStatusFilter() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("limit", Arrays.asList(new String[] { "14" }));
    ToDos[] limit14ToDos = db.listToDos(queryParams);
    assertEquals(14, limit14ToDos.length,"Incorrect status for listed todos.");


    queryParams.put("limit", Arrays.asList(new String[] { "44" }));
    ToDos[] limit44ToDos = db.listToDos(queryParams);
    assertEquals(44, limit44ToDos.length,"Incorrect status for listed todos.");

  }
}

