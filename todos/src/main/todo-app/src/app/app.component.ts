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

  loadTodos(){
    this.httpClient.get('http://localhost:8080/todos').subscribe((response) =>
      alert(JSON.stringify(response)));

  }

  createTodo(id: number, summary: string, description: string) {
    var toPost:String[];
    toPost = [id.toString(), summary, description]
    this.httpClient.post('http://localhost:8080/todos', toPost).subscribe((response) =>
      alert(JSON.stringify(response)));
  }

}
