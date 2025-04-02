package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeFeed(
    val message: String,
    val posts: List<HomeFeedPost>,
    val page: Int
)

@Serializable
data class HomeFeedPost(
    @SerialName("postId")
    val postId: String,
    val title: String,
    val content: String,
    val author: String,
    val postDate: String,
    val likes: Int,
    val likedBy: List<String>,
    val images: List<String>,
    val userProfile: HomeFeedUserProfile
)

@Serializable
data class HomeFeedUserProfile(
    val id: String,
    val username: String,
    val email: String,
    val accountCreation: Long,
    val profileImageUrl: String? = null,
    val followers: List<HomeFeedUserSummary> = emptyList(),
    val following: List<HomeFeedUserSummary> = emptyList(),
    val posts: List<HomeFeedPostSummary> = emptyList(),
    val listeningHistory: List<HomeFeedListeningHistoryItem> = emptyList(),
    val favouriteArtists: List<HomeFeedArtist> = emptyList(),
    val currentlyPlaying: FullCurrentlyPlaying? = null
)

@Serializable
data class HomeFeedUserSummary(
    val id: String,
    val username: String,
    val accountCreation: Long,
    val profileImageUrl: String? = null,
    val spotifyLinked: Boolean? = null
)

@Serializable
data class HomeFeedPostSummary(
    @SerialName("postId")
    val postId: String,
    val title: String,
    val content: String,
    val author: String,
    val postDate: String,
    val likes: Int,
    val likedBy: List<String>,
    val images: List<String>
)

@Serializable
data class HomeFeedListeningHistoryItem(
    val albumCover: String,
    val artistName: String,
    val songName: String
)

@Serializable
data class HomeFeedArtist(
    val imageUrl: String,
    val name: String
)
