package org.nunocky.recyclerviewstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.nunocky.recyclerviewstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var listAdapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        listAdapter = MyListAdapter(this)
        binding.recyclerView.adapter = listAdapter
    }

    override fun onResume() {
        super.onResume()

        listAdapter.submitList(
            listOf(
                item1,
                item2,
                item3,
                item4,
                item5,
            )
        )

        item1.text.observe(this) {
            binding.text = it
        }

        item2.checked.observe(this) {
            binding.checked = if (it) "true" else "false"
        }
    }

    private val item1 = TextItem(id = 0, title = "テキスト入力")
    private val item2 = BooleanItem(id = 1, title = "チェックボックス")
    private val item3 = TextItem(id = 0, title = "テキスト入力2")
    private val item4 = BooleanItem(id = 1, title = "チェックボックス2")
    private val item5 = BooleanItem(id = 1, title = "チェックボックス3")
}