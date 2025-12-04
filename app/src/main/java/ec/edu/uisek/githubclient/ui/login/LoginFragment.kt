package ec.edu.uisek.githubclient.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ec.edu.uisek.githubclient.R
import ec.edu.uisek.githubclient.ui.list.RepoViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var repoViewModel: RepoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoViewModel =
            ViewModelProvider(requireActivity()).get(RepoViewModel::class.java)

        val etUsuario = view.findViewById<EditText>(R.id.etUsuario)
        val etContrasena = view.findViewById<EditText>(R.id.etContrasena)
        val btnIngresar = view.findViewById<Button>(R.id.btnIngresar)

        val prefs = requireContext()
            .getSharedPreferences("github_prefs", Context.MODE_PRIVATE)

        val usuarioGuardado = prefs.getString("usuario", null)
        if (!usuarioGuardado.isNullOrBlank()) {
            repoViewModel.currentUser = usuarioGuardado
            etUsuario.setText(usuarioGuardado)
        }

        btnIngresar.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val pass = etContrasena.text.toString().trim()

            if (usuario.isBlank() || pass.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Usuario y contraseña son obligatorios",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            prefs.edit().putString("usuario", usuario).apply()
            repoViewModel.currentUser = usuario

            Toast.makeText(
                requireContext(),
                "Inicio de sesión exitoso",
                Toast.LENGTH_SHORT
            ).show()

            findNavController()
                .navigate(R.id.action_loginFragment_to_repoListFragment)
        }
    }
}
