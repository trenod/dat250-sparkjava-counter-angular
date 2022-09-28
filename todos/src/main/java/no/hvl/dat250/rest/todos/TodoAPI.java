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

        after((req, res) -> res.type("application/json"));

        // TODO: Implement API, such that the testcases succeed.

        //get("/hello", (req, res) -> "Hello World!");

        //get("/counters", (req, res) -> todo.toJson());

        delete("/todos", (req, res) -> {
            Gson gson = new Gson();
            Todo todoobj = gson.fromJson(req.body(), Todo.class);
            todos.remove(todoobj);
            return gson.toJson(todoobj);
        });

        post("/todos", (req, res) -> {
            Gson gson = new Gson();
            Todo todoobj = gson.fromJson(req.body(), Todo.class);
            todos.add(todoobj);
            return gson.toJson(todoobj);
        });

        get("/todos", (req, res) -> {
            Gson gson = new Gson();
            return gson.toJson(todos);
        });

        put("/todos", (req,res) -> {
            Gson gson = new Gson();
            Todo todoobj = gson.fromJson(req.body(), Todo.class);
            todos.add(todoobj);
            return gson.toJson(todoobj);
        });
    }
}
