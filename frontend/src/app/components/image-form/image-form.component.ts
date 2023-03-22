import { Component, EventEmitter, OnInit, Output } from '@angular/core';

import { FormBuilder, FormControl, FormGroup} from "@angular/forms";
import { Image } from 'src/app/Image';

@Component({
  selector: 'app-image-form',
  templateUrl: './image-form.component.html',
  styleUrls: ['./image-form.component.css']
})
export class ImageFormComponent implements OnInit{
  @Output() onSubmit = new EventEmitter<Image>();

  imageForm!: FormGroup;

  title = new FormControl('');

  imageURL: string = '';

  ngOnInit(): void {
    this.imageForm = new FormGroup({
      title: new FormControl(''),
      image: new FormControl('')
    });
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];

    this.imageForm.patchValue({ image: file});
  }

  submit() {
    this.onSubmit.emit(this.imageForm.value);
  }

  
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
