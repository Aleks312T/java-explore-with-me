package ru.practicum.request.model;

public enum RequestStatuses {
    PENDING,
    CONFIRMED,
    REJECTED, //Отклонено владельцем события
    CANCELED //Отменено пользователем
}