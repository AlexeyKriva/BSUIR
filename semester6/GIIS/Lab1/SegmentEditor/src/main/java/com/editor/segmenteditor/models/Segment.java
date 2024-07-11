package com.editor.segmenteditor.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Segment {
    @Min(value = 0, message = "Координата должна быть больше 0")
    @Max(value = 999, message = "Координата не должна превышать 999")
    private int startX;
    @Min(value = 0, message = "Координата должна быть больше 0")
    @Max(value = 999, message = "Координата не должна превышать 999")
    private int startY;
    @Min(value = 0, message = "Координата должна быть больше 0")
    @Max(value = 999, message = "Координата не должна превышать 999")
    private int finishX;
    @Min(value = 0, message = "Координата должна быть больше 0")
    @Max(value = 999, message = "Координата не должна превышать 999")
    private int finishY;
    @NotNull(message = "Выберите алгоритм")
    private Algorithm algorithm;
}