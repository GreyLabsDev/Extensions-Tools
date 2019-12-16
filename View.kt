/**
 * Cheching view visibility
 */
fun View.isVisible(): Boolean = this.visibility == View.VISIBLE

/**
 * Animating fade in for view, can execure callback after animation is ended
 * 
 * @property duration - animation duration
 * @property onAnimationEndAction - callback for executiong after animation end
 */
fun View.animateFadeIn(duration: Long? = null, onAnimationEndAction: (() -> Unit)? = null) {
    this.animate()
            .alpha(1f)
            .setDuration(duration ?: 180L)
            .withEndAction { onAnimationEndAction?.invoke() }
            .start()
}

/**
 * Animating fade out for view, can execure callback after animation is ended
 * 
 * @property duration - animation duration
 * @property onAnimationEndAction - callback for executiong after animation end
 */
fun View.animateFadeOut(duration: Long? = null,  onAnimationEndAction: (() -> Unit)? = null) {
    this.animate()
            .alpha(0f)
            .setDuration(duration ?: 180L)
            .withEndAction { onAnimationEndAction?.invoke() }
            .start()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Measuring TextView width. Use this extension after view rendered
 */
fun TextView.measureWidth(): Int {
    val bound = Rect()
    this.paint.getTextBounds(this.text.toString(), 0, this.text.toString().length, bound)
    return bound.width()
}

/**
 * Measuring TextView height. Use this extension after view rendered
 */
fun TextView.measureHeight(): Int {
    val bound = Rect()
    this.paint.getTextBounds(this.text.toString(), 0, this.text.toString().length, bound)
    return bound.height()
}

/**
 * Reducing repeting code for inflating views 
 * 
 * @property resId - inflating layout resource id
 * @property attach
 */
fun ViewGroup.inflate(resId: Int, attach: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resId, this, attach)
}

fun Animation.setAnimationListener(
    onAnimationRepeat: (() -> Unit)? = null,
    onAnimationEnd: (() -> Unit)? = null,
    onAnimationStart: (() -> Unit)? = null
) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) {
            onAnimationRepeat?.invoke()
        }

        override fun onAnimationEnd(p0: Animation?) {
            onAnimationEnd?.invoke()
        }

        override fun onAnimationStart(p0: Animation?) {
            onAnimationStart?.invoke()
        }
    })
}

private fun EditText.listenChanges(
        afterChangedListener: ((text: String) -> Unit)? = null,
        beforeChangedListener: ((text: String,p1: Int, p2: Int, p3: Int) -> Unit)? = null,
        textChangedListener: ((text: String, p1: Int, p2: Int, p3: Int) -> Unit)? = null
    ) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterChangedListener?.invoke(editable.toString())
            }
            override fun beforeTextChanged(chars: CharSequence?, p1: Int, p2: Int, p3: Int) {
                beforeChangedListener?.invoke(chars.toString(), p1, p2, p3)
            }
            override fun onTextChanged(chars: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textChangedListener?.invoke(chars.toString(), p1, p2, p3)
            }
        })
    }
