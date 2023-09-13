package ru.practicum.comment.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.comment.dto.ReportRequestDto;
import ru.practicum.comment.dto.ReportResponseDto;
import ru.practicum.comment.model.Comment;
import ru.practicum.comment.model.Report;
import ru.practicum.event.mapper.EventMapper;
import ru.practicum.event.model.Event;
import ru.practicum.user.mapper.UserMapper;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;

import static ru.practicum.utils.EventTimeFormatConstants.TIMESTAMP_FORMATTER;

@UtilityClass
public class ReportMapper {
    public static ReportResponseDto toReportDto(Report report) {
        return ReportResponseDto.builder()
                .id(report.getId())
                .text(report.getText())
                .owner(UserMapper.toUserShortDto(report.getOwner()))
                .event(EventMapper.toEventShortDto(report.getEvent()))
                .comment(CommentMapper.toCommentShortDto(report.getComment()))
                .createdDate(report.getCreatedDate().format(TIMESTAMP_FORMATTER))
                .build();
    }

    public static Report toReport(ReportRequestDto reportDto, Comment comment, User user, Event event) {
        return Report.builder()
                .text(reportDto.getText())
                .owner(user)
                .event(event)
                .comment(comment)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
