package eu.kanade.tachiyomi.extension.en.manga347

import eu.kanade.tachiyomi.source.model.Filter

object Note : Filter.Header("NOTE: Ignored if using text search!")

sealed class Select(
    name: String,
    val param: String,
    values: Array<String>,
) : Filter.Select<String>(name, values) {
    open val selection: String
        get() = if (state == 0) "" else state.toString()
}

class StatusFilter(
    values: Array<String> = statuses.keys.toTypedArray(),
) : Select("Status", "status", values) {
    override val selection: String
        get() = statuses[values[state]]!!

    companion object {
        private val statuses = mapOf(
            "All" to "",
            "Completed" to "completed",
            "OnGoing" to "on-going",
            "On-Hold" to "on-hold",
            "Canceled" to "canceled",
        )
    }
}

class SortFilter(
    values: Array<String> = orders.keys.toTypedArray(),
) : Select("Sort", "sort", values) {
    override val selection: String
        get() = orders[values[state]]!!

    companion object {
        private val orders = mapOf(
            "Default" to "default",
            "Latest Updated" to "latest-updated",
            "Most Viewed" to "views",
            "Most Viewed Month" to "views_month",
            "Most Viewed Week" to "views_week",
            "Most Viewed Day" to "views_day",
            "Score" to "score",
            "Name A-Z" to "az",
            "Name Z-A" to "za",
            "The highest chapter count" to "chapters",
            "Newest" to "new",
            "Oldest" to "old",
        )
    }
}

class Genre(name: String, val id: String) : Filter.CheckBox(name)

class GenresFilter(
    values: List<Genre> = genres,
) : Filter.Group<Genre>("Genres", values) {
    val param = "genres"

    val selection: String
        get() = state.filter { it.state }.joinToString(",") { it.id }

    companion object {
        private val genres: List<Genre>
            get() = listOf(
                Genre("Action", "1"),
                Genre("Adaptation", "47"),
                Genre("Adventure", "5"),
                Genre("Comedy", "9"),
                Genre("Drama", "3"),
                Genre("Ecchi", "17"),
                Genre("Fantasy", "197"),
                Genre("Full Color", "13"),
                Genre("Harem", "222"),
                Genre("Historical", "4"),
                Genre("Horror", "5"),
                Genre("Isekai", "259"),
                Genre("Long Strip", "7"),
                Genre("Magic", "272"),
                Genre("Manhwa", "266"),
                Genre("Martial Arts", "40"),
                Genre("Mature", "5311"),
                Genre("Monster", "2830"),
                Genre("Murim", "1598"),
                Genre("Mystery", "6"),
                Genre("Romance", "186"),
                Genre("School Life", "601"),
                Genre("Sci-Fi", "1845"),
                Genre("Seinen", "731"),
                Genre("Shounen", "254"),
                Genre("Super power", "481"),
                Genre("Supernatural", "198"),
                Genre("Survival", "44"),
                Genre("Thriller", "1058"),
                Genre("Time Travel", "299"),
                Genre("Tragedy", "41"),
                Genre("Video Games", "1846"),
                Genre("Villainess", "278"),
                Genre("Virtual Reality", "1847"),
                Genre("Web Comic", "12"),
                Genre("Webtoon", "279"),
            )
    }
}
