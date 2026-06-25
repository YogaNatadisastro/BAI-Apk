package com.project.bai_app.ui.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.project.bai_app.R
import com.project.bai_app.databinding.FragmentAssessmentContainerBinding
import com.project.bai_app.di.model.session.Patient
import com.project.bai_app.di.model.session.SessionRequest
import com.project.bai_app.ui.gad7.Gad7Fragment
import com.project.bai_app.ui.patient.PatientInfoFragment

class AssessmentContainerFragment : Fragment() {

    private var _bind: FragmentAssessmentContainerBinding? = null
    private val bind get() = _bind!!

    private val viewModel: AssessmentViewModel by viewModels {
        AssessmentViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentAssessmentContainerBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        initAssessmentSession()
    }

    private fun initAssessmentSession() {
        val patientName = arguments?.getString(EXTRA_PATIENT_NAME)
        val patientGender = arguments?.getString(EXTRA_PATIENT_GENDER)
        val patientBirthDate = arguments?.getString(EXTRA_PATIENT_BIRTH_DATE)
        val patientContact = arguments?.getString(EXTRA_PATIENT_CONTACT)

        val sharePref = requireActivity().getSharedPreferences("UserSession", android.content.Context.MODE_PRIVATE)
        val currentAdminId = sharePref.getInt("userId", 0)

        if (patientName != null && currentAdminId != 0) {
            val livaPatientData = Patient(
                name = patientName,
                gender = patientGender,
                birthDate = patientBirthDate,
                contact = patientContact
            )

            val sessionRequest = SessionRequest(
                userId = currentAdminId,
                patient = livaPatientData
            )

            viewModel.patientInfo(sessionRequest)
        } else {
            Toast.makeText(requireContext(), "Data is not valid", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupObservers() {
        viewModel.patientInfo.observe(viewLifecycleOwner) { result ->
            result.onSuccess { sessionResponse ->
                val activeSessionId = sessionResponse.sessionId

                if (childFragmentManager.findFragmentById(R.id.patientInfoFragment) == null) {
                    navigateToSubFragment(PatientInfoFragment())
                }
            }.onFailure { exception ->
                Toast.makeText(
                    requireContext(),
                    "Gagal memulai sesi: ${exception.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun navigateToSubFragment(targetFragment: Fragment) {
        childFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.gad7Fragment, targetFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

    companion object {
        const val EXTRA_PATIENT_NAME = "extra_patient_name"
        const val EXTRA_PATIENT_GENDER = "extra_patient_gender"
        const val EXTRA_PATIENT_BIRTH_DATE = "extra_patient_birth_date"
        const val EXTRA_PATIENT_CONTACT = "extra_patient_contact"
    }
}