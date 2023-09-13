package ru.practicum.event.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.category.model.Category;
import ru.practicum.comment.dto.CommentShortResponseDto;
import ru.practicum.event.dto.EventCreateRequestDto;
import ru.practicum.event.dto.EventFullResponseDto;
import ru.practicum.event.dto.EventShortResponseDto;
import ru.practicum.event.model.Event;
import ru.practicum.event.model.enums.EventState;
import ru.practicum.location.mapper.LocationMapper;
import ru.practicum.location.model.Location;
import ru.practicum.user.mapper.UserMapper;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.utils.EventTimeFormatConstants.TIMESTAMP_FORMATTER;

@UtilityClass
public class EventMapper {
    public static EventShortResponseDto toEventShortDto(Event event) {
        return EventShortResponseDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toCategoryDto(event.getCategory()))
                .eventDate(event.getEventDate().format(TIMESTAMP_FORMATTER))
                .id(event.getId())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .build();
    }

    public static EventShortResponseDto toEventShortDto(Event event, long confirmedRequests, long views) {
        return EventShortResponseDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toCategoryDto(event.getCategory()))
                .confirmedRequests(confirmedRequests)
                .eventDate(event.getEventDate().format(TIMESTAMP_FORMATTER))
                .id(event.getId())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(views)
                .build();
    }

    public static Event toEvent(EventCreateRequestDto eventDto, Category category, User initiator, Location location,
                                LocalDateTime createdOn, EventState eventState) {
        return Event.builder()
                .title(eventDto.getTitle())
                .annotation(eventDto.getAnnotation())
                .category(category)
                .description(eventDto.getDescription())
                .createdOn(createdOn)
                .eventDate(LocalDateTime.parse(eventDto.getEventDate(), TIMESTAMP_FORMATTER))
                .initiator(initiator)
                .location(location)
                .paid(eventDto.getPaid())
                .participantLimit(eventDto.getParticipantLimit())
                .requestModeration(eventDto.getRequestModeration())
                .state(eventState)
                .build();
    }

    public static EventFullResponseDto toEventFullDto(Event event) {
        return EventFullResponseDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toCategoryDto(event.getCategory()))
                .confirmedRequests(0L)
                .createdOn(event.getCreatedOn().format(TIMESTAMP_FORMATTER))
                .description(event.getDescription())
                .eventDate(event.getEventDate().format(TIMESTAMP_FORMATTER))
                .id(event.getId())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .location(LocationMapper.toLocationDto(event.getLocation()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .requestModeration(event.getRequestModeration())
                .state(event.getState().toString())
                .title(event.getTitle())
                .views(0L)
                .publishedOn(event.getPublishedOn() != null ? event.getPublishedOn().format(TIMESTAMP_FORMATTER) : null)
                .build();
    }

    public static EventFullResponseDto toEventFullDto(Event event,
                                                      long confirmedRequests,
                                                      long views,
                                                      List<CommentShortResponseDto> comments) {
        return EventFullResponseDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toCategoryDto(event.getCategory()))
                .confirmedRequests(confirmedRequests)
                .createdOn(event.getCreatedOn().format(TIMESTAMP_FORMATTER))
                .description(event.getDescription())
                .eventDate(event.getEventDate().format(TIMESTAMP_FORMATTER))
                .id(event.getId())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .location(LocationMapper.toLocationDto(event.getLocation()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .requestModeration(event.getRequestModeration())
                .state(event.getState().toString())
                .title(event.getTitle())
                .views(views)
                .publishedOn(event.getPublishedOn() != null ? event.getPublishedOn().format(TIMESTAMP_FORMATTER) : null)
                .comments(comments)
                .build();
    }
}
