package ru.netology

import ru.netology.date.Comment
import ru.netology.date.Post
import ru.netology.service.WallService
import java.lang.RuntimeException

class PostNotFoundExeption(message: String) : RuntimeException(message)
fun main() {
    val service = WallService()
    val post = Post(
        id = 1,
        ownerId = 123,
        fromId = 123,
        createdBy = 0,
        date = 1633928571,
        text = "Первый пост",
        postType = "post",
        canPin = true
    )

    service.add(post)

    val comment = Comment(
        id = 1,
        postId = 1,
        fromId = 123,
        date = 1633928572,
        text = "Первый комментарий"
    )

    try {
        val addedComment = service.createComment(1, comment)
        println("Комментарий добавлен: $addedComment")
    } catch (e: PostNotFoundExeption) {
        println("Error: ${e.message}")
    }
}



