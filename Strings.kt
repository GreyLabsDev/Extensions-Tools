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
        when (mask[i]) {
            'S' -> {
                if (stringCharIndex <= this.lastIndex) {
                    builder.append(this[stringCharIndex])
                    stringCharIndex++
                }
            }
            '*' -> {
                if (hideSymbols && stringCharIndex <= this.lastIndex) {
                    builder.append(replacingCharacter)
                    stringCharIndex++
                } else builder.append(mask[i])
            }
            else -> {
                if (i <= this.lastIndex) builder.append(mask[i])
            }
        }
    }
    if (stringCharIndex < this.lastIndex) {
        builder.append(this.substring(stringCharIndex, this.length))
    }

    return builder.toString()
}

/**
 * @author Sergey Sh. (GreyLabsDev) 2021
 *
 * Converting lower case string no name like string 
 * by detecting spaces in source string
 *
 * Example 
 * source - best name ever
 * result - Best Name Ever
 *
 */

fun String.maskAsName(): String {
    return if (this.isNotEmpty()) {
        val builder = StringBuilder()
        var doUpperCase = false
        this.forEachIndexed { index, char ->
            when {
                index == 0 -> builder.append(char.uppercaseChar())
                doUpperCase -> {
                    builder.append(char.uppercaseChar())
                    doUpperCase = false
                }
                index == this.lastIndex -> {
                    builder.append(char)
                    return@forEachIndexed
                }
                char == ' ' -> {
                    builder.append(char)
                    doUpperCase = true
                }
                else -> builder.append(char)
            }
        }
        return builder.toString()
    } else this
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
fun String.formatToPrice(): String {
    val formatter = NumberFormat.getInstance(Locale.FRANCE) as DecimalFormat
    val rubleSymbol = "\u20BD"
    formatter.applyPattern("#,###,###,### \u20BD")
    return "${formatter.format(this.toInt())}"
}

/**
 * Formatting string as price with optional money symbol
 */
fun Number.formatToPrice(moneySymbol: String = ""): String {
    return (NumberFormat.getInstance(Locale.FRANCE) as DecimalFormat).apply {
        decimalFormatSymbols = DecimalFormatSymbols(Locale.getDefault()).apply {
            decimalSeparator = '.'
            groupingSeparator = ' '
        }
        applyPattern("#,###,###,###.## $moneySymbol")
    }.format(this)
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

// Check if string is any type of number

fun String.isNumeric(): Boolean {
        return this.matches(Regex("-?\\d+(\\.\\d+)?"))
}

// Generate hash for string with supported algorithms

private fun String.SHA256(hexChars: String): String {
        val bytes = MessageDigest.getInstance("SHA-256")
            .digest(this.toByteArray())
        val out = StringBuilder(bytes.size * 2)
        bytes.forEach {
            val byte = it.toInt()
            out.append(hexChars[byte shr 4 and 0x0f])
            out.append(hexChars[byte and 0x0f])
        }
        return out.toString()
    }

    private fun String.SHA512(hexChars: String): String {
        val bytes = MessageDigest.getInstance("SHA-512")
            .digest(this.toByteArray())
        val out = StringBuilder(bytes.size * 2)
        bytes.forEach {
            val byte = it.toInt()
            out.append(hexChars[byte shr 4 and 0x0f])
            out.append(hexChars[byte and 0x0f])
        }
        return out.toString()
    }

    private fun String.SHA1(hexChars: String): String {
        val bytes = MessageDigest.getInstance("SHA-1")
            .digest(this.toByteArray())
        val out = StringBuilder(bytes.size * 2)
        bytes.forEach {
            val byte = it.toInt()
            out.append(hexChars[byte shr 4 and 0x0f])
            out.append(hexChars[byte and 0x0f])
        }
        return out.toString()
    }

    private fun String.MD5(hexChars: String): String {
        val bytes = MessageDigest.getInstance("MD5")
            .digest(this.toByteArray())
        val out = StringBuilder(bytes.size * 2)
        bytes.forEach {
            val byte = it.toInt()
            out.append(hexChars[byte shr 4 and 0x0f])
            out.append(hexChars[byte and 0x0f])
        }
        return out.toString()
    }
