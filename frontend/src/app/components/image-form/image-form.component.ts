import { Component, EventEmitter, OnInit, Output } from '@angular/core';

import { FormBuilder, FormControl, FormGroup} from "@angular/forms";
import { ImageUpload } from 'src/app/ImageUpload';

@Component({
  selector: 'app-image-form',
  templateUrl: './image-form.component.html',
  styleUrls: ['./image-form.component.css']
})
export class ImageFormComponent implements OnInit{
  @Output() onSubmit = new EventEmitter<ImageUpload>();

  imageForm!: FormGroup;

  title: string = '';
  imageURL: string = '';

  ngOnInit(): void {
    this.imageForm = new FormGroup({
      imageTitle: new FormControl(''),
      imageName: new FormControl('')
    });
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    this.imageForm.patchValue({ imageName : file});
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