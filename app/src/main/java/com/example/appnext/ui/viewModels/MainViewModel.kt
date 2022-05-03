package com.example.appnext.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnext.extensions.getMonthName
import com.example.appnext.models.ui.DailyInfoItem
import com.example.appnext.models.ui.Item
import com.example.appnext.models.ui.MonthItem
import com.example.appnext.useCases.WeeklyActivityInfoUseCase
import kotlinx.coroutines.flow.*

class MainViewModel(
    private val weeklyActivityInfoUseCase: WeeklyActivityInfoUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(State())
    val state: StateFlow<State>
        get() = _state

    init {
        weeklyActivityInfoUseCase.observeWeeklyActivity()
            .onEach {
                val list = mutableListOf<Item>()
                val groupByMont = it.groupBy { it.month }
                groupByMont.forEach { month, actions ->
                    list.add(MonthItem(month = getMonthName(month)))
                    actions.forEach {
                        val item = DailyInfoItem(
                            id = it.id,
                            dayOfWeek = it.dayOfWeek,
                            dayOfMonth = it.dayOfMonth,
                            goal = it.goal,
                            activity = it.activity,
                            meters = it.meters,
                            kCal = it.kCal,
                        )
                        list.add(item)
                    }
                }
                _state.value = _state.value.copy(detailsItems = list)
            }
            .launchIn(viewModelScope)
    }
}


data class State(
    val detailsItems: List<Item> = listOf()
) {
    fun getFilteredItem():List<DailyInfoItem> = detailsItems.filterIsInstance(DailyInfoItem::class.java)
}