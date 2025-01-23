package com.ang.anime.data.model

import com.google.gson.annotations.SerializedName


data class DataDashBoard (

  @SerializedName("mal_id"          ) var malId          : Int?                    = null,
  @SerializedName("url"             ) var url            : String?                 = null,
  @SerializedName("images"          ) var images         : ImagesDashBoard?                 = ImagesDashBoard(),
  @SerializedName("trailer"         ) var trailer        : Trailer?                = Trailer(),
  @SerializedName("approved"        ) var approved       : Boolean?                = null,
  @SerializedName("title"           ) var title          : String?                 = null,
  @SerializedName("title_english"   ) var titleEnglish   : String?                 = null,
  @SerializedName("title_japanese"  ) var titleJapanese  : String?                 = null,
  @SerializedName("title_synonyms"  ) var titleSynonyms  : ArrayList<String>       = arrayListOf(),
  @SerializedName("type"            ) var type           : String?                 = null,
  @SerializedName("source"          ) var source         : String?                 = null,
  @SerializedName("episodes"        ) var episodes       : Int?                    = null,
  @SerializedName("status"          ) var status         : String?                 = null,
  @SerializedName("airing"          ) var airing         : Boolean?                = null,
  @SerializedName("duration"        ) var duration       : String?                 = null,
  @SerializedName("rating"          ) var rating         : String?                 = null,
  @SerializedName("score"           ) var score          : Double?                 = null,
  @SerializedName("scored_by"       ) var scoredBy       : Int?                    = null,
  @SerializedName("rank"            ) var rank           : Int?                    = null,
  @SerializedName("popularity"      ) var popularity     : Int?                    = null,
  @SerializedName("members"         ) var members        : Int?                    = null,
  @SerializedName("favorites"       ) var favorites      : Int?                    = null,
  @SerializedName("synopsis"        ) var synopsis       : String?                 = null,
  @SerializedName("background"      ) var background     : String?                 = null,
  @SerializedName("season"          ) var season         : String?                 = null,
  @SerializedName("year"            ) var year           : Int?                    = null,
  @SerializedName("genres"            ) var genres           : List<Genres? >                   = emptyList<Genres>(),
)

data class Images (

  @SerializedName("image_url"         ) var imageUrl        : String? = null,
  @SerializedName("small_image_url"   ) var smallImageUrl   : String? = null,
  @SerializedName("medium_image_url"  ) var mediumImageUrl  : String? = null,
  @SerializedName("large_image_url"   ) var largeImageUrl   : String? = null,
  @SerializedName("maximum_image_url" ) var maximumImageUrl : String? = null

)

data class Trailer (

  @SerializedName("youtube_id" ) var youtubeId : String? = null,
  @SerializedName("url"        ) var url       : String? = null,
  @SerializedName("embed_url"  ) var embedUrl  : String? = null,
  @SerializedName("images"     ) var images    : Images? = Images()

)

data class ImagesDashBoard (

  @SerializedName("jpg"  ) var jpg  : Jpg?  = Jpg(),

)

data class Jpg (

  @SerializedName("image_url"       ) var imageUrl      : String? = null,
  @SerializedName("small_image_url" ) var smallImageUrl : String? = null,
  @SerializedName("large_image_url" ) var largeImageUrl : String? = null

)

data class Genres (

  @SerializedName("mal_id" ) var malId : Int?    = null,
  @SerializedName("type"   ) var type  : String? = null,
  @SerializedName("name"   ) var name  : String? = null,
  @SerializedName("url"    ) var url   : String? = null

)
