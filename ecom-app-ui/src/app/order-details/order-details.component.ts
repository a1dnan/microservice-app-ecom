import {Component, OnInit} from '@angular/core';
import {OrdersService} from "../services/orders.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit{

  orderDetails : any;
  orderId !: number;

  constructor(private orderService: OrdersService,
              private route: ActivatedRoute,
              private router: Router) {
    this.orderId = route.snapshot.params['orderId'];
  }

  ngOnInit(): void {
    this.orderService.getOrderDetails(this.orderId).subscribe({
      next : (data)=>{
        this.orderDetails = data;
      },
      error : (err)=>{

      }
    })
  }
}
