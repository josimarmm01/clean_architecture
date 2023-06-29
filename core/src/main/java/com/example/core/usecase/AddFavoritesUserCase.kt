package com.example.core.usecase

import com.example.core.data.repository.FavoritesRepository
import com.example.core.domain.model.Movie
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AddFavoritesUserCase {

    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

    data class Params(val idMovie: Int,
                      val name: String,
                      val imgUrl: String,
                      val idUser: Int)

}

class AddFavoritesUserCaseImpl @Inject constructor (
    private val repository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
        ): UseCase<AddFavoritesUserCase.Params, Unit>(), AddFavoritesUserCase {

    override suspend fun doWork(params: AddFavoritesUserCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            repository.saveFavorite(
                movie = Movie(params.idMovie, params.name, params.imgUrl)
            )
            ResultStatus.Success(Unit)
        }
    }
}
