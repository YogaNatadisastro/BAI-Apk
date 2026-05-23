package com.project.bai_app.ui.form

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.project.bai_app.di.model.gad7.GadResponse
import com.project.bai_app.di.model.hads.HadsResponse
import com.project.bai_app.di.model.repo.MainRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AssessmentViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: MainRepository
    private lateinit var viewModel: AssessmentViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = AssessmentViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `submitGad7 should update session ID and emit success`() {
        val gadResponse = GadResponse(sessionId = 123, message = "Success")
        coEvery { repository.postGad7(any()) } returns gadResponse

        val observer = mockk<Observer<Result<GadResponse>>>(relaxed = true)
        viewModel.gad7Result.observeForever(observer)

        viewModel.submitGad7(emptyList())
        testDispatcher.scheduler.advanceUntilIdle()

        verify { observer.onChanged(Result.success(gadResponse)) }
    }

    @Test
    fun `submitHads should emit success using current session ID`() {
        // First set session ID via GAD7
        val gadResponse = GadResponse(sessionId = 456)
        coEvery { repository.postGad7(any()) } returns gadResponse
        viewModel.submitGad7(emptyList())
        testDispatcher.scheduler.advanceUntilIdle()

        // Then test HADS
        val hadsResponse = HadsResponse(sessionId = 456, message = "Success HADS")
        coEvery { repository.postHads(match { it.sessionId == 456 }) } returns hadsResponse

        val observer = mockk<Observer<Result<HadsResponse>>>(relaxed = true)
        viewModel.hadsResult.observeForever(observer)

        viewModel.submitHads(emptyList())
        testDispatcher.scheduler.advanceUntilIdle()

        verify { observer.onChanged(Result.success(hadsResponse)) }
    }
}
