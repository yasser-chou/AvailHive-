import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ActivatedRoute, Router } from '@angular/router';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.scss']
})
export class ReviewComponent implements OnInit {

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
    this.validateForm = this.fb.group({
      rating: [null, Validators.required],
      review: [null, Validators.required]
    });
  }

  giveReview(): void {
    if (this.validateForm.valid) {
      const reviewDTO = {
        rating: this.validateForm.get('rating')?.value,
        review: this.validateForm.get('review')?.value,
        userId: this.userStorageService.getUserId(),
        bookId: this.bookId
      };

      this.clientService.giveReview(reviewDTO).subscribe(
        res => {
          this.notification.success(
            'SUCCESS',
            'Review posted successfully',
            { nzDuration: 5000 }
          );
          this.router.navigateByUrl('/client/bookings');
        },
        error => {
          this.notification.error(
            'ERROR',
            `${error.message}`,
            { nzDuration: 5000 }
          );
        }
      );
    } else {
      this.notification.error(
        'ERROR',
        'Please fill in all required fields',
        { nzDuration: 5000 }
      );
    }
  }
}
