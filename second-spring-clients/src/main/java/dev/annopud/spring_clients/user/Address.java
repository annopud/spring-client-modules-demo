package dev.annopud.spring_clients.user;

public record Address(
    String street,
    String suite,
    String city,
    String zipcode,
    Geo geo
) {
}
