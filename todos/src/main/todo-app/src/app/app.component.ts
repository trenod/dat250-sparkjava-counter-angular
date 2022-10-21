import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'todo-app';
  constructor(private httpClient: HttpClient) {
    this.loadTodos();

  }

  todos: any[] = []

  loadTodos(){
    this.httpClient.get('http://localhost:8080/todos').subscribe((todos: any) =>
      this.todos = todos);


  }

  createTodo() {
    var toPost: string
    //toPost = summary
    //toPost += " "
    //toPost += description
    this.httpClient.post('http://localhost:8080/todos', {
        summary: "This is the summary",
        description: "This is the description",
      })
      .subscribe((response) => {
      alert(JSON.stringify(response));
      },
      (error) => {
      alert(JSON.stringify(error));
      }
      );
  }

  deleteTodo(id: number) {
    this.httpClient.delete('http://localhost:8080/todos/{id}').subscribe((response) =>
      alert(JSON.stringify(response)));

  }

}
