package com.example.newdummyproject

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newdummyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addExpenseBtn.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivityForResult(intent, 1001)
        }

        binding.expenseListRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ExpenseAdapter(getExpensesList())
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
        if (requestCode == 1001) {

            var initialList = getExpensesList()
            initialList.add(
                Expense(
                    data?.getStringExtra("expenseName")!!,
                    setUpExpense(data.getStringExtra("expenseCat")!!),
                    data.getStringExtra("expenseAmount")!!
                )
            )
        }
    }

    private fun getExpensesList(): ArrayList<Expense> {
        return arrayListOf(
            Expense("Movie", ExpenseCategory.ENTERTAINMENT, "100"),
            Expense("CCD", ExpenseCategory.FOOD, "100"),
            Expense("Trip", ExpenseCategory.TRAVEL, "100"),
            Expense("Office", ExpenseCategory.TRAVEL, "200"),
            Expense("Movie", ExpenseCategory.ENTERTAINMENT, "100"),
        )
    }

    private fun setUpCategoryName(expenseCategory: ExpenseCategory): String {
        return when (expenseCategory) {
            ExpenseCategory.FOOD -> "Food"
            ExpenseCategory.TRAVEL -> "Travel"
            ExpenseCategory.ENTERTAINMENT -> "Entertainment"
        }
    }

    private fun setUpExpense(textString: String): ExpenseCategory {
        return when (textString) {
            "Food" -> ExpenseCategory.FOOD
            "Travel" -> ExpenseCategory.TRAVEL
            "Entertainment" -> ExpenseCategory.ENTERTAINMENT
            else -> ExpenseCategory.ENTERTAINMENT
        }
    }
}