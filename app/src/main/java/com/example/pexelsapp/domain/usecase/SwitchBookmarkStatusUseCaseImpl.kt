package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.domain.usecase.interfaces.SwitchBookmarkStatusUseCase
import javax.inject.Inject

class SwitchBookmarkStatusUseCaseImpl @Inject constructor(
    private val repository: PhotoRepository
): SwitchBookmarkStatusUseCase {
    override suspend fun execute(id: Int) {
        repository.switchBookmarkStatus(id)
    }
}