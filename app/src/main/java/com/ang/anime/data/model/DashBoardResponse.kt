package com.ang.anime.data.model

import com.google.gson.annotations.SerializedName


data class DashBoardResponse (

  @SerializedName("pagination" ) var pagination : Pagination?     = Pagination(),
  @SerializedName("data"       ) var data       : ArrayList<DataDashBoard> = arrayListOf()

)

data class  DetailScreenResponse(
  @SerializedName("data"       ) var data       : DataDashBoard? = DataDashBoard()

)