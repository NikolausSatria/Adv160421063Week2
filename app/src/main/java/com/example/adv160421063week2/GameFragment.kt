package com.example.adv160421063week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.adv160421063week2.databinding.FragmentGameBinding
import kotlin.random.Random

class GameFragment : Fragment() {
    private lateinit var binding : FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    var result = 0
    var points = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$playerName's Turn"

        }

        var number1 = Random.nextInt(100)
        var number2 = Random.nextInt(100)
        result = number1 + number2

        binding.txtNumber1.text = number1.toString()
        binding.txtNumber2.text = number2.toString()

//        passing value from another fragment using arguments
//        1. using if statement

//        if (arguments != null){
//            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
//            binding.txtTurn.text = "$playerName's Turn"
//        }

//        2. Using arguments? --> lambda let method


        binding.btnSubmit.setOnClickListener {
            var answer = binding.textAnswer.text.toString()
            if (answer.toInt() ==this.result){
                this.points += 1
                number1 = Random.nextInt(100)
                number2 = Random.nextInt(100)
                binding.txtNumber1.text = number1.toString()
                binding.txtNumber2.text = number2.toString()
                binding.textAnswer.text.clear()
                result = number1 + number2
            }
            else {
                val action = GameFragmentDirections.actionResultFragment(points)
                Navigation.findNavController(it).navigate(action)

            }
        }
    }

}