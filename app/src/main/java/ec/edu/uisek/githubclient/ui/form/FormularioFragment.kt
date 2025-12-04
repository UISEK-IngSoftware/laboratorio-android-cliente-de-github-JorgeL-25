package ec.edu.uisek.githubclient.ui.form

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

class FormularioFragment : Fragment(R.layout.fragment_formulario) {

    private lateinit var repoViewModel: RepoViewModel
    private var nombreOriginal: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoViewModel =
            ViewModelProvider(requireActivity()).get(RepoViewModel::class.java)

        val etNombre = view.findViewById<EditText>(R.id.etNombre)
        val etDescripcion = view.findViewById<EditText>(R.id.etDescripcion)
        val btnCancelar = view.findViewById<Button>(R.id.btnCancelar)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)

        // Si en el ViewModel hay un nombre para editar, lo usamos
        nombreOriginal = repoViewModel.editRepoName
        if (!nombreOriginal.isNullOrBlank()) {
            etNombre.setText(nombreOriginal)
        }

        btnCancelar.setOnClickListener {
            findNavController().popBackStack()
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val descripcion = etDescripcion.text.toString().trim()

            if (nombre.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "El nombre es obligatorio",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (nombreOriginal.isNullOrBlank()) {
                // Crear nuevo repositorio
                repoViewModel.crearRepo(nombre, descripcion)
                Toast.makeText(
                    requireContext(),
                    "Repositorio creado",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Actualizar repositorio existente
                repoViewModel.actualizarRepo(nombreOriginal!!, nombre, descripcion)
                Toast.makeText(
                    requireContext(),
                    "Repositorio actualizado",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Limpiar bandera de edición y volver a la lista
            repoViewModel.editRepoName = null
            findNavController().popBackStack()
        }
    }
}
