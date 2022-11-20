package com.example.board.module.entity


import com.example.board.module.entity.Comment
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "board")
class Board(
        var title: String,
        var content: String,
        var createdAt: Date,
        var updatedAt: Date,
        @ManyToOne
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "userId")
        val user: User,
        @JsonIgnoreProperties("board")
        @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
        var replyList: List<Comment>,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}