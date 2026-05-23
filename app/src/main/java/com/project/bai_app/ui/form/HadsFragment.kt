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
import com.project.bai_app.databinding.FragmentHadsBinding
import com.project.bai_app.di.model.hads.AnswersItem
import com.project.bai_app.di.model.repo.MainRepository

class HadsFragment : Fragment() {

    private var _binding: FragmentHadsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AssessmentViewModel by activityViewModels {
        AssessmentViewModelFactory(MainRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmitHads.setOnClickListener {
            val scoreQ1 = when (binding.rgHadsQ1.checkedRadioButtonId) {
                R.id.rbHadsQ1_0 -> 0
                R.id.rbHadsQ1_1 -> 1
                R.id.rbHadsQ1_2 -> 2
                R.id.rbHadsQ1_3 -> 3
                else -> -1
            }

            if (scoreQ1 == -1) {
                Toast.makeText(requireContext(), "Harap isi semua pertanyaan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val answers = listOf(
                AnswersItem(scoreValue = scoreQ1, questionId = 1)
            )
            viewModel.submitHads(answers)
        }

        viewModel.hadsResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                findNavController().navigate(R.id.action_hadsFragment_to_resultFragment)
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
