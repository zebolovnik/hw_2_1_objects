package ru.netology

import org.junit.Test

import org.junit.Assert.*


class WallServiceTest {

    @Test
    fun addPostIdNotZero() {
        val service = WallService()
        val post = Post(0, 1, 1, System.currentTimeMillis(), "Text")
        val result = service.add(post)
        assertTrue(result.id != 0)
    }

    @Test
    fun updatePostExistingIdReturnTrue() {
        val service = WallService()
        service.add(Post(1, 1, 1, System.currentTimeMillis(), "Text 1"))
        service.add(Post(2, 2, 2, System.currentTimeMillis(), "Text 2"))
        service.add(Post(3, 3, 3, System.currentTimeMillis(), "Text 3"))
        val update = Post(2, 2, 2, System.currentTimeMillis(), "New Text 2")

        val result = service.updatePost(update)

        assertTrue(result)
    }

    @Test
    fun updatePostNotExistingIdReturnFalse() {
        val service = WallService()
        val post = Post(0, 1, 1, System.currentTimeMillis(), "Text")
        service.add(post)
        val updatedPost = post.copy(id = 2, text = "New Text")

        val result = service.updatePost(updatedPost)

        assertFalse(result)
    }
}