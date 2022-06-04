package com.kimym.rickandmorty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

class RickAndMortyPagingSource<T : Any>(
    private val getDataFromNetwork: suspend (Int) -> List<T>,
) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val page = params.key ?: 1
            val data = getDataFromNetwork(page)
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (data.size < 20) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
