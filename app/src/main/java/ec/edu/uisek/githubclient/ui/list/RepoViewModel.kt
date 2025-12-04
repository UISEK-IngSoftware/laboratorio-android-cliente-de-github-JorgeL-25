package ec.edu.uisek.githubclient.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ec.edu.uisek.githubclient.services.Repo
import ec.edu.uisek.githubclient.services.RetrofitClient
import kotlinx.coroutines.launch

class RepoViewModel : ViewModel() {

    val repositorios = MutableLiveData<List<Repo>>(emptyList())
    val loading = MutableLiveData(false)
    val errorMensaje = MutableLiveData<String?>()

    var currentUser: String = ""          // usuario de GitHub para listar repos
    var editRepoName: String? = null      // nombre original al editar

    fun cargarRepos() {
        if (currentUser.isBlank()) return
        loading.value = true
        viewModelScope.launch {
            try {
                val lista = RetrofitClient.api.getUserRepos(currentUser)
                repositorios.value = lista
                errorMensaje.value = null
            } catch (e: Exception) {
                errorMensaje.value = "Error al cargar repos: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    fun crearRepo(nombre: String, descripcion: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val nuevo = Repo(
                    name = nombre,
                    description = descripcion.ifBlank { null }
                )
                RetrofitClient.api.createRepo(nuevo)
                cargarRepos()
            } catch (e: Exception) {
                errorMensaje.value = "Error al crear repo: ${e.message}"
                loading.value = false
            }
        }
    }

    fun actualizarRepo(nombreOriginal: String, nuevoNombre: String, descripcion: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val body = Repo(
                    name = nuevoNombre,
                    description = descripcion.ifBlank { null }
                )
                RetrofitClient.api.updateRepo(currentUser, nombreOriginal, body)
                cargarRepos()
            } catch (e: Exception) {
                errorMensaje.value = "Error al actualizar repo: ${e.message}"
                loading.value = false
            }
        }
    }

    fun eliminarRepo(nombre: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                RetrofitClient.api.deleteRepo(currentUser, nombre)
                cargarRepos()
            } catch (e: Exception) {
                errorMensaje.value = "Error al eliminar repo: ${e.message}"
                loading.value = false
            }
        }
    }
}
