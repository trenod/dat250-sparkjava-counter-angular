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
    static int idinc;
    //static Todo todo = null;

    public static void main(String[] args) {
        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        //todo = new Todo();

        todos = new ArrayList<>();
        idinc = 0;


        after((req, res) -> res.type("application/json"));

        // TODO: Implement API, such that the testcases succeed.
        //get("/hello", (req, res) -> "Hello World!");
        //get("/counters", (req, res) -> todo.toJson());

        delete("/todos/:id", (req, res) -> {
            String inid = req.params(":id");
            if (!(isLong(inid))){
                return (String.format("The id \"%s\" is not a number!", inid));
            }
            Todo todoobj = getTodo(Long.parseLong(inid));
            if (todoobj != null){
                todos.remove(todoobj);
            } else {
                return (String.format("Todo with the id \"%s\" not found!", inid));
            }
            Gson gson = new Gson();
            return gson.toJson(todoobj);
        });

        post("/todos", (req, res) -> {
            Gson gson = new Gson();
            String todoJson = "{id:"+ idinc + ",\n"+req.body().substring(1);
            idinc +=1;

            Todo createdTodo = gson.fromJson(todoJson, Todo.class);
            todos.add(createdTodo);
            Todo nnTodo = getTodo(createdTodo.getId());
            return nnTodo.toJson();
        });

        get("/todos", (req, res) -> {
            Gson gson = new Gson();
            return gson.toJson(todos);
        });

        get("/todos/:id", (req, res) -> {
            String idin = req.params(":id");
            if (!(isLong(idin))){
                return (String.format("The id \"%s\" is not a number!", idin));
            }
            Long id = Long.parseLong(idin);

            Todo gTodo = getTodo(id);
            if (gTodo == null) {
                return (String.format("Todo with the id  \"%s\" not found!", idin));

            } else {
                Gson gson = new Gson();
                return gson.toJson(gTodo);
            }
        });

        put("/todos/:id", (req,res) -> {
            Gson gson = new Gson();
            Todo todotochange;
            String idin = req.params(":id");
            if (!(isLong(idin))){
                return (String.format("The id \"%s\" is not a number!", idin));
            }
            for (Todo todo : todos) {
                if (todo.getId() == Integer.parseInt(req.params(":id"))) {
                    todotochange = todo;
                    Todo todoobj = gson.fromJson(req.body(), Todo.class);
                    todos.remove(todotochange);
                    todos.add(todoobj);
                    return gson.toJson(todoobj);
                }
            }
            return (String.format("Todo with the id  \"%s\" not found!", idin));
        });
    }

    private static Boolean isLong (String num) {
        try {
            Long.parseLong(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Todo getTodo(Long todoId) {
        Todo todo = todos.stream()
                .filter(t -> todoId.equals(t.getId()))
                .findAny()
                .orElse(null);
        return todo;
    }


}
