inline fun <T: Any, R: Any> multiLet(vararg variables: T?, out: (List<T>) -> R?): R? {
    return if (variables.all { it != null }) out (listOfNotNull(*variables)) else null
}
