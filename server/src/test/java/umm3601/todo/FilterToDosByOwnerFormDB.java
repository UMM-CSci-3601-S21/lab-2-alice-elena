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
 * Tests umm3601.user.Database filterToDosByOwner and listToDos with _owner_ query
 * parameters
 */
public class FilterToDosByOwnerFormDB {

  @Test
  public void filterToDosByOwner() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos[] allToDos = db.listToDos(new HashMap<>());

    ToDos[] ownerBlancheToDos = db.filterToDosByOwner(allToDos, "Blanche");
    for (ToDos todo : ownerBlancheToDos) {
      assertEquals("Blanche", todo.owner,"Incorrect owner for listed todos.");
    }
  }

  @Test
  public void listToDosWithOwnerFilter() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] { "Fry" }));
    ToDos[] ownerBlancheToDos = db.listToDos(queryParams);
    for (ToDos todo : ownerBlancheToDos) {
      assertEquals("Fry", todo.owner, "Incorrect owner for listed todos.");
    }

    queryParams.put("owner", Arrays.asList(new String[] { "Barry" }));
    ToDos[] ownerBarryToDos = db.listToDos(queryParams);
    for (ToDos todo : ownerBarryToDos) {
      assertEquals("Barry", todo.owner, "Incorrect owner for listed todos.");
    }
  }
}
