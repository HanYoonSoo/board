package com.example.board.module.repository

import com.example.board.module.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>{
}