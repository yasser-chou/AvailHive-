import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {ActivatedRoute, Router} from "@angular/router";
import {UserStorageService} from "../../../basic/services/storage/user-storage.service";
import {ClientService} from "../../services/client.service";

@Component({
  selector: 'app-reclamation',
  templateUrl: './reclamation.component.html',
  styleUrls: ['./reclamation.component.scss']
})
export class ReclamationComponent implements OnInit {

  bookId: number;
  validateForm!: FormGroup;

  constructor(
      private fb: FormBuilder,
      private notification: NzNotificationService,
      private router: Router,
      private activatedroute: ActivatedRoute,
      private userStorageService: UserStorageService,
      private clientService: ClientService
  ) {
    this.bookId = this.activatedroute.snapshot.params['id'];
    console.log('bookId:', this.bookId);
  }

  ngOnInit(): void {
    console.log('book id', this.bookId)
  }

}
