package com.nexa.perfstabilitylab

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.appbar.MaterialToolbar
import com.nexa.perfstabilitylab.ui.home.LabListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 设置ActionBar
        val toolBar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)

        // 把状态栏/刘海区域的高度加到 toolbar 的 top padding
        val initialTopPadding = toolBar.paddingTop
        ViewCompat.setOnApplyWindowInsetsListener(toolBar) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            v.updatePadding(top = initialTopPadding + topInset)
            insets
        }
        ViewCompat.requestApplyInsets(toolBar)

        // 先设置左上角返回按钮点击事件，day2再做显示隐藏逻辑
        toolBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, LabListFragment())
                .commit()
        }
    }
}