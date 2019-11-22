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

fun TextView.setClickableText(clickableTextFragment: String, onclickAction: () -> Unit) {
    val spannableString = SpannableString(this.text.toString())
    if (spannableString.contains(clickableTextFragment)) {
        val startIndex = spannableString.indexOf(clickableTextFragment)
        val endIndex = startIndex + clickableTextFragment.length
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {onclickAction.invoke()}
        }
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.text = spannableString
        this.movementMethod = LinkMovementMethod.getInstance()
        this.highlightColor = Color.TRANSPARENT
    } else {
        throw Exception("Source TextView does not contain clickable text")
    }
}
