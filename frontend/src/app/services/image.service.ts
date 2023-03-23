import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Image } from '../Image';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private baseApiUrl = environment.baseApiUrl;
  private apiUrl = `${this.baseApiUrl}/all`;

  constructor(private http: HttpClient) { }

  getImage(): Observable<Image[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      map(images => images.map(image => {
        return {
          image: {
            id: image.image.id,
            imageName: image.image.imageName,
            imageTitle: image.image.imageTitle
          },
          imageData: image.imageData,
        };
      }))
    );
  }

  createImage(formData: FormData): Observable<FormData> {
    return this.http.post<FormData>(this.baseApiUrl, formData);
  }
}