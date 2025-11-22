package ec.edu.uisek.githubclient.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ec.edu.uisek.githubclient.model.Repositorio
import ec.edu.uisek.githubclient.R

class RepoViewModel : ViewModel() {
    // Lista dinámica de repositorios
    val repositorios = MutableLiveData(
        mutableListOf(
            Repositorio(R.drawable.avatar1, "Jenkins", "No existe descripción en el repositorio", "Shell"),
            Repositorio(R.drawable.avatar2, "laboratorio-5-JorgeUISEK", "Descripción del repo", "HTML")
        )
    )

    // Índice del repositorio a editar (null si es modo agregar)
    var editIndex: Int? = null
}
