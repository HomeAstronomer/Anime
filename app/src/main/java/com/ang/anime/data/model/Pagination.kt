package com.ang.anime.data.model

import com.google.gson.annotations.SerializedName


data class Pagination (

  @SerializedName("last_visible_page" ) var lastVisiblePage : Int?     = null,
  @SerializedName("has_next_page"     ) var hasNextPage     : Boolean? = null,
  @SerializedName("current_page"      ) var currentPage     : Int?     = null,
  @SerializedName("items"             ) var items           : Items?   = Items()

)

data class Items (

  @SerializedName("count"    ) var count   : Int? = null,
  @SerializedName("total"    ) var total   : Int? = null,
  @SerializedName("per_page" ) var perPage : Int? = null

)