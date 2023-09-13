package ru.practicum.comment.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.event.dto.EventShortResponseDto;
import ru.practicum.user.dto.UserShortResponseDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportResponseDto {
    Long id;
    String text;
    UserShortResponseDto owner; //Владелец жалобы
    EventShortResponseDto event;
    CommentShortResponseDto comment;
    String createdDate;
}
