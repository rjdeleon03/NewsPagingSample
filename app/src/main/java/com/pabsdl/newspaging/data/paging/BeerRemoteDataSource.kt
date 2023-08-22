package com.pabsdl.newspaging.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pabsdl.newspaging.data.model.BeerItem
import com.pabsdl.newspaging.data.network.BeerService
import com.pabsdl.newspaging.data.network.BeerService.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException


class BeerRemoteDataSource(private val beerService: BeerService): PagingSource<Int, BeerItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerItem> {
        try {
            val pageKeyToLoad = params.key ?: 1 // Key is page number
            val response = beerService.getNews(page = pageKeyToLoad)
            val responseData = mutableListOf<BeerItem>()
            val loadedData = response.body() ?: arrayListOf()
            responseData.addAll(loadedData)

            val prevKey = if (pageKeyToLoad == 1) null else pageKeyToLoad - 1

            val nextKey = if (loadedData.isEmpty()) {
                // Means end has been reached
                null
            } else {
                // Initial load size: 3 * NETWORK_PAGE_SIZE
                // Ensure we're not requesting duplicate items
                pageKeyToLoad + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (ex: IOException) {
            return LoadResult.Error(ex)
        } catch (ex: HttpException) {
            return LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BeerItem>): Int? {
        // Get the page to load when data needs to be refreshed
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
