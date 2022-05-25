package com.android.example.retrofit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.example.retrofit.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var number : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        //Set input
        binding.buttonInput.setOnClickListener {
            increaseValue()
        }
    }

    //Function for increase value whit coroutine
    @SuppressLint("SetTextI18n")
    private fun increaseValue(){
        viewLifecycleOwner.lifecycleScope.launch {
        delay(2000)
            number?.let{
                number = number?.plus(1)
                binding.textFirst.text = "value is $it"
            } ?: kotlin.run {
                number = binding.editText.text.toString().toIntOrNull()?.plus(1)
                binding.textFirst.text = "Il valore è: $number"
            }
    }}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}