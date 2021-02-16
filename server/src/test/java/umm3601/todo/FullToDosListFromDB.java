package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import umm3601.todos.ToDos;
import umm3601.todos.ToDosDatabase;

/**
 * Tests umm3601.todo.ToDosDatabase listToDo functionality
 */
public class FullToDosListFromDB {

  @Test
  public void totalToDosCount() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos[] allToDos = db.listToDos(new HashMap<>());
    assertEquals(300, allToDos.length, "Incorrect total number of todos");
  }

  @Test
  public void firstToDosInFullList() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos[] allToDos = db.listToDos(new HashMap<>());
    ToDos firstToDo = allToDos[0];
    assertEquals("Blanche", firstToDo.owner, "Incorrect owner");
    assertEquals(false, firstToDo.status, "Incorrect status");
    assertEquals("In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.",
     firstToDo.body, "Incorrect body");
    assertEquals("software design", firstToDo.category, "Incorrect category");
  }
}
