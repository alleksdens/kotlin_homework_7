data class Comment(
    val postID: Int,
    val id: Int? = null,
    val fromID: Int = 1,
    val date: Int = (System.currentTimeMillis() / 1000).toInt(),
    val text: String,
    val donut: Donut = Donut(isDonut = false, placeholder = Placeholder("не дон")),
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    val attachments: Array<Attachment>? = null,
    val parents_stack: Array<Int>? = null,
    val thread: Thread? = null
)