package com.example.board.module.dto

import com.example.board.module.entity.Board
import com.example.board.module.entity.Comment

data class TestDto(
    val title : String,
    val content: String,
    val comment: List<TCommentDto>
)

data class TCommentDto(
    val content: String
){
    companion object{
        fun toDto(comment: Comment) : TCommentDto{
            return TCommentDto(comment.content)
        }
    }
}
