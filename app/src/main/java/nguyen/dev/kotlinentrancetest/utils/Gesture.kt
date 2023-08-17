package nguyen.dev.kotlinentrancetest.utils

import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var lastClickViewId: Int = 0

private var lastEventId: Int = -2

private var lastClickTime: Long = System.currentTimeMillis()

abstract class ViewClickListener(
    private val delayedInterval: Long = 300,
    private val eventId: Int = 1
) :
    View.OnClickListener {

    @Volatile
    var onTrigger: Boolean = false

    private val isDelayed: Boolean get() = System.currentTimeMillis() - lastClickTime > delayedInterval

    private val View.isAcceptClick: Boolean get() = id != lastClickViewId

    abstract fun onClicks(v: View)

    final override fun onClick(v: View?) {
        v ?: return
        if (eventId > 0 && eventId == lastEventId) return
        if (onTrigger) return
        if (v.isAcceptClick || isDelayed) {
            onTrigger = true
            lastClickViewId = v.id
            lastClickTime = System.currentTimeMillis()
            lastEventId = eventId
            onClicks(v)
            GlobalScope.launch {
                delay(delayedInterval)
                lastEventId = -2
                onTrigger = false
            }
        }
    }

}



