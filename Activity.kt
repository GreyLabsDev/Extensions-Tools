/**
 * Builds alert dialog with defined title, message and callback for click on positive button
 * @property title
 * @property message
 */
fun Activity.buildAlertDialog(title: String? = null,
                              message: String? = null,
                              onPositiveBtnClick: (() -> Unit)? = null): AlertDialog {
    val alertDialog = AlertDialog.Builder(this).create()
    title?.let { alertDialog.setTitle(it) }
    message?.let { alertDialog.setMessage(it) }
    alertDialog.setMessage(message)
    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.yes)) { dialog, _ ->
        onPositiveBtnClick?.invoke()
        dialog.dismiss()
    }
    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.no)) { dialog, _ ->
        dialog.dismiss()
    }
    return alertDialog
}

/**
 * Forced show system keyboard
 */
fun Activity.showKeyboard() {
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    this.currentFocus?.let {focusedView ->
        inputMethodManager.toggleSoftInputFromWindow(focusedView.windowToken, InputMethodManager.SHOW_FORCED, 0)
    }
}

/**
 * Forced hide system keyboard
 */
fun Activity.hideKeyboard() {
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    this.currentFocus?.let {focusedView ->
            inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
    }
}

/**
 * Adding keyboard show/hide listener to current activity, returning ViewTreeObserver that should be removed from
 * listeners when activity destroyed or listener done its work
 * 
 * @property onKeyboardOpenedAction callback to make reaction on event when keyboard is opening
 * @property onKeyboardClosedAction callback to make reaction on event when keyboard is closing
 */
fun Activity.addKeyboardListener(onKeyboardOpenedAction: (() -> Unit)? = null,
                                 onKeyboardClosedAction: (() -> Unit)? = null): ViewTreeObserver.OnGlobalLayoutListener {
    val rootView = this.findViewById<View>(android.R.id.content)
    var opened = false
    val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        val rect = Rect().apply { rootView.getWindowVisibleDisplayFrame(this) }
        val screenHeight = rootView.height
        val keyboardHeight = screenHeight - rect.bottom
        if ((keyboardHeight > screenHeight * 0.15) == opened) {
            return@OnGlobalLayoutListener
        }
        opened = keyboardHeight > screenHeight * 0.15
        if (opened) {
            onKeyboardOpenedAction?.invoke()
        } else onKeyboardClosedAction?.invoke()
    }
    rootView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    return globalLayoutListener
}

/**
 * Removing ViewTreeObserver from activity, made for easier us of Activity.addKeyboardListener extension
 * @property listener - listener needs to be removed
 */
fun Activity.removeKeyboardListener(listener: ViewTreeObserver.OnGlobalLayoutListener) {
    val rootView = this.findViewById<View>(android.R.id.content)
    rootView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
}
