import { Component } from '@angular/core';
import { FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-new-image',
  templateUrl: './new-image.component.html',
  styleUrls: ['./new-image.component.css']
})
export class NewImageComponent {
  title: string = '';
  imageURL: string = '';

  uploadForm: FormGroup;
  constructor(public fb: FormBuilder) {
    this.uploadForm = this.fb.group({
      image: [null],
    })
  }

  showPreview(event: any) {
    const file = (event.target as HTMLInputElement).files![0];
    this.uploadForm.patchValue({
      image: file
    });
    this.uploadForm.get('image')!.updateValueAndValidity()
    const reader = new FileReader();
    reader.onload = () => {
      this.imageURL = reader.result as string;
    }
    reader.readAsDataURL(file)
  }
}
