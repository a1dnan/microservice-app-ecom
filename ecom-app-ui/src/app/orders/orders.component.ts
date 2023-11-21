import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../services/customer.service";
import {ActivatedRoute, Router} from "@angular/router";
import {OrdersService} from "../services/orders.service";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit{
  orders : any;
  customerId !: number;

  constructor(private orderService: OrdersService,
              private route: ActivatedRoute,
              private router: Router) {
    this.customerId = route.snapshot.params['customerId'];
  }

  ngOnInit(): void {
    this.orderService.getOrdersByCustomerId(this.customerId).subscribe({
      next : (data)=>{
        this.orders = data;
      },
      error : (err)=>{

      }
    })
  }

  getOrderDetails(o : any) {
    this.router.navigateByUrl("/order-details/"+o.id);
  }
}
