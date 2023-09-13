package ru.practicum.comment.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.comment.dto.CommentFullResponseDto;
import ru.practicum.comment.dto.CommentRequestDto;
import ru.practicum.comment.dto.CommentShortResponseDto;
import ru.practicum.comment.model.Comment;
import ru.practicum.comment.model.CommentState;
import ru.practicum.event.mapper.EventMapper;
import ru.practicum.event.model.Event;
import ru.practicum.user.mapper.UserMapper;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;

import static ru.practicum.utils.EventTimeFormatConstants.TIMESTAMP_FORMATTER;

@UtilityClass
public class CommentMapper {
    public static CommentShortResponseDto toCommentShortDto(Comment comment) {
        return CommentShortResponseDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(UserMapper.toUserShortDto(comment.getAuthor()))
                .event(comment.getEvent().getId())
                .publishedDate(comment.getPublishedDate().format(TIMESTAMP_FORMATTER))
                .build();
    }

    public static CommentFullResponseDto toCommentFullDto(Comment comment) {
        return CommentFullResponseDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(UserMapper.toUserShortDto(comment.getAuthor()))
                .event(EventMapper.toEventShortDto(comment.getEvent()))
                .state(comment.getState().toString())
                .createdDate(comment.getCreatedDate().format(TIMESTAMP_FORMATTER))
                .publishedDate(comment.getPublishedDate() != null
                        ? comment.getPublishedDate().format(TIMESTAMP_FORMATTER) : null)
                .build();
    }

    public static Comment toComment(CommentRequestDto commentRequestDto, User user, Event event) {
        return Comment.builder()
                .text(commentRequestDto.getText())
                .author(user)
                .event(event)
                .createdDate(LocalDateTime.now())
                .state(CommentState.PENDING)
                .build();
    }
}

