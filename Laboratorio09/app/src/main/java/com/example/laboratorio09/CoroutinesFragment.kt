package com.example.laboratorio09

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.corutinesdemo.databinding.FragmentCoroutinesBinding
import kotlinx.coroutines.*

class CoroutinesFragment : Fragment() {

    private lateinit var binding: FragmentCoroutinesBinding
    private var count = 0

    // "Job" represents a unit of work in a coroutine.
    // It is declared globally so that it can later be canceled in another function if necessary.
    private var downloadJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoroutinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.countButton.setOnClickListener {
            binding.counterTextView.text = count++.toString()
        }

        binding.downloadButton.setOnClickListener {
            //creando un afuncion asincrona llamada scope que representara una corrutina es decir una funcion asincrona
            val scope = CoroutineScope(Dispatchers.IO) // en dispatcher.IO es una funcion que se usa para nombre como corrutina, tambien se puede usar Dispatchers.Default
            downloadJob = scope.launch {//una variable dowload que viene con el contexto job que va siempre con la funcion Dispacher
                downloadUserData() ///llamando una funcion qeu solo puede ser usada en un scope es decir llamada por otra ufuncion asincrona
            }
        }
    }
/// una funcion suspend que se usa para una corrutina es decir usar otro hilo y solo puede ser usada para otra corrutina
    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            // This is NOT running in the main thread
            Log.i(MainActivity.APP_TAG, "Downloading user $i in ${Thread.currentThread().name}")

            // This is an ERROR
            // tvMessage.text = "Downloading user $i" // Can not be called in a background thread

            // Switch to main thread to update view
            //Funcion para volver al hilo principal, si queremos modificar algo nos movemos al hilo principal que SIEMPRE se llama Main, Dispacher.main
            withContext(Dispatchers.Main) {
                binding.messageTextView.text = "Downloading user $i"
            }
            delay(100)//para ver el cambio mas facil en la UI (vista)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // If downloadJob is running then cancel the coroutine
        // when fragment is closed
        downloadJob?.cancel()
    }
}