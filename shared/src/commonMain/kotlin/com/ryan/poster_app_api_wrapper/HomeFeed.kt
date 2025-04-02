package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeFeed(
    val message: String,
    val posts: List<Post>,
    val page: Int
)

@Serializable
data class Post(
    @SerialName("postId")
    val postId: String,
    val title: String,
    val content: String,
    val author: String,
    val postDate: String,
    val likes: Int,
    val likedBy: List<String>,
    val images: List<String>,
    val userProfile: UserProfile
)

@Serializable
data class UserProfile(
    val id: String,
    val username: String,
    val email: String,
    val accountCreation: Long,
    val followers: List<UserSummary> = emptyList(),
    val following: List<UserSummary> = emptyList(),
    val posts: List<PostSummary> = emptyList(),
    val listeningHistory: List<ListeningHistoryItem> = emptyList(),
    val favouriteArtists: List<Artist> = emptyList(),
    val currentlyPlaying: CurrentlyPlaying? = null
)

@Serializable
data class UserSummary(
    val id: String,
    val username: String,
    val accountCreation: Long,
    val profileImageUrl: String? = null,
    val spotifyLinked: Boolean? = null
)

@Serializable
data class PostSummary(
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
data class ListeningHistoryItem(
    val albumCover: String,
    val artistName: String,
    val songName: String
)

@Serializable
data class Artist(
    val imageUrl: String,
    val name: String
)
