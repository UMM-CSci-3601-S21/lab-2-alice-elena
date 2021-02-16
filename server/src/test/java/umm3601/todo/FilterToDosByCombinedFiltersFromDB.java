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
 * Tests umm3601.todo.ToDosDatabase listToDos with _owner_, _status_ query and _limit_ query
 * parameters
 */
public class FilterToDosByCombinedFiltersFromDB {

  @Test
  public void listToDosWithCombinedFilters() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] { "Blanche" }));
    ToDos[] ownerBlancheToDos = db.listToDos(queryParams);
    assertEquals(43, ownerBlancheToDos.length, "Incorrect number of todos that have Blanche as owner.");

    queryParams.clear();
    queryParams.put("status", Arrays.asList(new String[] { "complete" }));
    ToDos[] statusCompleteToDos = db.listToDos(queryParams);
    assertEquals(143, statusCompleteToDos.length, "Incorrect number of todos that have status as complete.");

    queryParams.clear();
    queryParams.put("limit", Arrays.asList(new String[] { "12" }));
    ToDos[] limit7ToDos = db.listToDos(queryParams);
    assertEquals(12, limit7ToDos.length, "Incorrect number of todos that will be displayed.");

    queryParams.clear();
    queryParams.put("owner", Arrays.asList(new String[] { "Blanche" }));
    queryParams.put("status", Arrays.asList(new String[] { "complete" }));
    queryParams.put("limit", Arrays.asList(new String[] { "12" }));
    ToDos[] ownerBlancheStatusCompleteLimit7ToDos = db.listToDos(queryParams);
	assertEquals(12, ownerBlancheStatusCompleteLimit7ToDos.length, "Incorrect number of todos with owner Blanche and status complete in the limit as 12.");
  }
}
