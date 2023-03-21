import { Component, OnInit } from '@angular/core';
import { Image } from 'src/app/Image';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-new-image',
  templateUrl: './new-image.component.html',
  styleUrls: ['./new-image.component.css']
})
export class NewImageComponent implements OnInit{

  constructor(private imageService: ImageService) {}

  ngOnInit(): void {}

  async createHandler(image: Image) {
    const formData = new FormData();
    formData.append('title', image.title);
    formData.append('image', image.image);

    await this.imageService.createImage(formData).subscribe();
  }
}
