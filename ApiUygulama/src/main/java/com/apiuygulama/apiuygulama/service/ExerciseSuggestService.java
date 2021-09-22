package com.apiuygulama.apiuygulama.service;

import com.apiuygulama.apiuygulama.model.Exercise;
import com.apiuygulama.apiuygulama.model.JExercise;
import com.apiuygulama.apiuygulama.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ExerciseSuggestService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    private final Random rnd = new Random();

    public List<Exercise> exerciseSuggest(Double calorie){
        try{
            Exercise exercise1, exercise2, exercise3;
            exercise1 = rndExercise(getExercise(calorie));
            exercise2 = rndExercise(getExercise(calorie));
            exercise3 = rndExercise(getExercise(calorie));

            if((exercise1.name).equals(exercise2.name)){
                return exerciseSuggest(calorie);
            }
            else if((exercise2.name).equals(exercise3.name)){
                return exerciseSuggest(calorie);
            }
            else if((exercise1.name).equals(exercise3.name)){
                return exerciseSuggest(calorie);
            }

            return new ArrayList<>(Arrays.asList(exercise1, exercise2, exercise3));
        }
        catch (RuntimeException exception) {
            throw new RuntimeException("Zaman Aşımı Tekrar Deneyiniz...");
        } catch (StackOverflowError exception) {
            throw new StackOverflowError("Zaman Aşımı Tekrar Deneyiniz...");
        }
    }

    public JExercise createExercise(Double calorie) {
        List<Exercise> exlist = exerciseSuggest(calorie);
        Exercise exercise1, exercise2, exercise3;
        Double minExercise1, minExercise2, minExercise3;

        Long minExercise1L, minExercise2L, minExercise3L;

        exercise1 = exlist.get(0);
        exercise2 = exlist.get(1);
        exercise3 = exlist.get(2);

        minExercise1 = calorie / exercise1.calorie;
        minExercise2 = calorie / exercise2.calorie;
        minExercise3 = calorie / exercise3.calorie;

        minExercise1L = Math.round(minExercise1);
        minExercise2L = Math.round(minExercise2);
        minExercise3L = Math.round(minExercise3);

        return new JExercise(exercise1, minExercise1L, exercise2,
                minExercise2L, exercise3, minExercise3L);
    }

    public Exercise rndExercise(List<Exercise> exercises) {
        return exercises.get(rnd.nextInt(exercises.size()));
    }

    public List<Exercise> getExercise(Double calorie){
        return exerciseRepository.findByCalorieLessThanEqual(
                calorie
        );
    }
}
