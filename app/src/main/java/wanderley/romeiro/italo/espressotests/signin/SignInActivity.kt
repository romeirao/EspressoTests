package wanderley.romeiro.italo.espressotests.signin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_sign_in.errorMsgTv
import kotlinx.android.synthetic.main.activity_sign_in.passwordEt
import kotlinx.android.synthetic.main.activity_sign_in.signInBt
import kotlinx.android.synthetic.main.activity_sign_in.usernameEt
import wanderley.romeiro.italo.espressotests.R
import wanderley.romeiro.italo.espressotests.dashboard.DashboardActivity
import wanderley.romeiro.italo.espressotests.util.hideKeyboard

class SignInActivity: AppCompatActivity(), SignInView {

    companion object {
        private const val USERNAME = "SignInActivity.USERNAME"
        private const val PASSWORD = "SignInActivity.PASSWORD"
    }

    private val presenter by lazy { SignInPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initPresenter(savedInstanceState)
        initView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.apply {
            putString(USERNAME, presenter.username)
            putString(PASSWORD, presenter.password)
        }
    }

    private fun initView() {
        configSignInBt()
        configUsernameEt()
        configPasswordEt()
    }

    private fun configSignInBt() {
        signInBt.setOnClickListener {
            presenter.clickOnSignIn()
        }
    }

    private fun configUsernameEt() {
        usernameEt.setText(presenter.username)
        usernameEt.addTextChangedListener(getTextWatcher {
            presenter.username = usernameEt.text.toString()
        })
    }

    private fun configPasswordEt() {
        passwordEt.setText(presenter.password)
        passwordEt.addTextChangedListener(getTextWatcher {
            presenter.password = passwordEt.text.toString()
        })
        passwordEt.setOnEditorActionListener { _, _, _ ->
            hideKeyboard(passwordEt)
            presenter.clickOnSignIn()
            true
        }
    }

    private fun initPresenter(savedInstanceState: Bundle?) {
        savedInstanceState?.apply {
            presenter.username = getString(USERNAME)
            presenter.password = getString(PASSWORD)
        }
    }

    override fun emptyFieldErrorMsg() {
        errorMsgTv.text = getString(R.string.emptyFieldsMsg)
    }

    override fun clearErrorMsg() {
        errorMsgTv.text = ""
    }

    override fun goToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    private fun getTextWatcher(func: () -> Unit) =
        object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                func()
            }
        }
}
