package com.cardinalhealth.katas

class Paper(private var contents: String = "") {
    fun read() = contents

    fun write(characters: String) {
        contents += characters
    }
}

class Pencil(
    var pointDurability: Int = 10000
) {
    private val freeCharacters = listOf(' ', '\n')

    fun write(paper: Paper, characters: String) {
        pointDurability -= characters.filter { !freeCharacters.contains(it) }.length
        paper.write(characters)
    }
}
