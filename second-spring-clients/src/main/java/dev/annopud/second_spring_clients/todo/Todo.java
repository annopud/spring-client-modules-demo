package dev.annopud.second_spring_clients.todo;

public record Todo(
    Integer userId,
    Integer id,
    String title,
    Boolean completed
) {
}
