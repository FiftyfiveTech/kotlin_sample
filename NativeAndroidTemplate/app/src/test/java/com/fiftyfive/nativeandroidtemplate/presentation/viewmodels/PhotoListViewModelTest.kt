package com.renarosantos.ecommerceapp.product_details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fiftyfive.nativeandroidtemplate.business.PhotoLocalRepository
import com.fiftyfive.nativeandroidtemplate.business.PhotoRepository
import com.fiftyfive.nativeandroidtemplate.presentation.viewmodels.PhotoListViewModel
import com.fiftyfive.nativeandroidtemplate.presentation.viewstates.PhotoListViewState
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

    private val repository = mockk<PhotoRepository>(relaxed = true)
    private val repositoryLocal = mockk<PhotoLocalRepository>(relaxed = true)
    private lateinit var viewModel: PhotoListViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = PhotoListViewModel(repository, repositoryLocal)
    }

    @Test
    fun `Test loading state is shown before the content`() {
        val viewStates = mutableListOf<PhotoListViewState>()

        val viewModelScope = CoroutineScope(Dispatchers.IO)

        viewModelScope.launch {
            viewModel.photoListState.collect{
                viewStates.add(it)
            }
        }

        coEvery { repository.getPhotos() } returns mockk()

        assert(viewStates[0] is PhotoListViewState.Loading)
    }
}