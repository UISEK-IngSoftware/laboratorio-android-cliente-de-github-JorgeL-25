package ec.edu.uisek.githubclient.ui.form

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ec.edu.uisek.githubclient.R

class FormularioFragment : Fragment(R.layout.fragment_formulario) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCancelar = view.findViewById<Button>(R.id.btnCancelar)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)

        btnCancelar.setOnClickListener {
            findNavController().popBackStack()
        }

        btnGuardar.setOnClickListener {
            // Esto muestra el mensaje Toast
            Toast.makeText(requireContext(), "El repositorio se ha guardado con éxito", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }
}
