package com.college.tangkis_rpl.model

data class ArtikelInformasi(
    val articleId: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val content: String = "",
    val postDate: String = "",
) {

    fun getDaftarArtikel() = listOf(
        ArtikelInformasi(
            articleId = "1",
            title = "Artikel 1",
            imageUrl = "",
            content = "Konten artikel 1",
            postDate = "9 September 2023"
        ),
        ArtikelInformasi(
            articleId = "2",
            title = "Artikel 2",
            imageUrl = "",
            content = "Konten artikel 2",
            postDate = "9 September 2023"
        ),
        ArtikelInformasi(
            articleId = "3",
            title = "Artikel 3",
            imageUrl = "",
            content = "Konten artikel 2",
            postDate = "9 September 2023"
        )
    )

    fun getArtikelInformasi(idArtikel: String): ArtikelInformasi? {
        val daftarArtikel = listOf(
            ArtikelInformasi(
                articleId = "1",
                title = "Artikel 1",
                imageUrl = "",
                content = "Konten artikel 1",
                postDate = "9 September 2023"
            ),
            ArtikelInformasi(
                articleId = "2",
                title = "Artikel 2",
                imageUrl = "",
                content = "Konten artikel 2",
                postDate = "9 September 2023"
            ),
            ArtikelInformasi(
                articleId = "3",
                title = "Artikel 3",
                imageUrl = "",
                content = "Konten artikel 2",
                postDate = "9 September 2023"
            )
        )
        for (artikel in daftarArtikel) {
            if (artikel.articleId == idArtikel) {
                return artikel
            }
        }
        return null
    }
}
