package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import umm3601.todos.ToDos;
import umm3601.todos.ToDosDatabase;

/**
 * Tests umm3601.user.Database getUser functionality
 */
public class GetToDoByIDFromDB {

  @Test
  public void getBlanche() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos todo = db.getToDo("58895985a22c04e761776d54");
    assertEquals("Blanche", todo.owner, "Incorrect owner");
  }

  @Test
  public void getFry() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos todo = db.getToDo("58895985c1849992336c219b");
    assertEquals("Fry", todo.owner, "Incorrect owner");
  }
}
