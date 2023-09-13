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
public class CommentFullResponseDto {
    Long id;
    String text;
    UserShortResponseDto author; //Владелец комментария
    EventShortResponseDto event;
    String state;
    String createdDate;
    String publishedDate;
}
