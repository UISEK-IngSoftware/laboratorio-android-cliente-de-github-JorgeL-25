package ec.edu.uisek.githubclient.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.edu.uisek.githubclient.model.Repositorio
import ec.edu.uisek.githubclient.R

class RepoAdapter(
    private val repositorios: MutableList<Repositorio>,
    private val onEdit: (Int) -> Unit,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    inner class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvDescripcion: TextView = view.findViewById(R.id.tvDescripcion)
        val tvLenguaje: TextView = view.findViewById(R.id.tvLenguaje)
        val btnEditar: ImageView = view.findViewById(R.id.btnEditar)
        val btnEliminar: ImageView = view.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(v)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repositorios[position]
        holder.ivAvatar.setImageResource(repo.avatarRes)
        holder.tvNombre.text = repo.nombre
        holder.tvDescripcion.text = repo.descripcion
        holder.tvLenguaje.text = repo.lenguaje

        holder.btnEditar.setOnClickListener {
            onEdit(position) // Acción editar: lo manejas en el fragment
        }
        holder.btnEliminar.setOnClickListener {
            onDelete(position) // Acción eliminar: lo manejas en el fragment
        }
    }

    override fun getItemCount() = repositorios.size
}
