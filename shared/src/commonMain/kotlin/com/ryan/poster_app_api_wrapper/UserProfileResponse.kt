package com.ryan.poster_app_api_wrapper

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    val message: String,
    val user: UserProfile
)

@Serializable
data class UserProfile(
    val id: String,
    val username: String,
    val email: String,
    val profileImageUrl: String,
    val accountCreation: Long,
    val isAdmin: Boolean,
    val followers: List<Follower>,
    val following: List<Following>,
    val posts: List<Post>,
    val listeningHistory: List<ListeningHistoryItem>,
    val favouriteArtists: List<FavouriteArtist>,
    val currentlyPlaying: CurrentlyPlaying?
)

@Serializable
data class Follower(
    val id: String,
    val username: String,
    val accountCreation: Long,
    val spotifyLinked: Boolean? = null,
    val profileImageUrl: String? = null
)

@Serializable
data class Following(
    val id: String,
    val username: String,
    val accountCreation: Long,
    val profileImageUrl: String? = null
)

@Serializable
data class Post(
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
data class FavouriteArtist(
    val imageUrl: String,
    val name: String
)

@Serializable
data class CurrentlyPlaying(
    val name: String,
    val artists: List<String>
)
