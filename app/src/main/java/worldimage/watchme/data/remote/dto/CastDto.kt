package worldimage.watchme.data.remote.dto

data class CastListDto(
    val cast: List<CastItemDto>,
    val crew: List<CrewItemDto>,
    val id: Int
)

data class CastItemDto(
    val adult: Boolean?,
    val cast_id: Int?,
    val character: String?,
    val credit_id: String?,
    val gender: Int?,
    val id: Int?,
    val known_for_department: String?,
    val name: String?,
    val order: Int?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?
)

data class CrewItemDto(
    val adult: Boolean?,
    val credit_id: String?,
    val department: String?,
    val gender: Int?,
    val id: Int?,
    val job: String?,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?
)