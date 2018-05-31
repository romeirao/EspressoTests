package wanderley.romeiro.italo.espressotests.signin

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.calls
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignInPresenterTest {

    @Mock
    private lateinit var view: SignInView
    private val presenter by lazy { SignInPresenter(view) }

    @Test
    fun signIn_emptyFields() {
        val userData = arrayOf("abc", "abc")

        for (i in 0..userData.size) {
            presenter.username = if (i - 1 == 0) userData[0] else ""
            presenter.password = if (i - 1 == 1) userData[1] else ""
            presenter.clickOnSignIn()
        }

        inOrder(view).verify(view, calls(userData.size + 1)).emptyFieldErrorMsg()
        verify(view, never()).clearErrorMsg()
        verify(view, never()).goToDashboard()
    }

    @Test
    fun signIn_filledFields() {
        presenter.username = "abc"
        presenter.password = "abc"
        presenter.clickOnSignIn()

        verify(view, never()).emptyFieldErrorMsg()
        with(inOrder(view)) {
            verify(view, calls(1)).clearErrorMsg()
            verify(view, calls(1)).goToDashboard()
        }
    }
}