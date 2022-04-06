package com.example.demo.controllers;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import com.example.demo.models.Person;
import com.example.demo.services.RickAndMortyService;
import com.example.demo.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ejercicio {

    @Autowired
    RickAndMortyService rickAndMortyService;

    // http://localhost:8080/
    @GetMapping("/")
    public String greet() {
        return "Bienvenido al servidor backend";
    }

    // http://localhost:8080/aleatorio
    @GetMapping("/aleatorio")
    public String numeroRandom() {
        long r = Math.round(Math.random() * 100);
        return r + "";
    }

    // http://localhost:8080/palindromo/ana
    @GetMapping("/palindromo/{name}")
    public String isPalindrome(@PathVariable String name) {
        boolean palindrome = Utils.isPalindrome(name);
        return palindrome ? "Si es palíndromo" : "No es palíndromo";
    }

    // http://localhost:8080/sumar?num1=5&num2=2
    @GetMapping("/sumar")
    public String add(@RequestParam String num1, @RequestParam String num2) {
        int resultado = Integer.parseInt(num1) + Integer.parseInt(num2);
        Object params[] = { num1, num2, resultado };
        return MessageFormat.format("La suma de {0} y {1} es {2}", params);
    }

    // http://localhost:8080/saveProductOnDisk
    @PostMapping("/saveProductOnDisk")
    public String saveProductOnDisk(@RequestParam Map<String, String> body) {
        // obtener los datos un producto {articulo, precio}
        String articleValue = body.get("article");
        String priceValue = body.get("price");
        // valido que el articulo precio no sean vacios
        if (articleValue.equals("") || priceValue.equals("")) {
            return "Error: Datos incorrectos";
        }
        if (Integer.parseInt(priceValue) < 0) {
            return "Error: El precio es negativo";
        }
        // guardo en el disco duro esa informacion
        try {
            Utils.save("datos.txt", articleValue + "," + priceValue + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al guardar en disco";
        }

        // devuelvo un msg al cliente "produc guardado correct"
        return "Producto guardado correctamente";
    }

    @DeleteMapping("/removeFile")
    public String removeFile() {
        boolean result = Utils.remove("datos.txt");
        return result ? "Borrado correcto" : "No se puede borrar";
    }

    // http://localhost:8080/rickandmorty/random
    @GetMapping("/rickandmorty/random")
    public String getRickAndMortyRandomCharacter(){
        Person c = rickAndMortyService.getCharacterFromAPI();
        return "<img src='" + c.image + "'/>";
        // return MessageFormat.format("<img src = '(0)'/>", c.image);
    }
}
