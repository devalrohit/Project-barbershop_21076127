package com.example.Project_barbershop

class Data(
    val id: Int,
    val username: String,
    val password: String,
    val alamat: String
) {
    var nama: String? = null
    var nomor_hp: String? = null
    var id_menu: Int? = null
    var menu: String? = null

    constructor(
        id: Int,
        nama: String,
        password: String,
        alamat: String,
        nomor_hp: String,
        id_menu: Int,
        menu: String
    ) : this(id, nama, password, alamat) {
        this.nomor_hp = nomor_hp
        this.id_menu = id_menu
        this.menu = menu
    }
}
