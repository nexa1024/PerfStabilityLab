# Week 1：基线与证据链 + 数组/字符串（minSdk 26）

> 本周目标：把 `PerfStabilityLab` 的“可持续扩展骨架”搭好，并形成后续每周都复用的证据链习惯（复现→证据→修复→回归）。  
> 本周交付：App 骨架 + 实验列表路由 + 统一日志/落盘 + 第一次 Trace 证据可见。

---

## 每日固定配方（90 分钟）

- 15m 算法：1 题（写边界 + 复杂度 + 使用模板）
- 45m 阅读 + 笔记：5–10 条要点 + 1 张小流程图
- 25m Demo：做出“可运行 + 可验证”的最小闭环
- 5m 输出：1 张卡片（60 秒可复述）

---

## Day 1：建工程 + Toolbar 容器 + 证据链模板

- 算法：双指针模板题 1 道（边界：空/单元素/重复值/全满足/全不满足）。
- 阅读笔记：写 1 页《证据链模板 1.0》
  - 现象（看到什么）→ 指标（量化什么）→ 证据（工具/日志/trace）→ 修复（改了什么）→ 回归（如何证明变好）
- Demo：
  - 新建工程 `PerfStabilityLab`（Kotlin + XML，minSdk 33）。
  - `activity_main.xml`：`MaterialToolbar(id=toolbar)` + `FragmentContainerView(id=main_container)`。
  - `MainActivity`：`setSupportActionBar(toolbar)`，并配置返回按钮点击 `onBackPressedDispatcher.onBackPressed()`。
- 输出卡片：《证据链四件套》一句话版（后面每次实验都用）。

验收：App 能启动，Toolbar 正常显示，容器可承载 Fragment。

---

## Day 2：实验列表（RecyclerView）+ 路由（点进 Lab Fragment）

- 算法：滑动窗口模板题 1 道（可变窗口优先，写清扩张/收缩条件）。
- 阅读笔记：写《四类问题的直觉分类》
  - 启动：首帧/可交互为何慢
  - 卡顿：主线程 >16.6ms 的来源（IO/锁/布局/GC）
  - 内存：泄漏 vs 抖动 vs OOM
  - 稳定性：Crash/ANR 的证据从哪里来
- Demo：
  - `LabId`/`LabItem`/`LabRegistry`（6 个实验：启动/卡顿/泄漏/OOM/ANR/Crash）。
  - `LabListFragment` + `LabListAdapter` + `RecyclerView` 列表。
  - `MainActivity.openLab(id)`：`replace(...).addToBackStack(id)`。
  - 监听 backStack：列表页隐藏返回箭头，实验页显示返回箭头。
- 输出卡片：《路由规则》：列表→实验页；返回→列表；标题如何设置。

验收：点任意实验项进入对应页面；返回可回列表；返回箭头随页面切换正确显示。

---

## Day 3：统一模板（复现/修复/验证）+ 屏幕日志 + 落盘记录

- 算法：前缀和模板题 1 道（边界：负数/全 0/溢出风险写清楚）。
- 阅读笔记：写《实验记录字段最小集合》
  - time、labId、action（复现/修复/验证）、detail、线程名、可选耗时
- Demo：
  - `fragment_lab_template.xml`：说明 + `复现/修复/验证` 三按钮 + 日志区（滚动 `TextView`）。
  - `LabLog`：统一 tag，输出包含线程名/时间戳。
  - `LabRecorder`：追加写入 `filesDir/perflab_records.jsonl`（或 `.txt`）。
  - 6 个 `LabFragment` 复用模板布局；按钮点击统一执行：屏幕日志追加 + Logcat 打印 + 落盘。
- 输出卡片：《一次实验最小闭环》：点按钮→屏幕有日志→文件有记录。

验收：任意实验页点按钮，三种记录都能看到（屏幕/Logcat/文件）。

---

## Day 4：Trace 打点 + 第一个“工具可见证据”（JankLab）

- 算法：数组/字符串综合 1 题（写清不变量：窗口里维持什么性质）。
- 阅读笔记：写《Trace 的目的与命名规范》
  - 目的：让“怀疑慢点”在系统 trace 里有可见 section
  - 命名：`Lab#动作#细节`，避免 `doWork` 这类无信息名字
- Demo：
  - `TraceUtils.traceSection(name) { ... }`（内部 `Trace.beginSection/endSection`）。
  - `JankLab` 的“复现”：主线程可控耗时（先用 `Thread.sleep(200)` 占位）并包在 trace section 内。
  - 同步写日志/落盘：`action=复现 detail=mainBlock200ms`。
  - “修复/验证”先做占位：修复可切换到后台线程；验证输出“如何抓 trace”的操作提示到日志区。
- 输出卡片：《我如何证明一次卡顿》：section 名 + 线程 + 时长。

验收：抓一次 System Trace/Perfetto，能搜到 `JankLab#复现#...` 的 section 且能看到持续时间。

---

## Day 5：Profiler 基础走查（CPU/Memory）+ 预埋可控动作

- 算法：复盘 Week1 前 4 天错题（只写错因：边界/下标/收缩条件/溢出）。
- 阅读笔记：写《Profiler 我会看的 3 个点》
  - CPU：主线程忙在哪（采样/调用栈）
  - Memory：分配曲线是否陡增、是否频繁 GC
  - Threads：是否长时间等待（先有直觉）
- Demo：
  - 在某个实验页增加两个可控动作：
    - `blockMainThread(200/500ms)`：为后续卡顿/ANR 做铺垫
    - `allocObjects()`：为后续 GC/分配抖动做铺垫
  - 每次点击都写日志 + 落盘。
- 输出卡片：《Profiler 操作清单》：我会怎么开、看哪里、怎么描述证据。

验收：你能用 Profiler 观察到一次明显的 CPU 峰值或 allocation 峰值，并能写下“看到的证据”。

---

## Day 6：周复盘与固化（变成可复用资产）

- 算法：补一题你最弱的模板（双指针/滑窗/前缀和三选一）。
- 阅读笔记：把 Week1 笔记压缩成 1 页《Week1 资产》
  - 工程骨架、日志/落盘、Trace 证据、Profiler 证据
- Demo：
  - 确保 6 个实验入口都可进入、三按钮可点、日志可滚动、记录可落盘。
  - `LabRegistry` 的标题/简介/tags 补齐到“以后自己看也能懂”。
- 输出卡片：《Week1 我已掌握》：3 条能力（例如会抓 trace、会复现问题、会留证据链）。

本周验收：新增一个实验页只需 3 步：加 Fragment → 注册到 `LabRegistry` → 复用模板布局并填三按钮逻辑。

