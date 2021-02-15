package umm3601.todos;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

/**
 * ToDos Controller will manage requests for info about ToDos.
 */
public class ToDosController {

  private ToDosDatabase database;

  /**
   * Construct a controller for ToDos.
   * This will store the ToDos info in database from a JSON file in order to
   * respond requests related to ToDOs.
   *
   * @param database the 'ToDosDatabase' containing todos data.
   */

  public ToDosController(ToDosDatabase database) {
    this.database = database;
  }

  /**
   * Get the single todo specified by the 'id' parameter in the request.
   *
   * @param ctx a Javalin HTTP context
   */
  public void getToDo(Context ctx) {
    String id = ctx.pathParam("id", String.class).get();
    ToDos toDo = database.getToDo(id);
    if (toDo != null) {
      ctx.json(toDo);
      ctx.status(201);
    } else {
      throw new NotFoundResponse(" No todo with id " + id + " was found.");
    }
  }

  /**
   * Get a JSON response with a list of all the users in the "database".
   *
   * @param ctx a Javalin HTTP context
   */
  public void getToDos(Context ctx) {
    ToDos[] toDos = database.listToDos(ctx.queryParamMap());
    ctx.json(toDos);
  }
}
