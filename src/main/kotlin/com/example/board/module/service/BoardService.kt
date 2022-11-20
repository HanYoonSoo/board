package com.example.board.module.service

import com.example.board.module.dto.BoardDto
import com.example.board.module.dto.BoardModifyDto
import com.example.board.module.dto.CommentDto
import com.example.board.module.dto.CommentModifyDto
import com.example.board.module.entity.Board
import com.example.board.module.entity.Comment
import com.example.board.module.repository.BoardRepository
import com.example.board.module.repository.CommentRepository
import com.example.board.module.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BoardService(private val boardRepository: BoardRepository, private val userRepository: UserRepository, private val commentRepository: CommentRepository) {

    fun createBoard(boardDto: BoardDto): Board{
        val userId = userRepository.getById(boardDto.userId)
        boardDto.apply{
            return boardRepository.save(Board(title, content, createdAt = Date(), updatedAt = Date(), userId, replyList = emptyList()))
        }
    }

    fun getBoardById(boardId: String): Board{
        return boardRepository.getById(boardId.toLong())
    }

    fun deleteById(boardId: String){
        return boardRepository.deleteById(boardId.toLong())
    }

    fun modifyById(boardId: String, board: BoardModifyDto): Board{
        val getBoard = boardRepository.getById(boardId.toLong()).apply{
            if(user.userId == board.userId){
                title = board.title
                content = board.content
                updatedAt = Date()
            }
        }

        return boardRepository.save(getBoard)
    }

    fun postComment(boardId: String, comment: CommentDto): Comment {
        val user = userRepository.getById(comment.userId)
        val board = boardRepository.getById(boardId.toLong())
        comment.apply {
            return commentRepository.save(Comment(user, board, content))
        }
    }

    fun modifyComment(commentId: Long, contentInput: CommentModifyDto): Comment {
        val comment = commentRepository.getById(commentId)
        comment.apply {
            content = contentInput.content
        }
        return commentRepository.save(comment)
    }

    fun deleteComment(commentId: Long) {
        return commentRepository.deleteById(commentId)
    }
}