package ec.edu.uisek.githubclient.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ec.edu.uisek.githubclient.R

class RepoDetailFragment : Fragment(R.layout.fragment_repo_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = requireArguments()
        view.findViewById<ImageView>(R.id.imgAvatar).setImageResource(args.getInt("avatar"))
        view.findViewById<TextView>(R.id.txtName).text = args.getString("name")
        view.findViewById<TextView>(R.id.txtOwner).text = args.getString("owner")
        view.findViewById<TextView>(R.id.txtLang).text = args.getString("lang")
        view.findViewById<TextView>(R.id.txtStars).text = "★ ${args.getInt("stars")}"
        view.findViewById<TextView>(R.id.txtDesc).text = args.getString("desc")
        }
}
