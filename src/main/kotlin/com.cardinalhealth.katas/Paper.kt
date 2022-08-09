package com.cardinalhealth.katas

//It's preference, but I think breaking out classes like this make the project easier to navigate (more options for how to get somewhere, easier package scanning)
class Paper(contents: String = "") {
    //I'd use property access here instead of a getter
    var contents = contents
        private set

    //I really like your separation of concerns between paper and pencil. There are a lot of different places to draw this boundary and I like that your paper has no pencil 'business logic'.
    fun write(characters: String) {
        contents += characters
    }

    //This method feels really clean to me. Nice use of kotlin std lib
    fun erase(characters: String, eraseLength: Int) {
        contents.findLastAnyOf(listOf(characters))?.let { (pos, _) ->
            val end = pos + characters.length
            val start = end - eraseLength
            contents = contents.replaceRange(start, end, " ".repeat(eraseLength))
        }
    }
}
