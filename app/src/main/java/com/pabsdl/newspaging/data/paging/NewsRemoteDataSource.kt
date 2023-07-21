package com.pabsdl.newspaging.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pabsdl.newspaging.data.model.NewsItem
import com.pabsdl.newspaging.data.network.NewsService
import retrofit2.HttpException
import java.io.IOException

const val NETWORK_PAGE_SIZE = 37

class NewsRemoteDataSource(private val newsService: NewsService): PagingSource<Int, NewsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        try {
            val pageKeyToLoad = params.key ?: 1 // Key is page number
            val response = newsService.getNews(page = pageKeyToLoad)
            val responseData = mutableListOf<NewsItem>()
            val loadedData = response.body()?.articles ?: arrayListOf()
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

    override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
        // Get the page to load when data needs to be refreshed
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}