package com.jccsisc.irepcp.ui.navigation

import com.jccsisc.irepcp.core.constants.Constants
import com.jccsisc.irepcp.core.constants.Constants.MODIFY_TASKS_SCREEN
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreensDashboard
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.model.DrawerItem

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.navigation
 * Created by Julio Cesar Camacho Silva on 05/12/22
 */
sealed class Screens(val route: String) {

    /**
     * Task module
     * */
    object ModifyTaskScreen: Screens(MODIFY_TASKS_SCREEN)
    object AddTaskScreen : Screens(Constants.ADD_TASK_SCREEN)

}