package wanderley.romeiro.italo.espressotests.signin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import wanderley.romeiro.italo.espressotests.R
import wanderley.romeiro.italo.espressotests.core.di.Injector
import wanderley.romeiro.italo.espressotests.dashboard.DashboardActivity
import wanderley.romeiro.italo.espressotests.databinding.ActivitySignInBinding
import wanderley.romeiro.italo.espressotests.signin.di.DaggerSignInComponent
import wanderley.romeiro.italo.espressotests.signin.di.SignInModule
import wanderley.romeiro.italo.espressotests.util.hideKeyboard
import javax.inject.Inject

class SignInActivity : AppCompatActivity(), SignInView {

    companion object {
        private const val USERNAME = "SignInActivity.USERNAME"
        private const val PASSWORD = "SignInActivity.PASSWORD"
    }

    @Inject
    lateinit var presenter: SignInPresenter

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        try {
            doInjection()
            initPresenter(savedInstanceState)
            initView()
        } catch (e: Exception) {
            Log.e("SignInActivity", "Initialization failed", e)
            // Activity will display but without full functionality
        }
    }

    private fun doInjection() {
        try {
            DaggerSignInComponent.builder()
                .applicationComponent(Injector.get())
                .signInModule(SignInModule(this))
                .build().inject(this)
        } catch (e: Exception) {
            // For test mode, injection might fail
            e.printStackTrace()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
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
        binding.signInBt.setOnClickListener {
            presenter.clickOnSignIn()
        }
    }

    private fun configUsernameEt() {
        binding.usernameEt.setText(presenter.username)
        binding.usernameEt.addTextChangedListener(getTextWatcher {
            presenter.username = binding.usernameEt.text.toString()
        })
    }

    private fun configPasswordEt() {
        binding.passwordEt.setText(presenter.password)
        binding.passwordEt.addTextChangedListener(getTextWatcher {
            presenter.password = binding.passwordEt.text.toString()
        })
        binding.passwordEt.setOnEditorActionListener { _, _, _ ->
            hideKeyboard(binding.passwordEt)
            presenter.clickOnSignIn()
            true
        }
    }

    private fun initPresenter(savedInstanceState: Bundle?) {
        savedInstanceState?.apply {
            presenter.username = getString(USERNAME, "")
            presenter.password = getString(PASSWORD, "")
        }
    }

    override fun emptyFieldErrorMsg() {
        binding.errorMsgTv.text = getString(R.string.emptyFieldsMsg)
    }

    override fun clearErrorMsg() {
        binding.errorMsgTv.text = ""
    }

    override fun goToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    private fun getTextWatcher(func: () -> Unit) =
        object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                func()
            }
        }
}
