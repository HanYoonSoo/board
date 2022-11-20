package com.example.board.module.service

import com.example.board.module.dto.*
import com.example.board.module.entity.Board
import com.example.board.module.entity.Comment
import com.example.board.module.repository.BoardRepository
import com.example.board.module.repository.CommentRepository
import com.example.board.module.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class BoardService(private val boardRepository: BoardRepository, private val userRepository: UserRepository, private val commentRepository: CommentRepository) {

    fun createBoard(boardDto: BoardDto): Board{
        val userId = userRepository.getById(boardDto.userId)
        boardDto.apply{
            return boardRepository.save(Board(title, content, createdAt = Date(), updatedAt = Date(), userId, replyList = emptyList()))
        }
    }

    @Transactional
    fun getBoardById(boardId: String): TestDto{
        val board = boardRepository.getById(boardId.toLong())
        return TestDto(board.title, board.content, board.replyList.map{
            TCommentDto.toDto(it)
        })
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

    fun postComment(boardId: String, comment: CommentDto): Boolean {
        val user = userRepository.getById(comment.userId)
        val board = boardRepository.getById(boardId.toLong())
        comment.apply {
            commentRepository.save(Comment(user, board, content))
        }
        return true
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