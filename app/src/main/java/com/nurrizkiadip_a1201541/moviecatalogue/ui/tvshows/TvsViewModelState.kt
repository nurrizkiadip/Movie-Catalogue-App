package com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows

import com.nurrizkiadip_a1201541.moviecatalogue.data.TvShowEntity

sealed class TvsViewModelState

object Tvs : TvsViewModelState()
data class EmptyTvs(val message: String) : TvsViewModelState()
data class SuccessTvs(val listTvs: List<TvShowEntity>) : TvsViewModelState()
data class ErrorTvs(val message: String) : TvsViewModelState()