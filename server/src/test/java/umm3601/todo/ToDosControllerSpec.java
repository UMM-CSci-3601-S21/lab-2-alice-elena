package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import io.javalin.core.validation.Validator;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import umm3601.Server;
import umm3601.todos.ToDos;
import umm3601.todos.ToDosController;
import umm3601.todos.ToDosDatabase;

/**
 * Tests the logic of the ToDoController
 *
 * @throws IOException
 */
public class ToDosControllerSpec {

  private Context ctx = mock(Context.class);

  private ToDosController toDosController;
  private static ToDosDatabase db;

  @BeforeEach
  public void setUp() throws IOException {
    ctx.clearCookieStore();

    db = new ToDosDatabase(Server.TODOS_DATA_FILE);
    toDosController = new ToDosController(db);
  }

  @Test
  public void GET_to_request_all_todos() throws IOException {
    //Call the method on the mock controller
    toDosController.getToDos(ctx);

    //Confirm that `json` was called with all the users.
    ArgumentCaptor<ToDos[]> argument = ArgumentCaptor.forClass(ToDos[].class);
    verify(ctx).json(argument.capture());
    assertEquals(db.size(), argument.getValue().length);
  }

  @Test
  public void GET_to_request_status_true_todos() throws IOException {
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("status", Arrays.asList(new String[] {"complete"}));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    toDosController.getToDos(ctx);

    //Confirm that all the todos passed to `json` have status = true.
    ArgumentCaptor<ToDos[]> argument = ArgumentCaptor.forClass(ToDos[].class);
    verify(ctx).json(argument.capture());
    for (ToDos todo : argument.getValue()) {
      assertEquals(true, todo.status);
    }
  }

  @Test
  public void GET_to_request_owner_Blanche_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("owner", Arrays.asList(new String[] { "Blanche" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    toDosController.getToDos(ctx);

    // Confirm that all the todos passed to `json` work for Blanche.
    ArgumentCaptor<ToDos[]> argument = ArgumentCaptor.forClass(ToDos[].class);
    verify(ctx).json(argument.capture());
    for (ToDos todo : argument.getValue()) {
      assertEquals("Blanche", todo.owner);
    }
  }

  @Test
  public void GET_to_request_category_groceries_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("category", Arrays.asList(new String[] { "groceries" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    toDosController.getToDos(ctx);

    // Confirm that all the todos passed to `json` work for groceries.
    ArgumentCaptor<ToDos[]> argument = ArgumentCaptor.forClass(ToDos[].class);
    verify(ctx).json(argument.capture());
    for (ToDos todo : argument.getValue()) {
      assertEquals("groceries", todo.category);
    }
  }

  @Test
  public void GET_to_request_contains_qui_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("contains", Arrays.asList(new String[] { "qui" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    toDosController.getToDos(ctx);

    // Confirm that all the todos passed to `json` work for qui.
    ArgumentCaptor<ToDos[]> argument = ArgumentCaptor.forClass(ToDos[].class);
    verify(ctx).json(argument.capture());
    for (ToDos todo : argument.getValue()) {
      assertEquals(true, todo.body.contains("qui"));
    }
  }

  @Test
  public void GET_to_request_limit_7_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("limit", Arrays.asList(new String[] { "7" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    toDosController.getToDos(ctx);

    // Confirm that all the todos passed to `json` work for qui.
    ArgumentCaptor<ToDos[]> argument = ArgumentCaptor.forClass(ToDos[].class);
    verify(ctx).json(argument.capture());
    // for (ToDos todo : argument.getValue()) {
    //   assertEquals(true, todo.body.contains("qui"));
    // }
    assertEquals(7, argument.getValue().length);
  }

  @Test
  public void GET_to_request_owner_Blanche_status_complete_limit_7_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("owner", Arrays.asList(new String[] { "Blanche" }));

    queryParams.put("status", Arrays.asList(new String[] { "complete" }));

    queryParams.put("limit", Arrays.asList(new String[] { "7" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    toDosController.getToDos(ctx);
  }

  //   // Confirm that all the todos passed to `json` work for Blanche,
  //   // have status complete and the limit that the server will return is 7 todos.
  //   ArgumentCaptor<ToDos[]> argument = ArgumentCaptor.forClass(ToDos[].class);
  //   verify(ctx).json(argument.capture());
  //   for (ToDos todo : argument.getValue()) {
  //     assertEquals("Blanche", todo.owner);
  //     assertEquals("complete", todo.status);
  //   }
  //   assertEquals(7, argument.getValue().length);
  // }
}
