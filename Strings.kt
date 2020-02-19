/**
 * @author Sergey Sh. (GreyLabsDev) 2019
 *
 * @param mask
 * This is your masking pattern for current string
 *
 * @param hideSymbols
 * Use this parameter if you want to replace some symbols
 * it your string with special character
 *
 * @param replacingCharacter
 * This character will be used for replacing
 *
 * Mask format rules:
 * S - symbol
 * * - symbol that you want to hide with special character or string
 * empty space - space between symbols
 *
 * You car use not full length mask,
 * if mask length lower than base string,
 * mask will format part of this string,
 * matching mask length
 *
 * Mask example "SS-SS"
 * this mask from "123456" makes "12-3456"
 *
 * Mask example "SSS-SSS"
 * this mask from "123456" makes "123-456"
 *
 * Mask example "S SS-S(SS)"
 * this mask from "123456" makes "1 23-4(56)"
 */

fun String.applyMask(mask: String, hideSymbols: Boolean = false, replacingCharacter: String = "*"): String {
    val builder = StringBuilder()
    var stringCharIndex = 0

    for (i in mask.indices) {
        when (mask[i].toString()) {
            "S" -> {
                if (stringCharIndex <= this.lastIndex) {
                    builder.append(this[stringCharIndex])
                    stringCharIndex++
                }
            }
            "*" -> {
                if (hideSymbols && stringCharIndex <= this.lastIndex) {
                    builder.append(replacingCharacter)
                    stringCharIndex++
                } else builder.append(mask[i])
            }
            else -> {
                builder.append(mask[i])
            }
        }
    }
    if (stringCharIndex < this.lastIndex) {
        builder.append(this.substring(stringCharIndex, this.length))
    }

    return builder.toString()
}

/**
 * Parsing date from string by default of custom pattern, return null if parsing failed
 * 
 * @property pattern - date pattern
 */
fun String.parseDate(pattern: String? = null): Date? {
    var parsedDate: Date? = null
    val dateFormatIso8601 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    
    pattern?.let {datePattern ->
        try {
            val dateFormatByPattern = SimpleDateFormat(datePattern, Locale.getDefault())
            parsedDate = dateFormatByPattern.parse(this)
        } catch (e: Exception) {}
    } ?: run {
        try {
            parsedDate = dateFormatIso8601.parse(this)
        } catch (e: Exception) {}    
    }
    
    return parsedDate
}

/**
 * Formatting string as price in roubles with money symbol
 */
fun String.formatAsPrice(): String {
    val formatter = NumberFormat.getInstance(Locale.FRANCE) as DecimalFormat
    val rubleSymbol = "\u20BD"
    formatter.applyPattern("#,###,###,### \u20BD")
    return "${formatter.format(this.toInt())}"
}

fun String.isValidEmail(): Boolean {
    val emailRegex = compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return emailRegex.matcher(this).matches()
}

fun Number.formatAsPrice(useRoubleSymbol: Boolean = false): String {
    return (NumberFormat.getInstance(Locale.FRANCE) as DecimalFormat).apply {
        applyPattern("#,###,###,###${if (useRoubleSymbol) " \u20BD" else ""}")
    }.format(this)
}
