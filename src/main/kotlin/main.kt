fun main() {
    val original = Post(
        id = null,
        fromId = 1,
        ownerId = 1,
        createdBy = 1,
        date = System.currentTimeMillis(),
        text = "Новый пост",
        replyOwnerId = 1,
        replyPostId = 1,
        friendsOnly = false,
        comments = null,
        copyright = Copyright(1, "testLink", "testName", "testType"),
        likes = Likes(1, true, true, true),
        reposts = Reposts(1, true),
        views = Views(1),
        postType = "post",
        postSource = PostSource("vk", "android", "profile_activity", "vk.com"),
        attachment = arrayOf(Attachment.EventAttachment(event1), Attachment.DocumentAttachment(doc1)),
        geo = Geo("vkType", "55.667950, 35.369341", "home"),
        signerId = 1,
        copyHistory = null,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        markedAsAds = false,
        isFavorite = false,
        donut = Donut(false, Placeholder("не дон")),
        postponedId = 1
    )
    val original2 = Post(
        id = null,
        fromId = 1,
        ownerId = 1,
        createdBy = 1,
        date = System.currentTimeMillis(),
        text = "Новый пост2",
        replyOwnerId = 1,
        replyPostId = 1,
        friendsOnly = false,
        comments = null,
        copyright = Copyright(1, "testLink", "testName", "testType"),
        likes = Likes(1, true, true, true),
        reposts = Reposts(1, true),
        views = Views(1),
        postType = "post",
        postSource = PostSource("vk", "android", "profile_activity", "vk.com"),
        attachment = arrayOf(Attachment.NoteAttachment(note1)),
        geo = Geo("vkType", "55.667950, 35.369341", "home"),
        signerId = 1,
        copyHistory = null,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        markedAsAds = false,
        isFavorite = false,
        donut = Donut(false, Placeholder("не дон")),
        postponedId = 1
    )
    val forUpdate = Post(
        id = 2,
        ownerId = 1,
        fromId = 1,
        createdBy = 2,
        date = System.currentTimeMillis(),
        text = "Новый обновленный пост",
        replyOwnerId = 2,
        replyPostId = 2,
        friendsOnly = true,
        comments = null,
        copyright = Copyright(4, "testLink", "testName", "testType"),
        likes = Likes(4, true, true, true),
        reposts = Reposts(4, true),
        views = Views(4),
        postType = "post",
        postSource = PostSource("vk", "android", "profile_activity", "vk.com"),
        attachment = arrayOf(Attachment.AudioAttachment(audio1), Attachment.StickerAttachment(sticker1)),
        geo = Geo("vkType", "55.667950, 35.369341", "home"),
        signerId = 4,
        copyHistory = null,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        markedAsAds = true,
        isFavorite = false,
        donut = Donut(false, Placeholder("не дон")),
        postponedId = 4
    )

    val forUpdate2 = Post(
        id = 4,
        ownerId = 1,
        fromId = 1,
        createdBy = 2,
        date = System.currentTimeMillis(),
        text = "Новый необновленный пост",
        replyOwnerId = 2,
        replyPostId = 2,
        friendsOnly = true,
        comments = null,
        copyright = Copyright(4, "testLink", "testName", "testType"),
        likes = Likes(4, true, true, true),
        reposts = Reposts(4, true),
        views = Views(4),
        postType = "post",
        postSource = PostSource("vk", "android", "profile_activity", "vk.com"),
        attachment = arrayOf(Attachment.NoteAttachment(note1)),
        geo = Geo("vkType", "55.667950, 35.369341", "home"),
        signerId = 4,
        copyHistory = null,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        markedAsAds = true,
        isFavorite = false,
        donut = Donut(false, Placeholder("не дон")),
        postponedId = 4
    )
    WallService.add(original)
    WallService.print()
    WallService.add(original2)
    WallService.print()
    WallService.update(forUpdate)
    WallService.print()
    WallService.update(forUpdate2)
    WallService.print()

    println(WallService.createComment(Comment(postID = 1, id = 1, text = "Первый!")))
}

object WallService {
    private var nextId = 0
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun add(post: Post): Post {
        nextId += 1
        posts += post.copy(id = nextId)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index) in posts.withIndex()) {
            if (post.id == posts[index].id) {
                posts[index] = post.copy(
                    fromId = post.fromId,
                    createdBy = post.createdBy,
                    text = post.text,
                    replyOwnerId = post.replyOwnerId,
                    replyPostId = post.replyPostId,
                    friendsOnly = post.friendsOnly,
                    comments = post.comments,
                    copyright = post.copyright,
                    likes = post.likes,
                    reposts = post.reposts,
                    views = post.views,
                    postType = post.postType,
                    postSource = post.postSource,
                    geo = post.geo,
                    signerId = post.signerId,
                    copyHistory = post.copyHistory,
                    canPin = post.canPin,
                    canDelete = post.canDelete,
                    canEdit = post.canEdit,
                    isPinned = post.isPinned,
                    markedAsAds = post.markedAsAds,
                    isFavorite = post.isFavorite,
                    donut = post.donut,
                    postponedId = post.postponedId
                )
            }
        }
        return post in posts
    }

    fun print(): Boolean {
        for (post in posts) {
            println(post)
        }
        return true
    }

    fun printComments(): Boolean {
        for (comment in comments) {
            println(comment)
        }
        return true
    }

    fun findPostByID(requiredID: Int): Post? {
        var post: Post? = null
        for ((index, item) in posts.withIndex()) {
            when (item.id) {
                requiredID -> post = posts[index]
            }
        }
        return post
    }

    fun createComment(comment: Comment): Comment {
        findPostByID(comment.postID) ?: throw PostNotFoundException("Не найден пост с номером ${comment.postID} ")
        comments += comment
        return comment
    }
}