package com.app.onthisdayapp.presentation.data.models

import kotlinx.serialization.Serializable

@Serializable
data class WikiApiResponse(
    val selected: List<SelectedItem>
)

data class SelectedItem(
    val text: String,
    val pages: List<WikiPage>,
    val year: Int
)

data class WikiPage(
    val type: String?,
    val title: String?,
    val displaytitle: String?,
    val namespace: Namespace?,
    val wikibase_item: String?,
    val titles: Titles?,
    val pageid: Int?,
    val thumbnail: ImageInfo?,
    val originalimage: ImageInfo?,
    val lang: String?,
    val dir: String?,
    val revision: String?,
    val tid: String?,
    val timestamp: String?,
    val description: String?,
    val description_source: String?,
    val coordinates: Coordinates?,
    val content_urls: ContentUrls?,
    val extract: String?,
    val extract_html: String?,
    val normalizedtitle: String?
)

data class Namespace(
    val id: Int?,
    val text: String?
)

data class Titles(
    val canonical: String?,
    val normalized: String?,
    val display: String?
)

data class ImageInfo(
    val source: String?,
    val width: Int?,
    val height: Int?
)

data class Coordinates(
    val lat: Double?,
    val lon: Double?
)

data class ContentUrls(
    val desktop: PageLinks?,
    val mobile: PageLinks?
)

data class PageLinks(
    val page: String?,
    val revisions: String?,
    val edit: String?,
    val talk: String?
)
