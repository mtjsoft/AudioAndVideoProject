package androidx.lifecycle

import android.annotation.SuppressLint
import cn.mtjsoft.www.baseres.extentions.RxLifeScope

/**
 * @author mtj
 * @date 2021/10/20
 * @desc  扩展函数
 * @email mtjsoft3@gmail.com
 *
 *  在FragmentActivity、 ViewModel环境下，使用 rxLifeScope.launch 方式开启协程，会在页面销毁时，自动关闭协程
 * (注意：这里的rxLifeScope是变量，不是类名)
 * 其它环境下，需要拿到 RxLifeScope.launch方法的返回值后，手动调用Job.cancel方法关闭协程
 *
 */
private const val JOB_KEY = "androidx.lifecycle.ViewModelRxLifeScope.JOB_KEY"

val ViewModel.rxLifeScope: RxLifeScope
    get() {
        val scope: RxLifeScope? = this.getTag(JOB_KEY)
        if (scope != null) {
            return scope
        }
        return this.setTagIfAbsent(JOB_KEY, RxLifeScope())
    }

val LifecycleOwner.rxLifeScope: RxLifeScope
    get() = lifecycle.rxLifeScope

val Lifecycle.rxLifeScope: RxLifeScope
    @SuppressLint("RestrictedApi")
    get() {
        while (true) {
            val existing = mInternalScopeRef.get() as RxLifeScope?
            if (existing != null) {
                return existing
            }
            val newScope = RxLifeScope(this)
            if (this.mInternalScopeRef.compareAndSet(null, newScope)) {
                return newScope
            }
        }
    }