package ru.netology

fun main() {

}

data class Post(
    val id: Int, // Идентификатор записи
    val ownerId: Int,// Идентификатор владельца стены, на которой размещена запись
    val fromId: Int, // Идентификатор автора записи
    val date: Long, // Время публикации записи
    val text: String, // Текст записи
    val isPinned: Boolean = false, // Информация о том, что запись закреплена
    val canEdit: Boolean = true, // Информация о том, может ли текущий пользователь редактировать запись
    val canDelete: Boolean = true, // Информация о том, может ли текущий пользователь удалить запись
    val canPin: Boolean = true, // Информация о том, может ли текущий пользователь закрепить запись
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views()
)

data class Likes(
    val count: Int = 0, // число пользователей, которым понравилась запись
    val userLikes: Boolean = false, // наличие отметки «Мне нравится» от текущего пользователя
    val canLike: Boolean = true, // может ли текущий пользователь поставить отметку «Мне нравится»
    val canPublish: Boolean = true // может ли текущий пользователь сделать репост записи
)

data class Reposts(
    val count: Int = 0, // число пользователей, скопировавших запись
    val userReposted: Boolean = false // наличие репоста от текущего пользователя
)

data class Views(
    val count: Int = 0 // число просмотров записи
)

class WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 1

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

}
