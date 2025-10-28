package ec.edu.uisek.githubclient.model

import ec.edu.uisek.githubclient.R

object SampleData {
    val repos: List<Repo> = listOf(
        Repo(
            name = "awesome-android-ui",
            description = "Colección de componentes UI para Android.",
            owner = "androiddev",
            stars = 15234,
            language = "Kotlin",
            avatarResId = R.drawable.ic_repo
        ),
        Repo(
            name = "kotlin-coroutines",
            description = "Ejemplos prácticos de corrutinas.",
            owner = "kotlinx",
            stars = 9876,
            language = "Kotlin",
            avatarResId = R.drawable.ic_repo
        ),
        Repo(
            name = "material-design-samples",
            description = "Patrones y ejemplos de Material Design 3.",
            owner = "google",
            stars = 13500,
            language = "Kotlin",
            avatarResId = R.drawable.ic_repo
            )
    )
}