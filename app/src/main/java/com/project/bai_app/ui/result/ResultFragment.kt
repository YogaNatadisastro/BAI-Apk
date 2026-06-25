package com.project.bai_app.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.project.bai_app.R
import com.project.bai_app.databinding.FragmentResultBinding
import com.project.bai_app.di.model.repo.MainRepository
import com.project.bai_app.ui.form.AssessmentViewModel
import com.project.bai_app.ui.form.AssessmentViewModelFactory

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AssessmentViewModel by activityViewModels {
        AssessmentViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Implement result history list loading and adapter binding using result_recycler and empty_state
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
