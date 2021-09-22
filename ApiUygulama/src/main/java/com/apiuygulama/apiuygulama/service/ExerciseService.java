package com.apiuygulama.apiuygulama.service;

import com.apiuygulama.apiuygulama.exception.*;
import com.apiuygulama.apiuygulama.model.Exercise;
import com.apiuygulama.apiuygulama.model.Foods;
import com.apiuygulama.apiuygulama.repository.ExercisePageRepository;
import com.apiuygulama.apiuygulama.repository.ExerciseRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExercisePageRepository exercisePageRepository;

    public Exercise getExerciseById(int id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException("Error: Exercise is not found: " + id));
    }

    public Exercise addExercisesService(Exercise exercise){
        getExerciseByName(exercise.getName());

        Exercise model_exercise = new Exercise(
                exercise.getName(),
                exercise.getCalorie(),
                exercise.getImage_url());

        return (exerciseRepository.save(model_exercise));
    }

    public Exercise getExerciseByName(String name){
        if (exerciseRepository.findByName(name).isPresent())
            throw new ExerciseAlreadyExistsException("Error: Exercise is already exists: " + name);
        else
            return null;
    }

    public void updateExerciseById(int id, Exercise exercise) {
        Exercise oldExercise = getExerciseById(id);

        oldExercise.setName(exercise.getName());
        oldExercise.setCalorie(exercise.getCalorie());
        oldExercise.setImage_url(exercise.getImage_url());

        exerciseRepository.save(oldExercise);
    }

    public Page<Exercise> getAllPExercise(String foodsName, int page, int size) {
        Pageable pageable = createPageable(page, size);
        if (StringUtils.isEmpty(foodsName)) {
            return exercisePageRepository.findAll(pageable);
        }
        return exercisePageRepository.findByNameContaining(foodsName, pageable);
    }

    private Pageable createPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Pageable pageable1 = PageRequest.of(page, size);
        Pageable pageable2 = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return pageable;
    }

    public Exercise getExercisesByName(String name){
        return exercisePageRepository.findByNameIgnoreCase(name);
    }
}
