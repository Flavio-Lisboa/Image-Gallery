import { Component, OnInit } from '@angular/core';
import { ImageService } from 'src/app/services/image.service';
import { Image } from 'src/app/Image';
import { environment } from 'src/environments/environment';
import { ActivatedRoute } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit   {

  allImages: Image[] = [];
  images: Image[] = [];
  baseApiUrl = environment.baseApiUrl;

  constructor(
    private route: ActivatedRoute,
    private imageService: ImageService
  ) {}

  ngOnInit() {
    this.imageService.getImage().subscribe(images => {
      this.allImages = images;
      this.images = images;
    });
  }

  search(e: Event): void {
    const target = e.target as HTMLInputElement;
    const value = target.value.toLowerCase();

    this.images = this.allImages.filter((image) => {
      return image.image.imageTitle.toLowerCase().includes(value);
    });
  }
}

  
