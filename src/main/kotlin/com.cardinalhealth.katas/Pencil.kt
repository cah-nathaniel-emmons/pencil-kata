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
        val pointDurabilityCost = characters.filter { !freeCharacters.contains(it) }.length

        if (pointDurability < pointDurabilityCost) {
            var spent = 0
            var writeableCharacters = ""

            characters.forEach { char ->
                if (freeCharacters.contains(char)) {
                    writeableCharacters += char
                } else if (spent == pointDurability) {
                    writeableCharacters += " "
                } else {
                    writeableCharacters += char
                    spent++
                }
            }
            pointDurability = 0
            paper.write(writeableCharacters)
        } else {
            pointDurability -= pointDurabilityCost
            paper.write(characters)
        }
    }
}
