package com.jccsisc.irepcp.ui.navigation

import com.jccsisc.irepcp.core.constants.Constants.MODIFY_TASKS_SCREEN

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.navigation
 * Created by Julio Cesar Camacho Silva on 05/12/22
 */
sealed class Screens(val route: String) {
    object ModifyTaskScreen: Screens(MODIFY_TASKS_SCREEN)

}