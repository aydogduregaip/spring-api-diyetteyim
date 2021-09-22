package com.apiuygulama.apiuygulama.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class JExercise implements Serializable {
    public Exercise excersize1;
    public long minExercise1;
    public Exercise excersize2;
    public long minExercise2;
    public Exercise excersize3;
    public long minExercise3;

    public JExercise(
                @NotBlank Exercise excersize1,
                @NotBlank long minExercise1,
                @NotBlank Exercise excersize2,
                @NotBlank long minExercise2,
                @NotBlank Exercise excersize3,
                @NotBlank long minExercise3) {
        this.excersize1 = excersize1;
        this.minExercise1 = minExercise1;
        this.excersize2 = excersize2;
        this.minExercise2 = minExercise2;
        this.excersize3 = excersize3;
        this.minExercise3 = minExercise3;
    }
}