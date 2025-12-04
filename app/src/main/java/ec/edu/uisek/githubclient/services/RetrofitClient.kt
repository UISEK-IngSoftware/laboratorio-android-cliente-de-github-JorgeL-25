package ec.edu.uisek.githubclient.services

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

// PON AQUÍ TU TOKEN PERSONAL REAL
private const val GITHUB_TOKEN = "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"
private const val API_VERSION = "2022-11-28"

// ---------- MODELOS ----------

data class RepoOwner(
    val login: String? = null,
    val avatar_url: String? = null
)

data class Repo(
    val name: String,
    val description: String? = null,
    val language: String? = null,
    val owner: RepoOwner? = null
)

// ---------- INTERFAZ API ----------

interface GithubApiService {

    @GET("users/{user}/repos")
    suspend fun getUserRepos(
        @Path("user") user: String
    ): List<Repo>

    @POST("user/repos")
    suspend fun createRepo(
        @Body body: Repo
    ): Repo

    @PATCH("repos/{owner}/{repo}")
    suspend fun updateRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Body body: Repo
    ): Repo

    @DELETE("repos/{owner}/{repo}")
    suspend fun deleteRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    )
}

// ---------- CLIENTE RETROFIT ----------

object RetrofitClient {

    private val authInterceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("Accept", "application/vnd.github+json")
            .header("X-GitHub-Api-Version", API_VERSION)
            .header("Authorization", "Bearer $GITHUB_TOKEN")
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logging)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: GithubApiService = retrofit.create(GithubApiService::class.java)
}
