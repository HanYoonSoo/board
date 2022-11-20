package com.example.board.module.controller

import com.example.board.module.dto.UserDto
import com.example.board.module.entity.User
import com.example.board.module.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping("/apply")
    fun applyUser(@RequestBody request: UserDto): User {
        request.apply {
            return userService.applyUser(UserDto(userId, userName, userPassword))
        }
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: String) {
        return userService.deleteUser(userId)
    }
}