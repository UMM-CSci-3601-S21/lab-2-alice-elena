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
 * Tests umm3601.todo.ToDosDatabase filterToDosByOrderBy and listToDos with _status_ query or _owner_ query
 * or _category_ query or _body_ parameters
 */
public class FilterToDosByOrderByFormDB {

  @Test
  public void sortToDosByOrderBy() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    ToDos[] allToDos = db.listToDos(new HashMap<>());

    ToDos[] orderByOwnerToDos = db.sortToDosByOrderBy(allToDos, "owner");
    for (int i = 0; i < orderByOwnerToDos.length-1; i++) {
      int myNum = orderByOwnerToDos[i].owner.compareTo(orderByOwnerToDos[i+1].owner);
      Boolean result = myNum < 0;
      if (myNum == 0) {
        assertEquals(0 , myNum);
      } else{
      assertEquals(true, result); }
    }

    ToDos[] orderByCategoryToDos = db.sortToDosByOrderBy(allToDos, "category");
    for (int i = 0; i < orderByCategoryToDos.length-1; i++) {
      int myNum = orderByCategoryToDos[i].category.compareTo(orderByCategoryToDos[i+1].category);
      Boolean result = myNum < 0;
      if (myNum == 0) {
        assertEquals(0 , myNum);
      } else{
      assertEquals(true, result); }
    }

    ToDos[] orderByBodyToDos = db.sortToDosByOrderBy(allToDos, "body");
    for (int i = 0; i < orderByBodyToDos.length-1; i++) {
      int myNum = orderByBodyToDos[i].body.compareTo(orderByBodyToDos[i+1].body);
      Boolean result = myNum < 0;
      if (myNum == 0) {
        assertEquals(0 , myNum);
      } else{
      assertEquals(true, result); }
    }

    ToDos[] orderByStatusToDos = db.sortToDosByOrderBy(allToDos, "status");
    for (int i = 0; i < orderByCategoryToDos.length-1; i++) {
      int myNum = orderByStatusToDos[i].status.compareTo(orderByStatusToDos[i+1].status);
      Boolean result = myNum < 0;
      if (myNum == 0) {
        assertEquals(0 , myNum);
      } else{
      assertEquals(true, result); }
    }
  }

  @Test
  public void listToDosWithOrderByFilter() throws IOException {
    ToDosDatabase db = new ToDosDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("orderBy", Arrays.asList(new String[] { "owner" }));
    ToDos[] ownerToDos = db.listToDos(queryParams);
    for (int i =0; i<ownerToDos.length-1; i++) {
      int myNum = ownerToDos[i].owner.compareTo(ownerToDos[i+1].owner);
      Boolean result = myNum < 0;
      if (myNum == 0) {
        assertEquals(0 , myNum);
      } else{
      assertEquals(true, result); }
    }

    queryParams.put("orderBy", Arrays.asList(new String[] { "category" }));
    ToDos[] categoryToDos = db.listToDos(queryParams);
    for (int i =0; i<categoryToDos.length-1; i++) {
      int myNum = categoryToDos[i].category.compareTo(categoryToDos[i+1].category);
      Boolean result = myNum < 0;
      if (myNum == 0) {
        assertEquals(0 , myNum);
      } else{
      assertEquals(true, result); }
    }

    queryParams.put("orderBy", Arrays.asList(new String[] { "status" }));
    ToDos[] statusToDos = db.listToDos(queryParams);
    for (int i =0; i<statusToDos.length-1; i++) {
      int myNum = statusToDos[i].status.compareTo(statusToDos[i+1].status);
      Boolean result = myNum < 0;
      if (myNum == 0) {
        assertEquals(0 , myNum);
      } else{
      assertEquals(true, result); }
    }

    queryParams.put("orderBy", Arrays.asList(new String[] { "body" }));
    ToDos[] bodyToDos = db.listToDos(queryParams);
    for (int i =0; i<bodyToDos.length-1; i++) {
      int myNum = bodyToDos[i].body.compareTo(bodyToDos[i+1].body);
      Boolean result = myNum < 0;
      if (myNum == 0) {
        assertEquals(0 , myNum);
      } else{
      assertEquals(true, result); }
    }
  }
}

