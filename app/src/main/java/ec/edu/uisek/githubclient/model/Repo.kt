package ec.edu.uisek.githubclient.model

data class Repo(
    val name: String,
    val description: String,
    val owner: String,
    val stars: Int,
    val language: String,
    val avatarResId: Int
)
