package ec.edu.uisek.githubclient.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ec.edu.uisek.githubclient.R
import ec.edu.uisek.githubclient.model.SampleData

class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = view.findViewById<RecyclerView>(R.id.rvRepos)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = RepoAdapter(SampleData.repos) { repo ->
            val args = Bundle().apply {
                putString("name", repo.name)
                putString("owner", repo.owner)
                putString("desc", repo.description)
                putInt("stars", repo.stars)
                putString("lang", repo.language)
                putInt("avatar", repo.avatarResId)
            }
            findNavController().navigate(R.id.repoDetailFragment, args)
            }
        }
}
