import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  private readonly api = "http://localhost:9999/order-service/orders/search/byCustomerId?customerId=";
  private readonly urlApi = "http://localhost:9999/order-service/fullOrder";

  constructor(private http: HttpClient) { }

  getOrdersByCustomerId(id:number):Observable<any>{
    return this.http.get(`${this.api}${id}`);
  }

  getOrderDetails(id:number):Observable<any>{
    return this.http.get(`${this.urlApi}/${id}`);
  }

}
