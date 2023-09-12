import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.statsdto.HitDto;
import ru.practicum.utils.TimeFormatUtil;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class StatsClient extends BaseClient {
    @Autowired
    public StatsClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> hit(HitDto hitDto) {
        log.info("StatsClient hit: {}", hitDto);
        return post(hitDto);
    }

    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (start == null) {
            throw new IllegalArgumentException("Некорректное начало промежутка.");
        }
        if (end == null) {
            throw new IllegalArgumentException("Некорректный конец промежутка.");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Начало промежутка не может быть позже конца.");
        }

        log.info("StatsClient getStats: \nstart: {}, end: {}, uris: {}, unique: {}", start, end, uris, unique);

        Map<String, Object> parameters = Map.of(
                "start", start.format(TimeFormatUtil.TIMESTAMP_FORMATTER),
                "end", end.format(TimeFormatUtil.TIMESTAMP_FORMATTER)
        );

        StringBuilder path = new StringBuilder("/stats?start={start}&end={end}");

        Optional.ofNullable(uris)
                .orElse(Collections.emptyList())
                .forEach(uri -> path.append("&uris=").append(uri));

        Optional.ofNullable(unique)
                .ifPresent(u -> path.append("&unique=").append(u));

        return get(path.toString(), parameters);
    }
}