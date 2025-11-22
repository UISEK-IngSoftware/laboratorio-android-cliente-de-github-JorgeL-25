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

    private val listaRepos = listOf(
        Repositorio(R.drawable.avatar1, "Jenkins", "No existe descripción en el repositorio", "Shell"),
        Repositorio(R.drawable.avatar2, "laboratorio-5-JorgeUISEK", "Descripción del repo", "HTML"),
        // agrega aquí más repos estáticos si lo necesitas
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.rvRepos)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = RepoAdapter(listaRepos)

        val fab = view.findViewById<FloatingActionButton>(R.id.fabAgregar)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_repoListFragment_to_newProjectFragment)
        }
    }
}
