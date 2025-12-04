package ec.edu.uisek.githubclient.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ec.edu.uisek.githubclient.R
import ec.edu.uisek.githubclient.services.Repo

class RepoAdapter(
    private var repos: List<Repo>,
    private val onEdit: (Repo) -> Unit,
    private val onDelete: (Repo) -> Unit
) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombreRepo)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionRepo)
        val tvLenguaje: TextView = itemView.findViewById(R.id.tvLenguaje)
        val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repos[position]

        holder.tvNombre.text = repo.name
        holder.tvDescripcion.text = repo.description ?: "No existe descripción en el repositorio"
        holder.tvLenguaje.text = repo.language ?: "No especificado"

        val avatarUrl = repo.owner?.avatar_url
        if (!avatarUrl.isNullOrBlank()) {
            Glide.with(holder.itemView.context)
                .load(avatarUrl)
                .placeholder(R.drawable.ic_avatar_placeholder)
                .into(holder.ivAvatar)
        } else {
            holder.ivAvatar.setImageResource(R.drawable.ic_avatar_placeholder)
        }

        holder.btnEdit.setOnClickListener { onEdit(repo) }
        holder.btnDelete.setOnClickListener { onDelete(repo) }
    }

    override fun getItemCount(): Int = repos.size

    fun updateRepos(newRepos: List<Repo>) {
        repos = newRepos
        notifyDataSetChanged()
    }
}
