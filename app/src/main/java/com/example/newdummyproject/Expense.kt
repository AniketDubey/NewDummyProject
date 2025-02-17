package com.example.newdummyproject

data class Expense(
    val expenseName: String,
    val expenseCategory: ExpenseCategory,
    val expenseAmount: String
)

enum class ExpenseCategory {
    FOOD,
    TRAVEL,
    ENTERTAINMENT
}
