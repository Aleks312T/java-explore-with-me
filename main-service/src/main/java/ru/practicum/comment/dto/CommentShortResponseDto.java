package ru.practicum.comment.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.user.dto.UserShortResponseDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentShortResponseDto {
    Long id;
    String text;
    UserShortResponseDto author; //Владелец комментария
    Long event;
    String publishedDate;
}
