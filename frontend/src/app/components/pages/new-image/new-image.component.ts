import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ImageUpload } from 'src/app/ImageUpload';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-new-image',
  templateUrl: './new-image.component.html',
  styleUrls: ['./new-image.component.css']
})
export class NewImageComponent implements OnInit{

  constructor(
    private imageService: ImageService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  async createHandler(image: ImageUpload) {
    const formData = new FormData();

    formData.append('imageTitle', image.imageTitle);
    formData.append('imageName', image.imageName);

    await this.imageService.createImage(formData).subscribe();

    this.router.navigate(['/']);
  }
}
