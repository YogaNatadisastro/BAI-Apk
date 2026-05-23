package com.project.bai_app.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.project.bai_app.R
import com.project.bai_app.databinding.FragmentGad7Binding
import com.project.bai_app.di.model.gad7.AnswersItem
import com.project.bai_app.di.model.repo.MainRepository

class Gad7Fragment : Fragment() {

    private var _binding: FragmentGad7Binding? = null
    private val binding get() = _binding!!
    
    private val viewModel: AssessmentViewModel by activityViewModels {
        AssessmentViewModelFactory(MainRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGad7Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmitGad7.setOnClickListener {
            val scoreQ1 = when (binding.rgQ1.checkedRadioButtonId) {
                R.id.rbQ1_0 -> 0
                R.id.rbQ1_1 -> 1
                R.id.rbQ1_2 -> 2
                R.id.rbQ1_3 -> 3
                else -> -1
            }
            val scoreQ2 = when (binding.rgQ2.checkedRadioButtonId) {
                R.id.rbQ2_0 -> 0
                R.id.rbQ2_1 -> 1
                R.id.rbQ2_2 -> 2
                R.id.rbQ2_3 -> 3
                else -> -1
            }

            if (scoreQ1 == -1 || scoreQ2 == -1) {
                Toast.makeText(requireContext(), "Harap isi semua pertanyaan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val answers = listOf(
                AnswersItem(scoreValue = scoreQ1, questionId = 1),
                AnswersItem(scoreValue = scoreQ2, questionId = 2)
            )
            viewModel.submitGad7(answers)
        }

        viewModel.gad7Result.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                findNavController().navigate(R.id.action_gad7Fragment_to_hadsFragment)
            }
            result.onFailure {
                Toast.makeText(requireContext(), "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
