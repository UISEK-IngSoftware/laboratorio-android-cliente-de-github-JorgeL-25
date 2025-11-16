package ec.edu.uisek.githubclient.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ec.edu.uisek.githubclient.R
import ec.edu.uisek.githubclient.model.Repositorio

class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    // Lista estática de repositorios con avatares
    private val listaRepos = listOf(
        Repositorio(R.drawable.avatar1, "Jenkins", "No existe descripción en el repositorio", "Shell"),
        Repositorio(R.drawable.avatar2, "laboratorio-5-JorgeUISEK", "desarrollo-mobil-2025-5-laboratorio-5-laboratorio-5-ejemplo created by GitHub Classroom", "HTML"),
        Repositorio(R.drawable.avatar3, "Tarea-10", "No existe descripción en el repositorio", "HTML"),
        Repositorio(R.drawable.avatar4, "test", "No existe descripción en el repositorio", "Lenguaje no especificado"),
        Repositorio(R.drawable.avatar5, "Tarea10-APE", "No existe descripción en el repositorio", "HTML")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.rvRepos)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = RepoAdapter(listaRepos) { repo ->
            val args = Bundle().apply {
                putString("name", repo.nombre)
                putString("desc", repo.descripcion)
                putString("lang", repo.lenguaje)
                putInt("avatar", repo.avatarRes)
            }
            findNavController().navigate(R.id.repoDetailFragment, args)
        }

        // Código para el FAB: Navega al formulario
        val fab = view.findViewById<FloatingActionButton>(R.id.fabAgregar)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_repoListFragment_to_newProjectFragment)
            // Usa el id exacto de tu nav_graph.xml para la acción hacia el formulario
        }
    }
}
