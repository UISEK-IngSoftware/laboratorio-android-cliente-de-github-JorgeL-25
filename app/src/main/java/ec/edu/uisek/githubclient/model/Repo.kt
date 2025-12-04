package ec.edu.uisek.githubclient.model

data class Repo(
    val name: String,
    val description: String? = null,
    val language: String? = null,
    val owner: RepoOwner? = null
)
