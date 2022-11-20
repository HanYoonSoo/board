package com.example.board.module.entity

import com.example.board.module.entity.Board
import com.example.board.module.entity.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "comment")
class Comment(

        @ManyToOne(fetch = FetchType.LAZY)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "userId")
        val user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "boardId")
        val board: Board,

        var content: String,
){
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0
}