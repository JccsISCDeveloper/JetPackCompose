package com.jccsisc.irepcp.ui.activities.home.navigation

import com.jccsisc.irepcp.core.constants.Constants.ADD_OR_MODIFY_TASK_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.BOOKS_ADD_SCREEN
import com.jccsisc.irepcp.core.constants.Constants.DETAIL_MASCOTA_SCREEN

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.navigation
 * Created by Julio Cesar Camacho Silva on 05/12/22
 */
sealed class Screens(val route: String) {

    /**
     * Task module
     * */
    object AddOrModifyTaskScreen : Screens(ADD_OR_MODIFY_TASK_SCREEN)


    /**
     * Books module
     * */
    object DetailBookScreen : Screens(DETAIL_MASCOTA_SCREEN)
    object BookCameraScreen : Screens(BOOKS_ADD_SCREEN)

}