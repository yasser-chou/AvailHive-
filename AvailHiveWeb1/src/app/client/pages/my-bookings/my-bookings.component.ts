import { Component, OnInit } from '@angular/core';
import {ClientService} from "../../services/client.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.scss']
})
export class MyBookingsComponent implements OnInit {

  bookedServices:any

  constructor(private clientService: ClientService,
              private router:Router) { }


  ngOnInit(): void {
    this.getMyBookings();
  }

  getMyBookings(){
    this.clientService.getMyBookings().subscribe(res=>{
      this.bookedServices = res;

    })
  }

  reclamation(bookingId: number): void {
    this.router.navigate(['/client/reclamation', bookingId]);
  }
}
