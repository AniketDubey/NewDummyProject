package com.example.newdummyproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newdummyproject.databinding.ActivityAddExpenseBinding

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExpenseBinding
    private fun setUpExpenseCategory(): ExpenseCategory {
        if (binding.travelRadio.isChecked)
            return ExpenseCategory.TRAVEL
        else if (binding.foodRadio.isChecked)
            return ExpenseCategory.FOOD
        else if (binding.entertainmentRadio.isChecked)
            return ExpenseCategory.ENTERTAINMENT
        return ExpenseCategory.ENTERTAINMENT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.AddBtn.setOnClickListener {
            if (checkForValidation(binding.addExpenseName.text.toString()) &&
                checkForValidation(binding.addExpenseAmount.text.toString())
            ) {
                val intent = Intent()
                intent.putExtra("expenseName", binding.addExpenseName.text.toString())
                intent.putExtra("expenseAmount", binding.addExpenseAmount.text.toString())
                intent.putExtra("expenseCat", setUpExpenseCategory())
                setResult(RESULT_OK,intent)
                finish()
            }
        }
    }

    private fun checkForValidation(editText: String): Boolean {
        return !editText.isNullOrEmpty()
    }
}