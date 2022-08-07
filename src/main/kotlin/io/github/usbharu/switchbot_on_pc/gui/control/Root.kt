package io.github.usbharu.switchbot_on_pc.gui.control

import io.github.usbharu.switchbot_on_pc.getToken
import io.github.usbharu.switchbot_on_pc.gui.view.DeviceControlView
import io.github.usbharu.switchbot_on_pc.gui.view.RootView
import java.awt.BorderLayout
import java.io.FileWriter
import java.nio.file.Paths
import java.util.*
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel

class Root {
    private val rootView: RootView = RootView()
    private val controlPanel: JPanel = JPanel(BorderLayout())

    init {
        if (getToken() ==null) {
            val fileWriter = FileWriter(Paths.get("setting.properties").toFile())
            val properties = Properties()
            properties.setProperty("token",JOptionPane.showInputDialog("Please input Token"))
            properties.store(fileWriter,"token")

        }
        rootView.addTab("Devices", Devices().getDevicesView())
        rootView.addTab("Control", controlPanel)
        rootView.addTab("Setting", Setting().getSettingView())
    }

    fun getRootView(): RootView {
        return rootView
    }

    fun setControlView(panel: DeviceControlView) {
        controlPanel.removeAll()
        controlPanel.add(panel)
        controlPanel.add(JLabel(panel.name),BorderLayout.NORTH)
        controlPanel.repaint()
        controlPanel.revalidate()
    }
}
