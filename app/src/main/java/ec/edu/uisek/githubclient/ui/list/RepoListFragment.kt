package ec.edu.uisek.githubclient.ui.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ec.edu.uisek.githubclient.R
import ec.edu.uisek.githubclient.services.Repo

class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    private lateinit var repoViewModel: RepoViewModel
    private lateinit var adapter: RepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoViewModel =
            ViewModelProvider(requireActivity()).get(RepoViewModel::class.java)

        val rv = view.findViewById<RecyclerView>(R.id.rvRepos)
        val progress = view.findViewById<ProgressBar>(R.id.progressBar)
        val fab = view.findViewById<FloatingActionButton>(R.id.fabNewProject)

        adapter = RepoAdapter(emptyList(),
            onEdit = { repo -> editarRepo(repo) },
            onDelete = { repo -> eliminarRepo(repo) }
        )

        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        repoViewModel.repositorios.observe(viewLifecycleOwner) { lista ->
            adapter.updateRepos(lista)
        }

        repoViewModel.loading.observe(viewLifecycleOwner) { cargando ->
            progress.visibility = if (cargando) View.VISIBLE else View.GONE
        }

        repoViewModel.errorMensaje.observe(viewLifecycleOwner) { msg ->
            if (!msg.isNullOrBlank()) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }

        fab.setOnClickListener {
            repoViewModel.editRepoName = null
            findNavController().navigate(R.id.formularioFragment)
        }

        // Cargar repos del usuario al entrar
        repoViewModel.cargarRepos()
    }

    private fun editarRepo(repo: Repo) {
        repoViewModel.editRepoName = repo.name
        findNavController().navigate(R.id.formularioFragment)
    }

    private fun eliminarRepo(repo: Repo) {
        repoViewModel.eliminarRepo(repo.name)
    }
}
