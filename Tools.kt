
/**
 * Multiple let. Allows you to use .let for few variables combinig its in vararg 
 * and if all of them is not null returning list of checked non-null values with same order.
 * 
 * multiLet(a,b,c) { values ->
 *     print("a is not null and = ${values[0]}")
 *     print("b is not null and = ${values[1]}")
 *     print("c is not null and = ${values[2]}")
 * }
 * 
 * @property variables - vararg of input nullable variables
 */
inline fun <T: Any, R: Any> multiLet(vararg variables: T?, out: (List<T>) -> R?): R? {
    return if (variables.all { it != null }) out (listOfNotNull(*variables)) else null
}

/**
 * Returnng random element from input variables
 * 
 * val randomLetter = randomFrom("a","b","c","d","e","f","g")
 * 
 * @property variables
 */
fun <T> randomFrom(vararg variables: T): T {
    return variables.random()
}

/**
 * Allows to use when without else block like this:
 * 
 * when(foo) {
 *    bar -> doAction()
 * }.exhaustive
 */
val <T> T.exhaustive: T
    get() = this

/**
 * JUST FOR FUN!
 * Because why not?
 * 
 * replace for stadard if (condition) { action } constructon to more beautiful:
 * 
 * condition.then {
 *     action
 * }
 * 
 * @property action - action that you want to do if condition is true
 * 
 */
fun Boolean.then(action: () -> Unit): Boolean {
    if (this) action.invoke()
    return this
}

/**
 * JUST FOR FUN!
 * Because why not?
 * 
 * replace for stadard if (!condition) { action } constructon to more beautiful:
 * 
 * condition.orNot {
 *     action
 * }
 * 
 * can be used with previous .then extension in chain like this:
 * 
 * condition.then {
 *    action
 * }.orNot {
 *    another action 
 * }
 * 
 * @property action - action that you want to do if condition is false
 * 
 */
fun Boolean.orNot(action: () -> Unit): Boolean {
    if (!this) action.invoke()
    return this
}


/**
 * Simple way to calculate approximated function execution time 
 * 
 * @property actionName - String to define action name in logcat 
 * @property action - action to measure 
 */
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


/**
 * Getting approximated Lat/Lng degrees value from meters
 */
fun Double.metersToLatLonDegrees(): Double {
    return this * 0.00001
}

/**
 * Just for better reading
 */
fun <T>Collection<T>.hasOnlySingleItem(): Boolean {
    return this.size == 1
}

/**
 * Great thing if you wand to add some item to mutable list if it is not n this list now or
 * remove this item, if list contains it in many parts of code
 * 
 * @property element - element to adding or removing
 */
fun <T>MutableList<T>.addOrRemove(element: T) {
    if (this.contains(element)) this.remove(element) else this.add(element)
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

/**
 * Like .first() but .second() =)
 */
fun <T> List<T>.second(): T? {
    return when {
        isEmpty() -> null
        size < 2 -> null
        else -> this[1]
    }
}

/**
 * Checking that all elements is equals
 * 
 * @property values - vararg of checking elements
 * 
 * allIsEqual("test", "test", "test") will return true
 */
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

/**
 * Executing out callback if all vararg values is not null
 * 
 * Like .multilet , but not returning values
 * 
 * @property values - vararg of checking elements
 * 
 * val a = "a"
 * val b = "b"
 * val c = null
 * 
 * allIsNotNull(a,b) {
 *     this action will be executed
 * }
 * 
 * allIsNotNull(a,b,c) {
 *     this action will NOT be executed
 * }
 * 
 */
fun <T, R>allIsNotNull(vararg values: T, out: () -> R?): R? {
    values.forEach {
        if (it == null) return null
    }
    return out()
}

// Check contains ignoring case
fun List<String>.containsIgnoreCase(value: String?): Boolean {
	this.forEach {
		if (it.equals(value, true)) return true
	}
	return false
}

// Replace code like "someValue != null" with simple sugar extension
fun Any?.notNull(): Boolean {
    return this != null
}
