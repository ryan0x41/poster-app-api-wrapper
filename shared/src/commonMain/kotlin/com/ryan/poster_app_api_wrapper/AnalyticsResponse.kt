import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnalyticsResponse(
    val server: ServerInfo,
    @SerialName("available_routes")
    val availableRoutes: List<RouteInfo>
)

@Serializable
data class ServerInfo(
    val hostname: String,
    val platform: String,
    val arch: String,
    @SerialName("node_version")
    val nodeVersion: String,
    @SerialName("memory_usage")
    val memoryUsage: String,
    val uptime: String,
    @SerialName("start_time")
    val startTime: String
)

@Serializable
data class RouteInfo(
    val path: String,
    val description: String
)
