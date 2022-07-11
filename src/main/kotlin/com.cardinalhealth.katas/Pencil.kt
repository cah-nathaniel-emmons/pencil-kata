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

    private fun charPointDurabilityCost(character: Char): Int {
        if (freeCharacters.contains(character)) {
            return 0
        } else if (character.isUpperCase()) {
            return 2
        }
        return 1
    }
}
