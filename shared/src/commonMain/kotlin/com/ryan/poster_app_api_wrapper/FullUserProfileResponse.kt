package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.Serializable

@Serializable
data class FullUserProfileResponse (
    val message: String,
    val user: FullUserProfile
)

@Serializable
data class FullUserProfile(
    val id: String,
    val username: String,
    val email: String,
    val profileImageUrl: String,
    val accountCreation: Long,
    val isAdmin: Boolean,
    val followers: List<FullFollower>,
    val following: List<FullFollowing>,
    val posts: List<FullPost>,
    val listeningHistory: List<FullListeningHistoryItem>,
    val favouriteArtists: List<FullFavouriteArtist>,
    val currentlyPlaying: FullCurrentlyPlaying?
)

@Serializable
data class FullFollower(
    val id: String,
    val username: String,
    val accountCreation: Long,
    val spotifyLinked: Boolean? = null,
    val profileImageUrl: String? = null
)

@Serializable
data class FullFollowing(
    val id: String,
    val username: String,
    val accountCreation: Long,
    val profileImageUrl: String? = null
)

@Serializable
data class FullPost(
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
data class FullListeningHistoryItem(
    val albumCover: String,
    val artistName: String,
    val songName: String
)

@Serializable
data class FullFavouriteArtist(
    val imageUrl: String,
    val name: String
)

@Serializable
data class FullCurrentlyPlaying(
    val name: String,
    val artists: List<String>
)
