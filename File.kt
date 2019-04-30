fun File.extractName(): String {
    return this.extractNameWithExtension().substringBeforeLast(".")
}

fun File.extractNameWithExtension(): String {
    return this.absolutePath.substringAfterLast("/")
}

fun File.extractExtension(): String {
    return this.absolutePath.substringAfterLast(".")
}
