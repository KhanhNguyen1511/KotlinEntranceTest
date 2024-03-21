package nguyen.dev.kotlinentrancetest.ui.signup

import androidx.core.content.ContextCompat
import nguyen.dev.kotlinentrancetest.R
import java.util.regex.Pattern

interface SignUpView {

    fun SignUpFragment.isDataFilled(): Boolean {
        return when {
            vb.textInputEditTextEmail.text.isNullOrBlank() -> {
                vb.textInputEditTextEmail.error = getString(R.string.field_cannot_be_null)
                return false
            }
            vb.textInputEditTextPassword.text.isNullOrBlank() -> {
                vb.textInputEditTextPassword.error = getString(R.string.field_cannot_be_null)
                return false
            }
            !vb.viewCheckTerms.isChecked -> {
                return false
            }
            else -> {true}
        }
    }

    fun SignUpFragment.validatePassword(str: String) {
        val weakState = ContextCompat.getColor(vb.root.context,R.color.track_weak)
        val fairState = ContextCompat.getColor(vb.root.context,R.color.track_fair)
        val strongState = ContextCompat.getColor(vb.root.context,R.color.track_good)
        val excellentState = ContextCompat.getColor(vb.root.context,R.color.track_excellent)
        val default = ContextCompat.getColor(vb.root.context,R.color.track_default)

        vb.strengthIndicator.let {
            when {
                str.length in 1..7 -> {
                    it.progress = 25
                    it.setIndicatorColor(weakState)
                    vb.textStrength.let {
                        it.text = getString(R.string.weak)
                        it.setTextColor(weakState)
                    }
                }
                str.length in 8..10 -> {
                    if (str.isValidLength || str.hasOneUppercase || str.hasOneLowercase || str.hasOneNumber || str.hasOneSpecialCharacter){
                        it.progress = 50
                        it.setIndicatorColor(fairState)
                        vb.textStrength.let {
                            it.text = getString(R.string.fair)
                            it.setTextColor(fairState)
                        }
                    }
                }
                str.length in 11..13 -> {
                    if (str.isValidLength || str.hasOneUppercase || str.hasOneLowercase || str.hasOneNumber || str.hasOneSpecialCharacter){
                        it.progress = 75
                        it.setIndicatorColor(strongState)
                        vb.textStrength.let {
                            it.text = getString(R.string.strong)
                            it.setTextColor(strongState)
                        }
                    }
                }
                str.length in 14..16 -> {
                    if (str.isValidLength || str.hasOneUppercase || str.hasOneLowercase || str.hasOneNumber || str.hasOneSpecialCharacter){
                        it.progress = 100
                        it.setIndicatorColor(excellentState)
                        vb.textStrength.let {
                            it.text = getString(R.string.excellent)
                            it.setTextColor(excellentState)
                        }
                    }
                }
                else -> {
                    it.progress = 0
                    it.setIndicatorColor(default)
                    vb.textStrength.let {
                        it.text = getString(R.string.too_short)
                        it.setTextColor(default)
                    }
                }
            }
        }
    }

    fun SignUpFragment.validateEmail(str: String) {
        if (str.isEmpty()) {
            vb.textInputEditTextEmail.error = getString(R.string.email_cannot_be_empty)
        } else {
            if (!isValidEmail(str)) {
                vb.textInputEditTextEmail.error = getString(R.string.email_invalid)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        )
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
}


val String?.isValidLength: Boolean
    get() {
        this ?: return false
        return this.length in 6..18
    }
val String?.hasOneUppercase: Boolean
    get() {
        this ?: return false
        return this.matches(".*[A-Z].*".toRegex())
    }

val String?.hasOneLowercase: Boolean
    get() {
        this ?: return false
        return matches(".*[a-z].*".toRegex())
    }

val String?.hasOneNumber: Boolean
    get() {
        this ?: return false
        return matches(".*\\d.*".toRegex())
    }

val String?.hasOneSpecialCharacter: Boolean
    get() {
        if (isNullOrEmpty()) return false
        return isNotContainRegex("[^a-z0-9 ]")
    }

fun String?.isNotContainRegex(regex: String): Boolean {
    this ?: return false
    return Pattern
        .compile(regex, Pattern.CASE_INSENSITIVE)
        .matcher(this)
        .find()
}