import { Component } from '@angular/core';
import { GroupMessage } from 'src/app/models/group-message';
import { GroupMessageService } from 'src/app/services/group-message.service';

@Component({
  selector: 'app-group-message',
  templateUrl: './group-message.component.html',
  styleUrls: ['./group-message.component.css']
})
export class GroupMessageComponent {

  messages: GroupMessage[] | null = null;

  constructor(private messageService: GroupMessageService) {

  }

  ngOnInit() {

  }




reload(id: number){
  this.messageService.reload(id).subscribe({
    next: (messages: GroupMessage[]) => {
      this.messages = messages;
    },
    error: (err) => {
      console.log(err);
    }
  })
}

}
