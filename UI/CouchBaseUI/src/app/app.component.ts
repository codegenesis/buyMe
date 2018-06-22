import { Component } from '@angular/core';
import { UserServie } from './pet.service';
import { Owner } from './model.owner';
import { User } from './model.user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  users: User[] = [];
  id: number

  constructor(private userService: UserServie) { }

  ngOnInit() {

  }

  getUsersById() {
    this.userService.getCouchBase('http://localhost:8080/' + this.id)
      .subscribe((users: User[]) => {
        this.users = users;
      });
  }

}
