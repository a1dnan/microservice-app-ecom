import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit{

  products : any;

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.productService.getProducts().subscribe({
      next : (data)=>{
        this.products = data;
      },
      error : (err)=>{

      }
    })
  }
}
