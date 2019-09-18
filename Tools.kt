inline fun <T: Any, R: Any> multiLet(vararg variables: T?, out: (List<T>) -> R?): R? {
    return if (variables.all { it != null }) out (listOfNotNull(*variables)) else null
}

fun <T> randomFrom(vararg variables: T): T {
    return variables.random()
}

fun Boolean.then(action: () -> Unit): Boolean {
    if (this) action.invoke()
    return this
}

fun Boolean.orNot(action: () -> Unit): Boolean {
    if (!this) action.invoke()
    return this
}

fun benchmarkAction(actionName: String, action: () -> Unit) {
    Log.i("BENCHMARK", "___________________________________")
    Log.i("BENCHMARK", "Action name: $actionName")
    val startTime = System.currentTimeMillis()
    Log.i("BENCHMARK", "Start time: $startTime")
    action.invoke()
    val endTime = System.currentTimeMillis()
    Log.i("BENCHMARK", "End time: $endTime")
    Log.i("BENCHMARK", "Action duration (millis): ${endTime - startTime}}")
}

fun Double.metersToLatLonDegrees(): Double {
    return this * 0.00001
}

fun <T>Collection<T>.hasOnlySingleItem(): Boolean {
    return this.size == 1
}

fun <T>MutableList<T>.addOrRemove(element: T) {
    if (this.contains(element)) this.remove(element) else this.add(element)
}

fun TextView.setTextOrHide(text: String?) {
    text?.let {
        this.text = it
    } ?: run {
        this.visibility = View.GONE
    }
}

fun TextView.setTextWithAnimation(text: String, duration: Long) {
    val stepDuration = duration/2
    this.animate()
            .alpha(0f)
            .setDuration(stepDuration)
            .withEndAction {
                this.text = text
                this.animate()
                        .alpha(1f)
                        .setDuration(stepDuration)
                        .start()
            }.start()
}

fun <T> Collection<T>?.joinToStringOrNull(separator: CharSequence): String? {
    return when {
        (this == null || this.count() == 0) -> null
        else -> {
            var result = ""
            this.forEachIndexed {index, item ->
                result += if (index != this.size - 1) "$item," else "$item"
            }
            result
        }
    }
}

fun <T> List<T>.second(): T? {
    return when {
        isEmpty() -> null
        size < 2 -> null
        else -> this[1]
    }
}

fun <T>allIsEqual(vararg values: T): Boolean {
    when {
        values.isEmpty() -> return false
        values.size == 1 -> return true
    }
    values.forEach {
        if ((it == values.first()).not()) return false
    }
    return true
}

fun <T, R>allIsNotNull(vararg values: T, out: () -> R?): R? {
    values.forEach {
        if (it == null) return null
    }
    return out()
}
