package ru.netology.service

import ru.netology.PostNotFoundExeption
import ru.netology.date.Comment
import ru.netology.date.Post

// TODO: Хранение постов в массиве
class WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var nextId = 1
    private var nextCommentId = 1

    fun add(post: Post): Post { // добавление нового поста
        val newPost = post.copy(id = nextId++)
        posts += newPost
        return posts.last()
    }

    fun updatePost(post: Post): Boolean {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == post.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        val post = posts.find { it.id == postId }
        if (post != null) {
            val comment = Comment(
                id = nextCommentId++,
                postId = postId,
                fromId = 0,
                date = (System.currentTimeMillis() / 1000).toInt(),
                text = comment.text
            )
            comments += comment
            return comments.last()
        } else {
            throw PostNotFoundExeption("Пост с id_$postId не существует, - комментарий не удалось добавить.")
        }
    }

}