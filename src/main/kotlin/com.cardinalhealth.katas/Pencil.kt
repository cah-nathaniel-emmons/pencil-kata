package com.cardinalhealth.katas

class Paper(private var contents: String = "") {
    fun read() = contents

    fun write(characters: String) {
        contents += characters
    }

    fun erase(characters: String) {
        contents.findLastAnyOf(listOf(characters))?.let { (pos, _) ->
            contents = contents.replaceRange(pos, pos + characters.length, " ".repeat(characters.length))
        }
    }
}

class Pencil(
    private val initialPointDurability: Int = 10000,
    initialLength: Int = 8,
    initialEraserDurability: Int = 200
) {
    private val freeCharacters = listOf(' ', '\n')

    //TODO remove public setters
    var pointDurability = initialPointDurability
    var length = initialLength
    var eraserDurability = initialEraserDurability

    fun write(paper: Paper, characters: String) {
        val pointDurabilityCost = characters.sumOf { charPointDurabilityCost(it) }

        if (pointDurability < pointDurabilityCost) {
            var spent = 0
            var writeableCharacters = ""

            characters.forEach { char ->
                val cost = charPointDurabilityCost(char)

                if (freeCharacters.contains(char)) {
                    writeableCharacters += char
                } else if (spent + cost <= pointDurability) {
                    writeableCharacters += char
                    spent += cost
                } else {
                    writeableCharacters += " "
                }
            }
            pointDurability = 0
            paper.write(writeableCharacters)
        } else {
            pointDurability -= pointDurabilityCost
            paper.write(characters)
        }
    }

    fun erase(paper: Paper, characters: String): Pencil {
        paper.erase(characters)
        eraserDurability -= characters.length
        return this
    }

    fun sharpen() {
        if (length > 0) {
            pointDurability = initialPointDurability
            length -= 1
        }
    }

    private fun charPointDurabilityCost(character: Char): Int {
        if (freeCharacters.contains(character)) {
            return 0
        } else if (character.isUpperCase()) {
            return 2
        }
        return 1
    }
}
