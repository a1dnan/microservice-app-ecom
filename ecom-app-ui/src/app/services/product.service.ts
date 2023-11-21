import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private readonly api = "http://localhost:9999/inventory-service/products";

  constructor(private http: HttpClient) { }

  getProducts():Observable<any>{
    return this.http.get(this.api);
  }
}
