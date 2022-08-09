package com.cardinalhealth.katas

import kotlin.math.min

//I appreciate the constructor defaults
class Pencil(
    private val initialPointDurability: Int = 10000,
    initialLength: Int = 8,
    initialEraserDurability: Int = 200
) {
    //Personally I think I like a trim or isBlank or something, but I think that's just cause I like the abstraction. I think this is just as valid and maybe performs better?
    private val freeCharacters = setOf(' ', '\n')

    var pointDurability = initialPointDurability
        private set
    var length = initialLength
        private set
    var eraserDurability = initialEraserDurability
        private set

    //I think there are a bunch of ways you can do this, and I think your version is easily parsable/readable
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
            //I did this too and I think it makes sense / is optimal, but you technically don't need it if you change the above pointDurability = 0 to `pointDurability -= spent`. I think this else is nice though to call out this specific case.
            pointDurability -= pointDurabilityCost
            paper.write(characters)
        }
    }

    //I don't think it makes sense for this to return a pencil
    fun erase(paper: Paper, characters: String) {
        val eraseLength = min(eraserDurability, characters.length)
        paper.erase(characters, eraseLength)
        eraserDurability -= eraseLength
    }

    fun sharpen() {
        if (length > 0) {
            pointDurability = initialPointDurability
            length -= 1
        }
    }

    private fun charPointDurabilityCost(character: Char) = when {
        freeCharacters.contains(character) -> 0
        character.isUpperCase() -> 2
        else -> 1
    }
}
