package com.college.tangkis_rpl.model

import com.college.tangkis_rpl.R

data class ArtikelInformasi(
    val idArtikel: String = "",
    val judul: String = "",
    val image: Int = 0,
    val konten: String = "",
    val tanggalUnggah: String = "",
) {

    fun getDaftarArtikel() = listOf(
        ArtikelInformasi(
            idArtikel = "1",
            judul = "Panduan Penggunaan Fitur SOS",
            image = R.drawable.iv_panduan_sos,
            konten = "Pesan darurat atau fitur SOS adalah sistem perlindungan darurat yang disediakan di Fakultas Ilmu Komputer Universitas Brawijaya. Fitur ini bertujuan untuk memberikan perlindungan dan kenyamanan kepada para mahasiswa dalam situasi-situasi yang memerlukan tindakan cepat, seperti pelecehan seksual.\n" +
                    "\n" +
                    "Cara Menggunakan Fitur SOS\n" +
                    "Pastikan telah menambahkan kontak darurat terlebih dahulu dalam aplikasi Tangkis. Setelah itu, langkah-langkah yang dilakukan yaitu\n" +
                    "Terdapat tombol bertuliskan \"SOS.\" Tekan tombol ini untuk memicu proses penggunaan fitur SOS.\n" +
                    "Setelah menekan akan diarahkan ke halaman pesan darurat. Di halaman ini, terdapat tombol besar bertuliskan \"SOS.\" Namun, ada syarat khusus yang harus dipenuhi untuk mengaktifkan tombol SOS. Anda harus berada dalam radius fakultas Ilmu Komputer agar tombol SOS dapat digunakan. Jika Anda berada di luar radius, tombol SOS tidak akan aktif.\n" +
                    "Setelah memastikan bahwa Anda berada dalam radius FILKOM UB, tekan tombol SOS. Setelahnya, halaman akan menampilkan waktu countdown selama 5 detik. Selama periode countdown ini, Anda masih memiliki opsi untuk membatalkan pengiriman pesan darurat dengan menekan tombol pembatalan (cancel).\n" +
                    "Jika Anda memutuskan untuk melanjutkan dan tidak membatalkan pengiriman pesan darurat selama countdown berlangsung, maka setelah 5 detik, halaman akan menampilkan pemberitahuan bahwa pesan darurat telah berhasil dikirimkan ke kontak darurat yang telah Anda tetapkan sebelumnya.\n" +
                    "Opsi untuk Menelpon Ultksp: Setelah pengiriman pesan darurat berhasil, Anda akan diberikan opsi untuk menelpon ULTKSP (Unit Layanan Terpadu Keamanan dan Keselamatan Universitas). Ini adalah langkah selanjutnya untuk penanganan lebih lanjut dalam situasi darurat. Anda dapat menekan tombol untuk langsung menghubungi ULTKSP, yang akan memberikan bantuan dan koordinasi yang diperlukan.",
            tanggalUnggah = "29 Oktober 2023 "
        ),
        ArtikelInformasi(
            idArtikel = "2",
            judul = "Unit Layanan Terpadu Kekerasan Seksual dan Perundungan FILKOM",
            image = R.drawable.iv_ultksp,
            konten = "Kekerasan Seksual adalah setiap perbuatan menghina, menyerang, dan/atau perbuatan lainnya terhadap tubuh, hasrat seksual seseorang, dan/atau fungsi reproduksi, secara paksa, atau bertentangan dengan kehendak seseorang serta dalam kondisi seseorang itu serta tidak mampu memberikan persetujuan dalam keadaan bebas karena ketimpangan relasi kuasa dan/atau relasi gender, yang berakibat atau dapat berakibat penderitaan atau kesengsaraan secara fisik, psikis, seksual yang dilakukan oleh dan/atau terhadap Mahasiswa FILKOM dalam melaksanakan kegiatan pendidikan, penelitian, dan pengabdian kepada masyarakat, serta kegiatan lain yang berada di bawah tanggung jawab FILKOM\n" +
                    "Bullying atau perundungan sendiri adalah sebuah kegiatan penyalahgunaan kekuasaan atau ‘kekuatan’ yang bertujuan untuk menyakiti orang lain baik dalam bentuk fisik, psikis atau perkataan sehingga sang korban seringnya akan merasakan sakit, depresi atau terjebak dalam keputusasaan. Biasanya, pelaku adalah orang yang merasa mempunyai posisi yang lebih tinggi atau lebih ‘kuat’ dari sang korban.\n" +
                    "Unit Layanan Terpadu Kekerasan Seksual dan Perundungan yang selanjutnya disingkat ULTKSP adalah sistem pelayanan dan penindakan yang dilakukan secara koordinatif dan terintegrasi. Merupakan tempat pelayanan bagi Mahasiswa FILKOM untuk memberikan layanan informasi yang dibutuhkan termasuk menerima dan mendokumentasikan laporan dugaan Kekerasan Seksual dan Perundungan.\n" +
                    "\n" +
                    "Layanan Berupa:\n" +
                    "Pelayanan dan Pendampingan terhadap Korban; dan\n" +
                    "Penindakan terhadap Pelaku.\n" +
                    "\n" +
                    "Jangka Waktu Pelayanan dan Biaya:\n" +
                    "Pelayanan awal diberikan paling lambat 3×24 (tiga kali dua puluh empat) jam sejak ULTKSP menerima laporan dugaan tindakan Kekerasan Seksual dan Perundungan\n" +
                    "Tidak ada biaya yang dikenakan",
            tanggalUnggah = "1 November 2023 "
        ),
    )

    fun getArtikelInformasi(idArtikel: String): ArtikelInformasi? {
        val daftarArtikel = listOf(
            ArtikelInformasi(
                idArtikel = "1",
                judul = "Panduan Penggunaan Fitur SOS",
                image = R.drawable.iv_panduan_sos,
                konten = "Pesan darurat atau fitur SOS adalah sistem perlindungan darurat yang disediakan di Fakultas Ilmu Komputer Universitas Brawijaya. Fitur ini bertujuan untuk memberikan perlindungan dan kenyamanan kepada para mahasiswa dalam situasi-situasi yang memerlukan tindakan cepat, seperti pelecehan seksual.\n" +
                        "\n" +
                        "Cara Menggunakan Fitur SOS\n" +
                        "Pastikan telah menambahkan kontak darurat terlebih dahulu dalam aplikasi Tangkis. Setelah itu, langkah-langkah yang dilakukan yaitu\n" +
                        "Terdapat tombol bertuliskan \"SOS.\" Tekan tombol ini untuk memicu proses penggunaan fitur SOS.\n" +
                        "Setelah menekan akan diarahkan ke halaman pesan darurat. Di halaman ini, terdapat tombol besar bertuliskan \"SOS.\" Namun, ada syarat khusus yang harus dipenuhi untuk mengaktifkan tombol SOS. Anda harus berada dalam radius fakultas Ilmu Komputer agar tombol SOS dapat digunakan. Jika Anda berada di luar radius, tombol SOS tidak akan aktif.\n" +
                        "Setelah memastikan bahwa Anda berada dalam radius FILKOM UB, tekan tombol SOS. Setelahnya, halaman akan menampilkan waktu countdown selama 5 detik. Selama periode countdown ini, Anda masih memiliki opsi untuk membatalkan pengiriman pesan darurat dengan menekan tombol pembatalan (cancel).\n" +
                        "Jika Anda memutuskan untuk melanjutkan dan tidak membatalkan pengiriman pesan darurat selama countdown berlangsung, maka setelah 5 detik, halaman akan menampilkan pemberitahuan bahwa pesan darurat telah berhasil dikirimkan ke kontak darurat yang telah Anda tetapkan sebelumnya.\n" +
                        "Opsi untuk Menelpon Ultksp: Setelah pengiriman pesan darurat berhasil, Anda akan diberikan opsi untuk menelpon ULTKSP (Unit Layanan Terpadu Keamanan dan Keselamatan Universitas). Ini adalah langkah selanjutnya untuk penanganan lebih lanjut dalam situasi darurat. Anda dapat menekan tombol untuk langsung menghubungi ULTKSP, yang akan memberikan bantuan dan koordinasi yang diperlukan.",
                tanggalUnggah = "29 Oktober 2023 "
            ),
            ArtikelInformasi(
                idArtikel = "2",
                judul = "Unit Layanan Terpadu Kekerasan Seksual dan Perundungan FILKOM",
                image = R.drawable.iv_ultksp,
                konten = "Kekerasan Seksual adalah setiap perbuatan menghina, menyerang, dan/atau perbuatan lainnya terhadap tubuh, hasrat seksual seseorang, dan/atau fungsi reproduksi, secara paksa, atau bertentangan dengan kehendak seseorang serta dalam kondisi seseorang itu serta tidak mampu memberikan persetujuan dalam keadaan bebas karena ketimpangan relasi kuasa dan/atau relasi gender, yang berakibat atau dapat berakibat penderitaan atau kesengsaraan secara fisik, psikis, seksual yang dilakukan oleh dan/atau terhadap Mahasiswa FILKOM dalam melaksanakan kegiatan pendidikan, penelitian, dan pengabdian kepada masyarakat, serta kegiatan lain yang berada di bawah tanggung jawab FILKOM\n" +
                        "Bullying atau perundungan sendiri adalah sebuah kegiatan penyalahgunaan kekuasaan atau ‘kekuatan’ yang bertujuan untuk menyakiti orang lain baik dalam bentuk fisik, psikis atau perkataan sehingga sang korban seringnya akan merasakan sakit, depresi atau terjebak dalam keputusasaan. Biasanya, pelaku adalah orang yang merasa mempunyai posisi yang lebih tinggi atau lebih ‘kuat’ dari sang korban.\n" +
                        "Unit Layanan Terpadu Kekerasan Seksual dan Perundungan yang selanjutnya disingkat ULTKSP adalah sistem pelayanan dan penindakan yang dilakukan secara koordinatif dan terintegrasi. Merupakan tempat pelayanan bagi Mahasiswa FILKOM untuk memberikan layanan informasi yang dibutuhkan termasuk menerima dan mendokumentasikan laporan dugaan Kekerasan Seksual dan Perundungan.\n" +
                        "\n" +
                        "Layanan Berupa:\n" +
                        "Pelayanan dan Pendampingan terhadap Korban; dan\n" +
                        "Penindakan terhadap Pelaku.\n" +
                        "\n" +
                        "Jangka Waktu Pelayanan dan Biaya:\n" +
                        "Pelayanan awal diberikan paling lambat 3×24 (tiga kali dua puluh empat) jam sejak ULTKSP menerima laporan dugaan tindakan Kekerasan Seksual dan Perundungan\n" +
                        "Tidak ada biaya yang dikenakan",
                tanggalUnggah = "1 November 2023 "
            ),
        )
        for (artikel in daftarArtikel) {
            if (artikel.idArtikel == idArtikel) {
                return artikel
            }
        }
        return null
    }
}
