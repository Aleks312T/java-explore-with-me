package controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import exception.ValidationException;
import service.StatsService;
import statsDto.HitDto;
import statsDto.StatsDto;
import utils.TimeFormatUtil;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @PostMapping("/hit")
    public HitDto hit(@Valid @RequestBody HitDto hitDto) {
        log.info("hit stats {}", hitDto);
        return statsService.hit(hitDto);
    }

    @GetMapping("/stats")
    public List<StatsDto> getStats(@RequestParam @DateTimeFormat(pattern = TimeFormatUtil.TIMESTAMP_FORMAT)
                                       LocalDateTime start,
                                   @RequestParam @DateTimeFormat(pattern = TimeFormatUtil.TIMESTAMP_FORMAT)
                                       LocalDateTime end,
                                   @RequestParam(required = false) List<String> uris,
                                   @RequestParam(defaultValue = "false") boolean unique
    ) {
        if (start.isAfter(end)) {
            throw new ValidationException("Начало промежутка не может быть позже конца.");
        }

        log.info("StatsController getStats: start: {}, end: {}, uris: {}, unique: {}", start, end, uris, unique);
        return statsService.getStats(start, end, uris, unique);
    }
}
