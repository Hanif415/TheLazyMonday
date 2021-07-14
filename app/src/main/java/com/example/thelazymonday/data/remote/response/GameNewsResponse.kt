package com.example.thelazymonday.data.remote.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameNewsResponse(

	@Json(name="GameNewsResponse")
	val gameNewsResponse: List<GameNewsResponseItem?>? = null
) : Parcelable

@Parcelize
data class GameNewsResponseItem(

	@Json(name="thumb")
	val thumb: String? = null,

	@Json(name="author")
	val author: String? = null,

	@Json(name="tag")
	val tag: String? = null,

	@Json(name="time")
	val time: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="key")
	val key: String? = null,

	@Json(name="desc")
	val desc: String? = null
) : Parcelable
