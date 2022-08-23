package com.example.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hola perra, esto es una API rest";
    }

    @GetMapping("/bootstrap")
    public String htmlTest(){
        return """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Bootstrap demo</title>
                  </head>
                  <body>
                  <style>
                    h1{
                        background-color : red;                     
                    }
                  </style>
                    <h1 >Hola mundo, desde Spring!</h1>
                  </body>
                </html>
                """;
    }
}
