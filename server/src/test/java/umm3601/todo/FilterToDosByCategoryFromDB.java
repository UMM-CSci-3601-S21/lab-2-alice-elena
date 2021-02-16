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
 * Tests umm3601.user.Database filterUsersByAge and listUsers with _age_ query
 * parameters
 */
public class FilterToDosByCategoryFromDB {

  @Test
  public void filterToDosByCategory() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos[] allToDos = db.listToDos(new HashMap<>());

    ToDos[] categoryGroceriesToDos = db.filterToDosByCategory(allToDos, "groceries");
    for (ToDos todo : categoryGroceriesToDos) {
      assertEquals("groceries", todo.category,"Incorrect category for listed todos.");
    }
  }

  @Test
  public void listToDosWithCategoryFilter() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("category", Arrays.asList(new String[] { "homework" }));
    ToDos[] categoryHomeworkToDos = db.listToDos(queryParams);
    for (ToDos todo : categoryHomeworkToDos) {
      assertEquals("homework", todo.category, "Incorrect category for listed todos.");
    }

    queryParams.put("category", Arrays.asList(new String[] { "software design" }));
    ToDos[] categorySoftwareDesignToDos = db.listToDos(queryParams);
    for (ToDos todo : categorySoftwareDesignToDos) {
      assertEquals("software design", todo.category, "Incorrect category for listed todos.");
    }
  }
}
