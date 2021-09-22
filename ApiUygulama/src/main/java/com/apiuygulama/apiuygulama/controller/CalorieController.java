package com.apiuygulama.apiuygulama.controller;

import com.apiuygulama.apiuygulama.payload.request.CalorieRequest;
import com.apiuygulama.apiuygulama.repository.FoodRepository;
import com.apiuygulama.apiuygulama.service.CalorieService;
import com.apiuygulama.apiuygulama.service.OptionalMenuService;
import com.apiuygulama.apiuygulama.service.SuggestionService;
import com.apiuygulama.apiuygulama.service.SuggestionService2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/calorie")
@Api(value = "Calorie API documentation")
public class CalorieController {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private SuggestionService2 suggestionService2;

    @Autowired
    private CalorieService calorieService;

    @Autowired
    private OptionalMenuService optionalMenuService;

    Integer diabetes = 0;
    private Double kalori = 0.0;

    @ApiOperation(value = "Girilen değerlere göre kalori hesaplar.")
    @PostMapping("/calculate")
    public ResponseEntity<?> cCalorie(@RequestBody CalorieRequest newCalorieRequest, @RequestParam Integer diabetes) {
        this.diabetes = diabetes;
        kalori = calorieService.kaloriHesap(
                newCalorieRequest.getCinsiyet(),
                newCalorieRequest.getKilo(),
                newCalorieRequest.getBoy(),
                newCalorieRequest.getYas(),
                newCalorieRequest.getAktivite(),
                newCalorieRequest.getKilotype());

        kalori = Math.ceil(kalori);

        return new ResponseEntity<>(kalori, OK);
    }

    @ApiOperation(value = "Girilen değerlere göre menu hesaplar.")
    @GetMapping("/getmenu")
    public ResponseEntity<?> getMenu(@RequestParam String cinsiyet) {
        return new ResponseEntity<>(suggestionService.createMenu(kalori, cinsiyet, diabetes), OK);
    }

    @ApiOperation(value = "Girilen değerlere göre menu hesaplar.")
    @GetMapping("/getmenu2")
    public ResponseEntity<?> getMenu2(@RequestParam String cinsiyet) {
        return new ResponseEntity<>(suggestionService2.createMenu2(kalori, cinsiyet, diabetes), OK);
    }

    @ApiOperation(value = "Girilen değerlere göre kalori hesaplar.")
    @GetMapping("/getMenu3")
    public ResponseEntity<?> getOptionalMenu(@RequestParam(required = false) String term) {
        return new ResponseEntity<>(foodRepository.findAllByNameIgnoreCaseContaining(term), OK);
    }

    @ApiOperation(value = "Girilen değerlere göre kalori hesaplar.")
    @GetMapping("/getMenu4")
    public ResponseEntity<?> getOptionalMenu2(@RequestParam String cinsiyet) {
        return new ResponseEntity<>(optionalMenuService.createMenu(kalori, cinsiyet, diabetes), OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(StackOverflowError.class)
    public ResponseEntity<String> handleStackOverflowError(StackOverflowError ex) {
        return new ResponseEntity<>(ex.getMessage(), SERVICE_UNAVAILABLE);
    }
}