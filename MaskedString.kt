import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * MaskedString delegate
 * @author Sergey Sh. (GreyLabsDev) 2021
 *
 * Making string value with automatic masking on every value change
 * Useful in Compose TextField and other cases when you need to get
 * already masked value
 *
 * @param mask
 * This is your masking pattern for current string
 *
 * Mask format rules:
 * S - symbol
 * * - symbol that you want to hide with special character or string
 * empty space - space between symbols
 *
 * Example
 * var phone by MaskedString("+7 (SSS) SSS SS SS")
 *
 */

class MaskedString(private val mask: String): ReadWriteProperty<Any?, String>{

    private var rawString: String = ""
    private var maskedString: String = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return if (maskedString.isNotEmpty()) maskedString
        else rawString
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        rawString = value
        maskedString = rawString.applyMask(mask)
    }

    private fun String.applyMask(mask: String, hideSymbols: Boolean = false, replacingCharacter: String = "*"): String {
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

}
