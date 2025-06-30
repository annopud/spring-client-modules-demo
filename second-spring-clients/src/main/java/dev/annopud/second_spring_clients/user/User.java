package dev.annopud.second_spring_clients.user;

public record User(
    int id,
    String name,
    String username,
    String email,
    Address address,
    String phone,
    String website,
    Company company
) {
}
