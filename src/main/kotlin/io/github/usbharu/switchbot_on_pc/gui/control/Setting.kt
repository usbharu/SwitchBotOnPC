package io.github.usbharu.switchbot_on_pc.gui.control

import io.github.usbharu.switchbot_on_pc.gui.view.SettingView
import java.io.FileWriter
import java.nio.file.Paths
import java.util.*
import javax.swing.JOptionPane

class Setting {
    private val settingView:SettingView = SettingView()
    init {
        settingView.button.addActionListener {
            val fileWriter = FileWriter(Paths.get("setting.properties").toFile())
            val properties = Properties()
            val token = JOptionPane.showInputDialog("Please input Token")
            if (token != null){
                properties.setProperty("token",token)
                properties.store(fileWriter,"token")
            }
        }
    }

    fun getSettingView():SettingView{
        return settingView
    }
}
