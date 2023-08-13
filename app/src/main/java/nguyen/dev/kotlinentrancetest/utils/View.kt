package nguyen.dev.kotlinentrancetest.utils

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView

fun TextView.setHyperText(s: String?, vararg args: Any?) {
    post {
        text = when {
            s.isNullOrEmpty() -> null
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(s.format(*args), 1)
            else -> @Suppress("DEPRECATION")
            Html.fromHtml(s.format(*args))
        }
    }
}