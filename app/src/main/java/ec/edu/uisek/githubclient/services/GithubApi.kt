package ec.edu.uisek.githubclient.services

import ec.edu.uisek.githubclient.model.Repo
import retrofit2.Response
import retrofit2.http.*

interface GithubApi {

    // Listar repos del usuario autenticado o de un usuario específico
    @GET("users/{user}/repos")
    suspend fun getRepos(
        @Path("user") user: String
    ): Response<List<Repo>>

    // Crear repo en la cuenta del usuario autenticado
    @POST("user/repos")
    suspend fun createRepo(
        @Body body: Repo
    ): Response<Repo>

    // Editar repo existente del usuario autenticado
    @PATCH("repos/{owner}/{repo}")
    suspend fun updateRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Body body: Repo
    ): Response<Repo>

    // Eliminar repo existente del usuario autenticado
    @DELETE("repos/{owner}/{repo}")
    suspend fun deleteRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<Unit>
}
