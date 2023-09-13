package ru.practicum.compilation.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;
import ru.practicum.utils.CompilationSizeConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationCreateRequestDto {
    @UniqueElements
    List<Long> events;

    Boolean pinned = false;

    @NotBlank
    @Size(min = CompilationSizeConstants.TITLE_SIZE_MIN, max = CompilationSizeConstants.TITLE_SIZE_MAX, message = "Title length should be between 1 and 50 characters")
    String title;
}