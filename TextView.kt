/**
 * Setting tring to TextView.text or hides this TextView if string is null
 * 
 * @property text
 */
fun TextView.setTextOrHide(text: String?) {
    text?.let {
        this.text = it
    } ?: run {
        this.visibility = View.GONE
    }
}

/**
 * Setting new text to TextView with something like fade to alpha animation
 * 
 * @property text - text to set to TextView
 * @property duration - animation final duration
 */
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

fun TextView.setClickableText(
    clickableTextFragment: String,
    useUnderline: Boolean = false,
    onclickAction: () -> Unit
) {
    text.indexOf(clickableTextFragment)
        .takeIf { it >= 0 }
        ?.let { startIndex ->
            val endIndex = startIndex + clickableTextFragment.length
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {onclickAction.invoke()}
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = useUnderline
                }
            }
            text = SpannableString(text).apply {
                setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            movementMethod = LinkMovementMethod.getInstance()
        } ?: throw Exception("Source TextView does not contain clickable text")
}

fun TextView.setStyledSpan(
    styledTextFragment: String,
    textColor: Int? = null,
    textSizeSp: Int? = null,
    isBoldText: Boolean = false
) {
    text.indexOf(styledTextFragment)
        .takeIf { it >= 0 }
        ?.let { startIndex ->
            val endIndex = startIndex + styledTextFragment.length
            text = SpannableString(text).apply {
                textSizeSp?.let { setSpan(AbsoluteSizeSpan(it.spToPx(context),false), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
                textColor?.let { setSpan(ForegroundColorSpan(it), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
                if (isBoldText) setSpan(StyleSpan(android.graphics.Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
}
