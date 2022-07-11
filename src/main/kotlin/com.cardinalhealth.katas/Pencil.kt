package com.cardinalhealth.katas

class Paper(private var contents: String = "") {
    fun read() = contents

    fun write(characters: String) {
        contents += characters
    }
}

class Pencil() {
    fun write(paper: Paper, characters: String) {
        paper.write(characters)
    }
}
