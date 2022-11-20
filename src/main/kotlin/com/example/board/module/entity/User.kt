package com.example.board.module.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "User")
class User(
        @Id
        val userId: String,
        val userName: String,
        @JsonIgnore()
        val userPassword: String,

        )