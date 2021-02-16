package umm3601.todos;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import io.javalin.http.BadRequestResponse;

/**
 * A fake "database" of todos info
 *
 * Acting like a real database by read todos data from
 * a specified JSON file and provide database by methods that
 * allow ToDosController to "query" the "database"
 */

 public class ToDosDatabase {

  private ToDos[] allToDos;

  public ToDosDatabase(String toDosDataFile) throws IOException {
    Gson gson = new Gson();
    InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(toDosDataFile));
    allToDos = gson.fromJson(reader, ToDos[].class);
  }

  public int size() {
    return allToDos.length;
  }

  /**
   * Get the specified single todo by given ID. Return `null` if there is no
   * user with that ID.
   *
   * @param id the ID was asked by clients
   * @return the todo with the given ID, or null if there is no todo with the given ID.
   */
   public ToDos getToDo(String id) {
     return Arrays.stream(allToDos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
   }

   /**
    * Get an array of all the todos satisfying the queries in the params.
    *
    * @param queryParams map of key-value pairs for the query
    * @return an array of all the users matching the given criteria
    */
    public ToDos[] listToDos(Map<String, List<String>> queryParams) {
      ToDos[] filteredToDos = allToDos;

      //Filter status is defined
      if (queryParams.containsKey("status")) {
        String targetStatus = queryParams.get("status").get(0);
          if (targetStatus.equals("complete")){
            targetStatus = "true";
            Boolean newTargetStatus = Boolean.parseBoolean(targetStatus);
            filteredToDos = filterToDosByStatus(filteredToDos, newTargetStatus);
          } else if (targetStatus.equals("incomplete")) {
            targetStatus = "false";
            Boolean newTargetStatus = Boolean.parseBoolean(targetStatus);
            filteredToDos = filterToDosByStatus(filteredToDos, newTargetStatus);
          }
      }

      //Filter body is defined
      if (queryParams.containsKey("contains")) {
        String targetBody = queryParams.get("contains").get(0);
        filteredToDos = filterToDosByBody(filteredToDos, targetBody);
      }


      //Filter owner is defined
      if (queryParams.containsKey("owner")) {
        String targetOwner = queryParams.get("owner").get(0);
        filteredToDos = filterToDosByOwner(filteredToDos, targetOwner);
      }

      //Filter category is defined
      if (queryParams.containsKey("category")) {
        String targetCategory = queryParams.get("category").get(0);
        filteredToDos = filterToDosByCategory(filteredToDos, targetCategory);
      }

      //Filter sort/order todos by particular attribute (still testing)
      if (queryParams.containsKey("orderBy")) {
        String targetOrderBy = queryParams.get("orderBy").get(0);
        filteredToDos = sortToDosByOrderBy(filteredToDos, targetOrderBy);
      }

      //Limit request
      if (queryParams.containsKey("limit")) {
        String limitParam = queryParams.get("limit").get(0);
        try {
          int targetLimit = Integer.parseInt(limitParam);
          filteredToDos = filterToDosByLimit(filteredToDos, targetLimit);
        } catch (NumberFormatException e) {
          throw new BadRequestResponse("Specified limit '" + limitParam + "' can't be parsed to an integer");
        }
      }

      return filteredToDos;
    }

    /**
     * Get an array of all the todos having the target status.
     *
     * @param todos           the list of todos to filter by target status
     * @param newTargetStatus the boolean of targetStatus
     * @return                an array of all the todos from the given list after filtering
     */
    public ToDos[] filterToDosByStatus(ToDos[] todos, Boolean newTargetStatus) {
      return Arrays.stream(todos).filter(x -> x.status.equals(newTargetStatus)).toArray(ToDos[]::new);
    }

    /**
     * Get an array of all the todos having the target limit.
     *
     * @param todos       the list of todos to filter by limit
     * @param targetLimit the number limit the amount of todos that will be displayed
     * @return            an array of all the todos from the given list after
     */
    public ToDos[] filterToDosByLimit(ToDos[] todos, Integer targetLimit) {
      return Arrays.stream(todos).limit(targetLimit).toArray(ToDos[]::new);
    }

    /**
     * Get an array of all the todos contains the target body.
     *
     * @param todos           the list of todos to filter by body
     * @param targetBody      the target context we look for in body
     * @return                an array of all the todos from the given list after
     */
    public ToDos[] filterToDosByBody(ToDos[] todos, String targetBody) {
      return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(ToDos[]::new);
    }

    /**
     * Get an array of all the todos having the target owner.
     *
     * @param todos       the list of todos to filter by owner
     * @param targetOwner the target owner to look for
     * @return            an array of all the todos from the given list that have
     *                    target owner.
     */
    public ToDos[] filterToDosByOwner(ToDos[] todos, String targetOwner) {
      return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(ToDos[]::new);
    }

    /**
     * Get an array of all the todos having the target category.
     *
     * @param todos           the list of todos to filter by owner
     * @param targetCategory  the target category to look for
     * @return                an array of all the todos from the given list that have
     *                        target category.
     */
    public ToDos[] filterToDosByCategory(ToDos[] todos, String targetCategory) {
      return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(ToDos[]::new);
    }

    /**
     * Get an array of all the todos having the filter orderBy.
     *
     * @param todos           the list of todos to be sorted by orderBy
     * @param targetOrderBy   the specified field will be used to sort all todos
     * @return                an array of all the todos from the given list
     *                        in alphabetical order sorted by orderBy.
     */
    public ToDos[] sortToDosByOrderBy(ToDos[] todos, String targetOrderBy) {
        ToDos[] mySorted = Arrays.stream(todos).sorted((h1,h2)->{
          if (targetOrderBy.equals("owner")) {
            return h1.owner.compareTo(h2.owner);
          }
          if (targetOrderBy.equals("body")) {
            return h1.body.compareTo(h2.body);
          }
          if (targetOrderBy.equals("category")) {
            return h1.category.compareTo(h2.category);
          }
          else if (targetOrderBy.equals("status")) {
            return h1.status.compareTo(h2.status);
          } else {
            return 0;
          }
        }).toArray(ToDos[]::new);
        return mySorted;
    }
 }
