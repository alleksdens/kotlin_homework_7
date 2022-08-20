data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean,
    val isHq: Boolean,
)

data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comments: Int,
    val readComments: Int,
    val viewUrl: String
)

data class Document(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String,
    val url: String,
    val date: Int,
    val type: Int,
    val preview: Any?
)

data class Sticker(
    val productId: Int,
    val stickerId: Int,
    val images: Any?,
    val imagesWithBackground: Any?,
    val animationUrl: String,
    val isAllowed: Boolean
)

data class Event(
    val id: Int,
    val time: Int,
    val memberStatus: Int,
    val isFavorite: Boolean,
    val address: String,
    val text: String,
    val buttonText: String,
    val friends: Int?,

    )


val doc1 = Document(1, 2, "3", 4, "5", "6", 7, 8, 9)
val audio1 = Audio(1, 2, "text", 4, "url", 5, 6, 5, 5, noSearch = false, isHq = false)
val note1 = Note(1, 2, "3", "4", 5, 6, 7, "8")
val sticker1 = Sticker(1, 2, 3, 4, "5", true)
val event1 = Event(1, 2, 3, false, "4", "5", "6", 7)


sealed class Attachment(val type: String) {
    data class EventAttachment(val event: Event) : Attachment("event")
    data class AudioAttachment(val audio: Audio) : Attachment("audio")
    data class NoteAttachment(val note: Note) : Attachment("note")
    data class DocumentAttachment(val document: Document) : Attachment("document")
    data class StickerAttachment(val sticker: Sticker) : Attachment("sticker")
}