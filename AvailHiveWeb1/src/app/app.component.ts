import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from "@angular/router";
import { UserStorageService } from "./basic/services/storage/user-storage.service";
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'AvailHiveWeb1';
  isClientLoggedIn: boolean = false;
  isCompanyLoggedIn: boolean = false;
  isAdDropdownVisible = false;
  isEmployerDropdownVisible = false;

  constructor(
    private router: Router,
    private userStorageService: UserStorageService // Inject the UserStorageService
  ) {}


  toggleAdDropdown(): void {
    this.isAdDropdownVisible = !this.isAdDropdownVisible;
    if (this.isAdDropdownVisible) {
      this.isEmployerDropdownVisible = false; // Ensure only one dropdown is visible
    }
  }

  toggleEmployerDropdown(): void {
    this.isEmployerDropdownVisible = !this.isEmployerDropdownVisible;
    if (this.isEmployerDropdownVisible) {
      this.isAdDropdownVisible = false; // Ensure only one dropdown is visible
    }
  }

  closeDropdowns() {
    this.isAdDropdownVisible = false;
    this.isEmployerDropdownVisible = false;
  }

  ngOnInit() {
    // Only respond to NavigationEnd events
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.isClientLoggedIn = this.userStorageService.isClientLoggedIn();
      this.isCompanyLoggedIn = this.userStorageService.isCompanyLoggedIn();
    });
  }


  logout() {
    this.userStorageService.signOut();
    this.router.navigateByUrl('/login');
    this.closeDropdowns();
  }
}
