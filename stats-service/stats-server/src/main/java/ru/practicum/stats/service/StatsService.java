package ru.practicum.stats.service;

import ru.practicum.statsdto.HitDto;
import ru.practicum.statsdto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {

    HitDto hit(HitDto hitDto);

    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
