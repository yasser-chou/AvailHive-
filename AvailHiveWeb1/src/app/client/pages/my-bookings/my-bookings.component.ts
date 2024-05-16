import { Component, OnInit } from '@angular/core';
import {ClientService} from "../../services/client.service";

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.scss']
})
export class MyBookingsComponent implements OnInit {

  bookedServices:any

  constructor(private clientService: ClientService) { }


  ngOnInit(): void {
    this.getMyBookings();
  }

  getMyBookings(){
    this.clientService.getMyBookings().subscribe(res=>{
      this.bookedServices = res;

    })
  }


}
