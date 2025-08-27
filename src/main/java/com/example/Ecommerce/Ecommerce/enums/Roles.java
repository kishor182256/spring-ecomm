package com.example.Ecommerce.Ecommerce.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Roles {

    USER(1),
    ADMIN(2),
    MODERATOR(3),
    SUPER_ADMIN(4);

    private final int id;

}
