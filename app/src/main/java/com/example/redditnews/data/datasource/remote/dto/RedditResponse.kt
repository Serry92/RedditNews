package com.example.redditnews.data.datasource.remote.dto

data class RedditResponse(val data: RedditData)
data class RedditData(val children: List<RedditChildren>)
data class RedditChildren(val data: RedditPostDto)
data class RedditPostDto(
    val title: String,
    val selftext: String?,
    val url: String,
    val media: Media?,
)

data class Media(
    val oembed: Oembed?,
)

data class Oembed(
    val thumbnail_url: String,
)