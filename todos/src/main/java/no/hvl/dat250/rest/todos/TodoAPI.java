package no.hvl.dat250.rest.todos;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static spark.Spark.*;

/**
 * Rest-Endpoint.
 */
public class TodoAPI {



    static ArrayList<Todo> todos;
    //static Todo todo = null;

    public static void main(String[] args) {
        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        //todo = new Todo();

        todos = new ArrayList<>();

        Long idinc = 0L;

        after((req, res) -> res.type("application/json"));

        // TODO: Implement API, such that the testcases succeed.

        //get("/hello", (req, res) -> "Hello World!");

        //get("/counters", (req, res) -> todo.toJson());

        delete("/todos", (req, res) -> {
            Gson gson = new Gson();
            Todo todoobj = gson.fromJson(req.body(), Todo.class);
            todos.remove(todoobj);
            return todoobj.toJson();
        });

        post("/todos", (req, res) -> {
            Gson gson = new Gson();
            Todo todoobj = gson.fromJson(req.body(), Todo.class);
            todos.add(todoobj);
            //return gson.toJson(todoobj);
            return todoobj.toJson();
        });

        get("/todos", (req, res) -> {
            Gson gson = new Gson();
            /*
            StringBuilder todostring = new StringBuilder();
            for (Todo todo : todos) {
                todostring.append(todo.toJson());
                todostring.append("\n");
            }
            return todostring;
            */
            return gson.toJson(todos);
        });

        put("/todos:id", (req,res) -> {
            Gson gson = new Gson();
            Boolean found = false;
            Todo todotochange = null;
            for (Todo todo : todos) {
                if (todo.getId() == Integer.parseInt(req.params(":id"))) {
                    todotochange = todo;
                    found = true;
                    break;
                }
            }
            if (found) {
                Todo todoobj = gson.fromJson(req.body(), Todo.class);
                todos.remove(todotochange);
                todos.add(todoobj);
                //return gson.toJson(todoobj);
                return todoobj.toJson();
            } else { return null; }
        });
    }


}
