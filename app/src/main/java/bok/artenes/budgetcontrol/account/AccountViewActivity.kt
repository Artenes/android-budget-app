package bok.artenes.budgetcontrol.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import bok.artenes.budgetcontrol.R

class AccountViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val id = intent?.extras?.getString(EXTRA_ID)
        val factory = FragmentViewFactory(id)
        supportFragmentManager.fragmentFactory = factory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_view)
    }

    companion object {

        private const val EXTRA_ID = "ID"

        fun start(context: Context, id: String? = null) {
            val intent = Intent(context, AccountViewActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context.startActivity(intent)
        }

    }

    class FragmentViewFactory(val id: String?) : FragmentFactory() {

        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            val clazz = loadFragmentClass(classLoader, className)
            return if (clazz == AccountViewFragment::class.java) {
                AccountViewFragment(id)
            } else {
                super.instantiate(classLoader, className)
            }
        }

    }

}