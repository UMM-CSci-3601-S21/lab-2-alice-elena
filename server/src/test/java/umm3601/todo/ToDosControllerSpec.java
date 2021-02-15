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


}
