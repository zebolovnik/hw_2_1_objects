package ru.netology

import org.junit.Test

import org.junit.Assert.*
import ru.netology.date.Comment
import ru.netology.date.Post
import ru.netology.service.WallService

class WallServiceTest {

    @Test
    fun addPostIdNotZero() {
        val service = WallService()
        val post = Post(
            id = 0,
            ownerId = 1,
            fromId = 1,
            date = System.currentTimeMillis().toInt(),
            text = "Text"
        )
        val result = service.add(post)
        assertTrue(result.id != 0)
    }


    @Test
    fun updatePostExistingIdReturnTrue() { // проверяем успешное обновление записи с существующим ID
        val service = WallService()
        service.add(Post(1, 1, 1, date = System.currentTimeMillis().toInt(), text = "Text 1"))
        service.add(Post(2, 2, 2, date = System.currentTimeMillis().toInt(), text = "Text 2"))
        service.add(Post(3, 3, 3, date = System.currentTimeMillis().toInt(), text = "Text 3"))
        val update = Post(2, 2, 2, date = System.currentTimeMillis().toInt(), text = "New Text 2")

        val result = service.updatePost(update)

        assertTrue(result)
    }

    @Test
    fun updatePostNotExistingIdReturnFalse() {
        val service = WallService()
        val post = Post(0, 1, 1, date = System.currentTimeMillis().toInt(), text = "Text")
        service.add(post)
        val updatedPost = post.copy(id = 2, text = "New Text")

        val result = service.updatePost(updatedPost)

        assertFalse(result)
    }

    @Test // Проверяем, что комментарий успешно добавляется к существующему посту:
    fun shouldAddCommentToValidPost() {
        val service = WallService()
        val post = Post(
            id = 0,
            ownerId = 0,
            fromId = 0,
            date = 0,
            text = "Test post"
        )
        val addedPost = service.add(post)
        val comment = Comment(
            id = 0,
            postId = addedPost.id,
            fromId = 0,
            date = 0,
            text = "Test comment"
        )
        val addedComment = service.createComment(addedPost.id, comment)
        assertEquals(comment.text, addedComment.text)
    }

    @Test(expected = PostNotFoundException::class) // проверяем, что будет выброшено исключение PostNotFoundException, если мы попытаемся добавить комментарий к несуществующему посту
    fun shouldThrowPostNotFoundException() {
        val service = WallService()
        val comment = Comment(
            id = 0,
            postId = 123,
            fromId = 0,
            date = 0,
            text = "Test comment"
        )
        service.createComment(comment.postId, comment)
    }

}