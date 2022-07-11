package com.cardinalhealth.katas

class Eraser {

    fun erase(full: String, toRemove: String) : String {
        val where = full.lastIndexOf(toRemove)

        var result = ""

        full.forEachIndexed { i, element ->

            if (i < where || i >= where + toRemove.length) {
                result += element
            } else {
                result += " "
            }
        }
        return result
    }
}
