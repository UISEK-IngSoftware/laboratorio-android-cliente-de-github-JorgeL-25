package ec.edu.uisek.githubclient.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.edu.uisek.githubclient.R
import ec.edu.uisek.githubclient.model.Repo

class RepoAdapter(
    private val data: List<Repo>,
    private val onClick: (Repo) -> Unit
) : RecyclerView.Adapter<RepoAdapter.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val img: ImageView = v.findViewById(R.id.imgAvatar)
        val name: TextView = v.findViewById(R.id.txtName)
        val owner: TextView = v.findViewById(R.id.txtOwner)
        val stars: TextView = v.findViewById(R.id.txtStars)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, position: Int) {
        val repo = data[position]
        h.img.setImageResource(repo.avatarResId)
        h.name.text = repo.name
        h.owner.text = repo.owner
        h.stars.text = "★ ${repo.stars}"
        h.itemView.setOnClickListener { onClick(repo) }
    }

    override fun getItemCount()=data.size
}
