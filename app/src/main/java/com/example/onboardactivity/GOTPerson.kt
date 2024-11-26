import kotlinx.serialization.Serializable

@Serializable
data class GOTPerson(
    val name: String,
    val culture: String,
    val born: String,
    val titles: List<String>,
    val aliases: List<String>,
    val playedBy: String,
)