import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {CustomerService} from "../services/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit{
  customers : any;

  constructor(private customerService: CustomerService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe({
      next : (data)=>{
        this.customers = data;
      },
      error : (err)=>{

      }
    })
  }
  getOrders(c: any) {
      this.router.navigateByUrl("/orders/"+c.id);
  }
}
