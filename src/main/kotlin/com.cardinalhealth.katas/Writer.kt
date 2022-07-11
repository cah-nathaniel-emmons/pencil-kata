package com.cardinalhealth.katas

class Writer(var stringArray: String = "") {
    fun write(toAdd: String): String {
        stringArray += toAdd
        return stringArray
    }

    fun erase(toRemove: String) : String {
        val where = stringArray.lastIndexOf(toRemove)

        var result = ""

        stringArray.forEachIndexed { i, element ->

            if (i < where || i >= where + toRemove.length) {
                result += element
            } else {
                result += " "
            }
        }

        stringArray = result
        return result
    }
}
