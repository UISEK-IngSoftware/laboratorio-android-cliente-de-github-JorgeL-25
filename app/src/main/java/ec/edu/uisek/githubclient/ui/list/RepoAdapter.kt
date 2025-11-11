package ec.edu.uisek.githubclient.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.edu.uisek.githubclient.R
import ec.edu.uisek.githubclient.model.Repositorio

class RepoAdapter(
    private val data: List<Repositorio>,
    private val onClick: (Repositorio) -> Unit
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.ivAvatar)
        val nombre: TextView = view.findViewById(R.id.tvNombre)
        val descripcion: TextView = view.findViewById(R.id.tvDescripcion)
        val lenguaje: TextView = view.findViewById(R.id.tvLenguaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = data[position]
        holder.avatar.setImageResource(repo.avatarRes)
        holder.nombre.text = repo.nombre
        holder.descripcion.text = repo.descripcion
        holder.lenguaje.text = repo.lenguaje

        holder.itemView.setOnClickListener { onClick(repo) }
    }

    override fun getItemCount() = data.size
}

