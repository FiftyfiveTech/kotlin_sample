package com.fiftyfive.nativeandroidtemplate.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fiftyfive.nativeandroidtemplate.business.PhotoLocalRepository
import com.fiftyfive.nativeandroidtemplate.presentation.viewstates.PhotoDetailViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ProductDetailsViewModelTest {

    private val repositoryLocal = mockk<PhotoLocalRepository>(relaxed = true)
    private lateinit var viewModel: PhotoDetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = PhotoDetailViewModel(repositoryLocal)
    }

    @Test
    fun `Test loading state is shown before the content`() {
        val viewStates = mutableListOf<PhotoDetailViewState>()

        val viewModelScope = CoroutineScope(Dispatchers.IO)

        viewModelScope.launch {
            viewModel.photoDetailState.collect{
                viewStates.add(it)
            }
        }

        coEvery { repositoryLocal.getPhotoDetail("anyId") } returns mockk()

        assert(viewStates[0] is PhotoDetailViewState.Loading)
    }
}