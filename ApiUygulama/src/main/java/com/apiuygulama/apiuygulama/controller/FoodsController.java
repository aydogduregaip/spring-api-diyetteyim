package com.apiuygulama.apiuygulama.controller;

import com.apiuygulama.apiuygulama.exception.FoodAlreadyExistsException;
import com.apiuygulama.apiuygulama.exception.FoodNotFoundException;
import com.apiuygulama.apiuygulama.exception.FoodTypeNotFoundException;
import com.apiuygulama.apiuygulama.model.*;
import com.apiuygulama.apiuygulama.payload.request.FoodRequest;
import com.apiuygulama.apiuygulama.repository.FoodCategoryRepository;
import com.apiuygulama.apiuygulama.repository.FoodRepository;
import com.apiuygulama.apiuygulama.service.FoodsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import liquibase.pro.packaged.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/foods")
@Api(value = "Foods API documentation")
public class FoodsController {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodCategoryRepository foodCategoryRepository;

    @Autowired
    FoodsService foodsService;

    @GetMapping("/get")
    @ApiOperation(value = "Tüm Foods listesini veya istenilen değeri getirir.")
    public ResponseEntity<List<Foods>> getFoods(@RequestParam(required = false) String name, @RequestParam(required = false) Integer id) {
        if(name == null){
            return new ResponseEntity<>(foodRepository.findAll(), OK) ;
        }
        else {
            return new ResponseEntity<>(foodRepository.findAllByName(name), OK);
        }

    }

    @PostMapping("/add")
    @ApiOperation(value = "Foods Ekleme İşlemi")
    public ResponseEntity<?> addFoods(@Valid @RequestBody FoodRequest foodRequest){
        return new ResponseEntity<>(foodsService.addFoodsService(foodRequest), OK);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Foods Güncelleme İşlemi")
    public ResponseEntity<?> updateFoods(@RequestParam(required = false) int id, @RequestBody FoodRequest foodRequest){
        foodsService.updateFoodById(id, foodRequest);
        return  new ResponseEntity<>(OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Foods Silme İşlemi")
    public ResponseEntity<?> deleteFoods(@RequestParam(required = true) Integer id){
        foodRepository.deleteById(id);
        return  new ResponseEntity<>(OK);
    }

    @GetMapping(value = "/foodpage")
    public ResponseEntity<Page<Foods>> fetchFoods(
            @RequestParam(required = false, name = "foodsName") String foodsName,
            @RequestParam(defaultValue = "0", name = "page") int page) {
        // @RequestParam(defaultValue = "5", name = "size") int size
        int size = 10;
        return new ResponseEntity<>(foodsService.getAllPFoods(foodsName, page, size), HttpStatus.OK);
    }

    @GetMapping(value = "/foodpagex/{foodsName}")
    public ResponseEntity<Foods> fetchFoods(
            @PathVariable(required = true, name = "foodsName") String foodsName) {
        return new ResponseEntity<>(foodsService.getFoodsByName(foodsName), HttpStatus.OK);
    }

    @ExceptionHandler(FoodAlreadyExistsException.class)
    public ResponseEntity<String> handleFoodAlreadyExistsException(FoodAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }

    @ExceptionHandler(FoodNotFoundException.class)
    public ResponseEntity<String> handleFoodNotFoundException(FoodNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(FoodTypeNotFoundException.class)
    public ResponseEntity<String> handleFoodTypeNotFoundException(FoodTypeNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
}
