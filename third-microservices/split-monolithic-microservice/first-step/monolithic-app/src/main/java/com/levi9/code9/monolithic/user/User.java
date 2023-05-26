package com.levi9.code9.monolithic.user;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "receive_update")
    private boolean receiveUpdate;
}
