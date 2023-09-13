package ru.practicum.compilation.service;

import ru.practicum.compilation.dto.CompilationCreateRequestDto;
import ru.practicum.compilation.dto.CompilationResponseDto;
import ru.practicum.compilation.dto.CompilationUpdateRequestDto;

import java.util.List;

public interface CompilationService {
    List<CompilationResponseDto> getCompilations(Boolean pinned, Integer from, Integer size);

    CompilationResponseDto getCompilationById(Long compId);

    CompilationResponseDto createCompilation(CompilationCreateRequestDto compilationDto);

    void deleteCompilation(Long compId);

    CompilationResponseDto updateCompilation(CompilationUpdateRequestDto compilationDto, Long compId);
}
