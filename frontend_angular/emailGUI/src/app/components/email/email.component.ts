import { ChangeDetectionStrategy, ChangeDetectorRef, Component, signal } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { merge } from 'rxjs'
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { BackendService } from '../../service/backend.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrl: './email.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmailComponent {
  
  data = {
    "to": "",
    "message": "",
    "subject": ""
  }

  readonly email = new FormControl('', [Validators.required, Validators.email]);

  errorMessage = signal('');
  flag:boolean= false;

  constructor(private msg: BackendService, private snak:MatSnackBar, private cd: ChangeDetectorRef) {
    merge(this.email.statusChanges, this.email.valueChanges)
      .pipe(takeUntilDestroyed())
      .subscribe(() => this.updateErrorMessage());
  }

  submitForm() {
    console.log("Email sent from browser");
    console.log("Data: ", this.data);

    if(this.data.to=='')
    {
      this.snak.open("Email field cannot be empty!!", "OK")
      return;
    }

    this.flag= true;
    this.cd.markForCheck(); // Manually trigger change detection
    
    this.msg.sendEmail(this.data).subscribe(
      response=>{
        console.log("Response: ",response);
        this.flag=false;
        this.cd.markForCheck(); // Ensure the progress bar stops
        this.snak.open("Email sent successfully!", "OK", { duration: 3000 });
      },
      error=>{
        console.log(error);
        this.flag=false;
        this.cd.markForCheck(); // Ensure the progress bar stops
        this.snak.open("Failed to send email. Please try again.", "OK", { duration: 3000 });
      }
    )
  }

  updateErrorMessage() {
    if (this.email.hasError('required')) {
      this.errorMessage.set('You must enter a value');
    } else if (this.email.hasError('email')) {
      this.errorMessage.set('Not a valid email');
    } else {
      this.errorMessage.set('');
    }
  }
}
