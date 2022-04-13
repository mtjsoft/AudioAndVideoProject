package cn.mtjsoft.www.baseres.extentions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.mtjsoft.www.baseres.view.RecyclerViewItemDecoration

val RecyclerView.gridLayoutManager: GridLayoutManager?
    get() = layoutManager as? GridLayoutManager

val RecyclerView.linearLayoutManager: LinearLayoutManager?
    get() = layoutManager as? LinearLayoutManager

fun RecyclerView.setItemDecorationSpacing(spacingPx: Int) {
    addItemDecoration(
        RecyclerViewItemDecoration(spacingPx)
    )
}