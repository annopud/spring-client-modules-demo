package dev.annopud.first_spring_clients.user;

public record Address(
    String street,
    String suite,
    String city,
    String zipcode,
    Geo geo
) {
}
