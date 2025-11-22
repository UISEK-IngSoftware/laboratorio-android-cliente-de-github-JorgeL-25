package ec.edu.uisek.githubclient.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ec.edu.uisek.githubclient.R

class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    private lateinit var repoViewModel: RepoViewModel
    private lateinit var repoAdapter: RepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtén el ViewModel compartido
        repoViewModel = ViewModelProvider(requireActivity()).get(RepoViewModel::class.java)

        val rv = view.findViewById<RecyclerView>(R.id.rvRepos)

        // Adapter con acciones de editar y eliminar
        repoAdapter = RepoAdapter(
            repoViewModel.repositorios.value ?: mutableListOf(),
            onEdit = { pos ->
                repoViewModel.editIndex = pos
                findNavController().navigate(R.id.action_repoListFragment_to_formularioFragment)
            },
            onDelete = { pos ->
                repoViewModel.repositorios.value?.removeAt(pos)
                repoViewModel.repositorios.postValue(repoViewModel.repositorios.value)
            }
        )

        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = repoAdapter

        // Observa los cambios y actualiza el recycler
        repoViewModel.repositorios.observe(viewLifecycleOwner) {
            repoAdapter.notifyDataSetChanged()
        }

        // FAB para agregar nuevo repositorio
        val fab = view.findViewById<FloatingActionButton>(R.id.fabAgregar)
        fab.setOnClickListener {
            repoViewModel.editIndex = null // modo agregar
            findNavController().navigate(R.id.action_repoListFragment_to_formularioFragment)
        }
    }
}
