package com.project.bai_app.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.project.bai_app.di.model.login.LoginRequest
import com.project.bai_app.di.model.login.LoginResponse
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
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: MainRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login should emit success result when repository returns response`() {
        val username = "testuser"
        val password = "password123"
        val expectedResponse = LoginResponse(accessToken = "token", tokenType = "Bearer")
        
        coEvery { repository.loginUser(any()) } returns expectedResponse

        val observer = mockk<Observer<Result<LoginResponse>>>(relaxed = true)
        viewModel.loginResult.observeForever(observer)

        viewModel.login(username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        verify { observer.onChanged(Result.success(expectedResponse)) }
    }

    @Test
    fun `login should emit failure result when repository throws exception`() {
        val exception = Exception("Network error")
        coEvery { repository.loginUser(any()) } throws exception

        val observer = mockk<Observer<Result<LoginResponse>>>(relaxed = true)
        viewModel.loginResult.observeForever(observer)

        viewModel.login("user", "pass")
        testDispatcher.scheduler.advanceUntilIdle()

        verify { observer.onChanged(match { it.isFailure && it.exceptionOrNull() == exception }) }
    }
}
