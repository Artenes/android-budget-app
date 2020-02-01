package bok.artenes.budgetcontrol

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import bok.artenes.budgetcontrol.account.AccountListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Repository.initDatabase(applicationContext)
        setContentView(R.layout.activity_main)
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, BudgetCreatorActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuAccounts -> {
                startActivity(Intent(this, AccountListActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
