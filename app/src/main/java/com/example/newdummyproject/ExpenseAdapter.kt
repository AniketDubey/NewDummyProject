package com.example.newdummyproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(val expenseList: List<Expense>) :
    RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {

    var listToShow: List<Expense> = expenseList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.expenseNameTxt.text = expenseList[position].expenseName
        holder.expenseCategoryName.text = setUpCategoryName(expenseList[position].expenseCategory)
        holder.expenseAmount.text = expenseList[position].expenseAmount.toString()
    }

    fun setUpNewList(passedList: List<Expense>) {
        this.listToShow = passedList
        notifyDataSetChanged()
    }

    private fun setUpCategoryName(expenseCategory: ExpenseCategory): String {
        return when (expenseCategory) {
            ExpenseCategory.FOOD -> "Food"
            ExpenseCategory.TRAVEL -> "Travel"
            ExpenseCategory.ENTERTAINMENT -> "Entertainment"
        }
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expenseNameTxt = itemView.findViewById<TextView>(R.id.expenseName)
        val expenseCategoryName = itemView.findViewById<TextView>(R.id.expenseCategoryName)
        val expenseAmount = itemView.findViewById<TextView>(R.id.expenseAmount)
    }
}