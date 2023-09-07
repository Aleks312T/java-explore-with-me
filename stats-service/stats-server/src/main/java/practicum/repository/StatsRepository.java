package practicum.repository;

import practicum.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stats_dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query("SELECT NEW statsDto.StatsDto(s.app, s.uri, COUNT(DISTINCT s.ip)) " +
            "FROM Stats AS s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(DISTINCT s.ip ) DESC")
    List<StatsDto> findStatsWithoutUrisUnique(LocalDateTime start, LocalDateTime end);

    @Query("SELECT NEW statsDto.StatsDto(s.app, s.uri, COUNT(s.ip)) " +
            "FROM Stats AS s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(s.ip ) DESC")
    List<StatsDto> findStatsWithoutUris(LocalDateTime start, LocalDateTime end);

    @Query("SELECT NEW statsDto.StatsDto(s.app, s.uri, COUNT(DISTINCT s.ip)) " +
            "FROM Stats AS s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "AND s.uri IN (?3) " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(DISTINCT s.ip ) DESC")
    List<StatsDto> findStatsWithUrisUnique(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT NEW statsDto.StatsDto(s.app, s.uri, COUNT(s.ip)) " +
            "FROM Stats AS s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "AND s.uri IN (?3) " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(s.ip ) DESC")
    List<StatsDto> findStatsWithUris(LocalDateTime start, LocalDateTime end, List<String> uris);
}
