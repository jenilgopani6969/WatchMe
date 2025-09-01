package worldimage.watchme.presentation.moviedetails.state

import worldimage.watchme.domain.model.CastDetails

data class CastAndCrewListState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val castList: List<CastDetails> = emptyList()
)