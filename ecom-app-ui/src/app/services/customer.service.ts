import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private readonly api = "http://localhost:9999/customer-service/customers";

  constructor(private http: HttpClient) { }

  getCustomers():Observable<any>{
    return this.http.get(this.api);
  }
}
