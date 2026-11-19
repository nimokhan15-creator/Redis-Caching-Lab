package com.example.rediscaching;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        Product product = productService.getProduct(1);

        System.out.println("Product retrieved without Redis:");
        System.out.println(product);
    }
}