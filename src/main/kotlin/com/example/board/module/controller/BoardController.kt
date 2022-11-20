package com.example.board.module.controller

import com.example.board.module.dto.BoardDto
import com.example.board.module.dto.BoardModifyDto
import com.example.board.module.dto.CommentDto
import com.example.board.module.dto.CommentModifyDto
import com.example.board.module.entity.Board
import com.example.board.module.entity.Comment
import com.example.board.module.service.BoardService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/board")
class BoardController(private val boardService: BoardService) {
    @PostMapping("/create")
    fun createBoard(@RequestBody request: BoardDto): Board {
        request.apply{
            return boardService.createBoard(BoardDto(title, content, userId))
        }
    }

    @GetMapping("{boardId}")
    fun getBoardById(@PathVariable boardId: String): Board{
        return boardService.getBoardById(boardId)
    }

    @DeleteMapping("{boardId}")
    fun deleteBoardById(@PathVariable boardId: String){
        return boardService.deleteById(boardId)
    }

    @PatchMapping("{boardId}")
    fun modifyBoardById(@PathVariable boardId: String, @RequestBody request: BoardModifyDto): Board{
        return boardService.modifyById(boardId, request)
    }

    //댓글
    @PostMapping("{boardId}/comment")
    fun postComment(@PathVariable boardId: String, @RequestBody request: CommentDto): Comment {
        return boardService.postComment(boardId, request)
    }
g
    @PatchMapping("comment/{commentId}")
    fun modifyComment(@PathVariable commentId: Long, @RequestBody request: CommentModifyDto): Comment {
        return boardService.modifyComment(commentId, request)
    }

    @DeleteMapping("comment/{commentId}")
    fun deleteComment(@PathVariable commentId: Long) {
        return boardService.deleteComment(commentId)
    }
}