package com.pinapp.notifications_api.notification.infrastructure.database;

import lombok.Data;

import java.time.Instant;

@Data
public class UserEntity {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;

}
