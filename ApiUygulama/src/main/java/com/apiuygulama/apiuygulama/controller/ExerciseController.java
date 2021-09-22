package com.apiuygulama.apiuygulama.controller;

import com.apiuygulama.apiuygulama.exception.ExerciseAlreadyExistsException;
import com.apiuygulama.apiuygulama.exception.ExerciseNotFoundException;
import com.apiuygulama.apiuygulama.model.Exercise;
import com.apiuygulama.apiuygulama.repository.ExerciseRepository;
import com.apiuygulama.apiuygulama.service.ExerciseService;

import com.apiuygulama.apiuygulama.service.ExerciseSuggestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/exercise")
@Api(value = "Exercise API documentation")
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ExerciseSuggestService exerciseSuggestService;

    @GetMapping("/all")
    @ApiOperation(value = "Tüm Exercise Getirme İşlemi")
    public ResponseEntity<List<Exercise>> getExercise(@RequestParam(required = false) String name) {
        if (name == null) {
            return new ResponseEntity<>(exerciseRepository.findAll(), OK);
        } else {
            return new ResponseEntity<>(exerciseRepository.findAllByName(name), OK);
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "Exercise Ekleme İşlemi")
    public ResponseEntity<?> addExercise(@Valid @RequestBody Exercise exercise){
        return new ResponseEntity<>(exerciseService.addExercisesService(exercise), OK);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Foods Güncelleme İşlemi")
    public ResponseEntity<String> updateExercise(@RequestParam(required = false) int id, @RequestBody Exercise exercise){
        exerciseService.updateExerciseById(id, exercise);
        return  new ResponseEntity<>(OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Exercise Silme İşlemi")
    public ResponseEntity<?> deleteExercise(@RequestParam(required = false) Integer id){
        exerciseRepository.deleteById(id);
        return  new ResponseEntity<>(OK);
    }

    @GetMapping(value = "/exercisepage")
    public ResponseEntity<Page<Exercise>> fetchExercise(
            @RequestParam(required = false, name = "exerciseName") String exerciseName,
            @RequestParam(defaultValue = "0", name = "page") int page) {
        // @RequestParam(defaultValue = "5", name = "size") int size
        int size = 10;
        return new ResponseEntity<>(exerciseService.getAllPExercise(exerciseName, page, size), OK);
    }

    @GetMapping(value = "/exercisepagex/{exerciseName}")
    public ResponseEntity<Exercise> fetchExercise(
            @PathVariable(required = true, name = "exerciseName") String exerciseName) {
        return new ResponseEntity<>(exerciseService.getExercisesByName(exerciseName), OK);
    }

    @GetMapping(value = "/getExercise")
    public ResponseEntity<?> exerciseSuggest (@RequestParam Double calorie){
        return new ResponseEntity<>(exerciseSuggestService.createExercise(calorie), OK);
    }

    @ExceptionHandler(ExerciseNotFoundException.class)
    public ResponseEntity<String> handleExerciseNotFoundException(ExerciseNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(ExerciseAlreadyExistsException.class)
    public ResponseEntity<String> handleExerciseAlreadyExistsException(ExerciseAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
}