fun Activity.buildAlertDialog(title: String? = null,
                              message: String? = null,
                              onPositiveBtnClick: (() -> Unit)? = null): AlertDialog {
    val alertDialog = AlertDialog.Builder(this).create()
    title?.let { alertDialog.setTitle(it) }
    message?.let { alertDialog.setMessage(it) }
    alertDialog.setMessage(message)
    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.yes)) { dialog, _ ->
        onPositiveBtnClick?.let {
            it()
        }
        dialog.dismiss()
    }
    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.no)) { dialog, _ ->
        dialog.dismiss()
    }
    return alertDialog
}