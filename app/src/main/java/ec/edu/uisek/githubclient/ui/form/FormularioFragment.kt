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
import ec.edu.uisek.githubclient.model.Repositorio
import ec.edu.uisek.githubclient.ui.list.RepoViewModel

class FormularioFragment : Fragment(R.layout.fragment_formulario) {
    private lateinit var repoViewModel: RepoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoViewModel = ViewModelProvider(requireActivity()).get(RepoViewModel::class.java)

        val etNombre = view.findViewById<EditText>(R.id.etNombre)
        val etDescripcion = view.findViewById<EditText>(R.id.etDescripcion)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)
        val btnCancelar = view.findViewById<Button>(R.id.btnCancelar)

        // Cargar datos si es edición
        val editIndex = repoViewModel.editIndex
        if (editIndex != null) {
            val repo = repoViewModel.repositorios.value?.get(editIndex)
            etNombre.setText(repo?.nombre)
            etDescripcion.setText(repo?.descripcion)
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.text.toString()
            val lenguaje = "Shell" // Puedes cambiar esto o pedirlo en otro campo si deseas
            val avatarRes = R.drawable.avatar1 // Cambia por lógica si usas varios avatares

            if (editIndex != null) {
                // Editar repo existente
                repoViewModel.repositorios.value?.set(editIndex, Repositorio(avatarRes, nombre, descripcion, lenguaje))
            } else {
                // Agregar nuevo repo
                repoViewModel.repositorios.value?.add(Repositorio(avatarRes, nombre, descripcion, lenguaje))
            }
            repoViewModel.repositorios.postValue(repoViewModel.repositorios.value)
            repoViewModel.editIndex = null
            Toast.makeText(requireContext(), "Repositorio guardado", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        btnCancelar.setOnClickListener {
            repoViewModel.editIndex = null
            findNavController().popBackStack()
        }
    }
}
