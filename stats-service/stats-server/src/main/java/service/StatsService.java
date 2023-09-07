package service;

import statsDto.HitDto;
import statsDto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {

    HitDto hit(HitDto hitDto);

    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
