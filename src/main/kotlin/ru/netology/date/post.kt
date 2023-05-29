package ru.netology.date

data class Post(
    val id: Int = 0, // Идентификатор записи
    val ownerId: Int = 0,// Идентификатор владельца стены, на которой размещена запись
    val fromId: Int = 0, // Идентификатор автора записи
    val createdBy: Int? = null, // Идентификатор администратора, который опубликовал запись
    val date: Int = 0, // Время публикации записи
    val text: String = "", // Текст записи
    val replyOwnerId: Int? = null, // Идентификатор владельца записи, в ответ на которую была оставлена текущая
    val replyPostId: Int? = null, // Идентификатор записи, в ответ на которую была оставлена текущая
    val friendsOnly: Boolean = false, // 1, если запись была создана с опцией «Только для друзей»
    val comments: Comments = Comments(), // Информация о комментариях к записи, объект с полями
    val copyright: Copyright? = null, // Источник материала, объект с полями
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: String? = "post", // Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest
    val postSource: PostSource? = null, // Информация о способе размещения записи. Содержит следующие поля:
    // type: String (Тип источника),
    // platform: String (Название платформы, если оно доступно),
    // url: String (URL ресурса, с которого была опубликована запись)
    val geo: Geo? = null, // Информация о местоположении , содержит поля:
    // type (string) — тип места;
    // coordinates (string) — координаты места;
    // place (object) — описание места (если оно добавлено)
    val signerId: Int? = null, // Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем
    val copyHistory: List<Post>? = null, // массив объектов, которые содержат историю репостов для записи
    val canPin: Boolean = true, // Информация о том, может ли текущий пользователь закрепить запись
    val canDelete: Boolean = true, // Информация о том, может ли текущий пользователь удалить запись
    val canEdit: Boolean = true, // Информация о том, может ли текущий пользователь редактировать запись
    val isPinned: Boolean = false, // Информация о том, что запись закреплена
    val markedAsAds: Boolean = false, // Информация о том, содержит ли запись отметку «реклама»
    val isFavorite: Boolean = false, // true, если объект добавлен в закладки у текущего пользователя
    val donut: Donut = Donut(), // Информация о записи VK Donut
    val postponedId: Int? = null, // Идентификатор отложенной записи
    val attachments: Array<Attachment>? = null
)

// TODO: блок Attachment
interface Attachment {
    val type: String
}

data class PhotoAttachment(
    override val type: String = "photo",
    val photo: Photo
) : Attachment

data class VideoAttachment(
    override val type: String = "video",
    val video: Video
) : Attachment

data class AudioAttachment(
    override val type: String = "audio",
    val audio: Audio
) : Attachment

data class NoteAttachment(
    override val type: String = "note",
    val note: Note
) : Attachment

data class MarketAttachment(
    override val type: String = "market",
    val market: Market
) : Attachment

data class Photo(
    val id: Int,
    val ownerId: Int,
    val text: String,
    val date: Int
)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val date: Int
)

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val date: Int
)

data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int
)

data class Market(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val price: Int,
    val date: Int
)

// TODO: блок вложенных классов в Post
data class Comments(
    val count: Int = 0, // количество комментариев
    val canPost: Boolean = false, // может ли текущий пользователь комментировать запись
    val groupsCanPost: Boolean = false, // могут ли сообщества комментировать запись
    val canClose: Boolean = false, // может ли текущий пользователь закрыть комментарии к записи
    val canOpen: Boolean = false // может ли текущий пользователь открыть комментарии к записи
)

data class Copyright(
    val id: Int,
    val link: String?,
    val name: String?,
    val type: String?
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

data class PostSource(
    val type: String, // Тип источника
    val platform: String?, // Название платформы, если оно доступно
    val url: String? // URL ресурса, с которого была опубликована запись
)

data class Geo(
    val type: String, // тип места
    val coordinates: String, // координаты места
    val place: Place? // описание места (если оно добавлено)
)

data class Place(
    val text: String = TODO() // поля, описывающие место
)

data class Donut(
    val isDonut: Boolean = false, // запись доступна только платным подписчикам VK Donut
    val paidDuration: Int = 0, // время, в течение которого запись будет доступна только платным подписчикам VK Donut
    val placeholder: Placeholder? = null, // заглушка для пользователей, которые не оформили подписку VK Donut
    val canPublishFreeCopy: Boolean = false, // можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut
    val editMode: String = "All" // какие значения VK Donut можно изменить в записи
    // all — всю информацию о VK Donut; duration — время, в течение которого запись будет доступна только платным подписчикам VK Donut
)

data class Placeholder(
    val text: String = TODO() // поля, описывающие заглушку
)

data class Comment(
    val id: Int, // Идентификатор комментария
    val postId: Int, // идентификатор поста, к которому принадлежит комментарий
    val fromId: Int, // Идентификатор автора комментария
    val date: Int, // Дата создания комментария
    val text: String // Текст комментария
)