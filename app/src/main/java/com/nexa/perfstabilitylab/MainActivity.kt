package com.nexa.perfstabilitylab

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.appbar.MaterialToolbar
import com.nexa.perfstabilitylab.core.LabId
import com.nexa.perfstabilitylab.ui.home.LabListFragment
import com.nexa.perfstabilitylab.ui.lab.*

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 设置ActionBar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 把状态栏/刘海区域的高度加到 toolbar 的 top padding
        val initialTopPadding = toolbar.paddingTop
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            v.updatePadding(top = initialTopPadding + topInset)
            insets
        }
        ViewCompat.requestApplyInsets(toolbar)

        // 设置左上角返回按钮点击事件
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 监听 backStack 变化，控制返回箭头显示/隐藏
        supportFragmentManager.addOnBackStackChangedListener {
            updateNavigationIcon()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, LabListFragment())
                .commit()
        }

        // 初始化返回箭头状态
        updateNavigationIcon()
    }

    /**
     * 打开指定实验页面
     */
    fun openLab(labId: LabId) {
        val fragment = when (labId) {
            LabId.STARTUP -> StartupLabFragment()
            LabId.JANK -> JankLabFragment()
            LabId.LEAK -> LeakLabFragment()
            LabId.OOM -> OomLabFragment()
            LabId.ANR -> AnrLabFragment()
            LabId.CRASH -> CrashLabFragment()
        }

        val labItem = com.nexa.perfstabilitylab.core.LabRegistry.getLabById(labId)
        supportActionBar?.title = labItem?.title ?: "实验"

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(labId.id)
            .commit()
    }

    /**
     * 更新返回箭头显示状态
     * - 列表页：隐藏返回箭头
     * - 实验页：显示返回箭头
     */
    private fun updateNavigationIcon() {
        val hasBackStack = supportFragmentManager.backStackEntryCount > 0
        if (hasBackStack) {
            toolbar.setNavigationIcon(com.google.android.material.R.drawable.abc_ic_ab_back_material)
        } else {
            toolbar.navigationIcon = null
            supportActionBar?.title = "PerfStabilityLab"
        }
    }
}