package practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicum.model.Stats;
import practicum.model.StatsMapper;
import practicum.repository.StatsRepository;
import ru.practicum.statsdto.HitDto;
import ru.practicum.statsdto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    @Transactional
    @Override
    public HitDto hit(HitDto hitDto) {
        Stats stats = statsRepository.save(StatsMapper.toStats(hitDto));

        log.info("Hit сохранен: {}", stats);
        return StatsMapper.toHitDto(stats);
    }

    @Transactional(readOnly  = true)
    @Override
    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        List<StatsDto> statsDtos;

        if (uris == null || uris.isEmpty()) {
            if (unique) {
                statsDtos = statsRepository.findStatsWithoutUrisUnique(start, end);
            } else {
                statsDtos = statsRepository.findStatsWithoutUris(start, end);
            }
        } else {
            if (unique) {
                statsDtos = statsRepository.findStatsWithUrisUnique(start, end, uris);
            } else {
                statsDtos = statsRepository.findStatsWithUris(start, end, uris);
            }
        }

        log.info("Stats: {}", statsDtos);
        return statsDtos;
    }
}
