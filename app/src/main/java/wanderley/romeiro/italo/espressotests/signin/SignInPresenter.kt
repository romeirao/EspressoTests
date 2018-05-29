package wanderley.romeiro.italo.espressotests.signin

class SignInPresenter(val view: SignInView) {

    var username: String = ""
    var password: String = ""

    fun clickOnSignIn() {
        when {
            hasValidFields() -> {
                view.clearErrorMsg()
                view.goToDashboard()
            }
            else -> view.emptyFieldErrorMsg()
        }
    }

    private fun hasValidFields() = username.isNotBlank() && password.isNotBlank()
}