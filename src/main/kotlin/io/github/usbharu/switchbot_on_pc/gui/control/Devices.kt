package io.github.usbharu.switchbot_on_pc.gui.control

import io.github.usbharu.switchbot_on_pc.getRoot
import io.github.usbharu.switchbot_on_pc.getToken
import io.github.usbharu.switchbot_on_pc.api.SwitchBot
import io.github.usbharu.switchbot_on_pc.gui.view.DevicesView
import javax.swing.JButton

class Devices {
    private val devicesView: DevicesView = DevicesView()

    private val switchBot: SwitchBot = SwitchBot.create(getToken()!!, false)

    init {
        var height = 0
        var widthOffset = 0
        for ((index, device) in switchBot.devices().deviceList.withIndex()) {
            if (index >= 3) {
                height++
                widthOffset += 3
            }
            val jButton = JButton(device.deviceName)
            jButton.addActionListener {
                getRoot().setControlView(DeviceControl(device).getDeviceControlView())
                getRoot().getRootView().selectedIndex = 1
            }
            devicesView.add("${index - widthOffset},$height,x,x", jButton)
        }
        widthOffset = 0
        height++
        for ((index, remoteDevice) in switchBot.devices().infraredRemoteList.withIndex()) {
            if (index >= 3) {
                height++
                widthOffset += 3
            }
            val jButton = JButton(remoteDevice.deviceName)
            jButton.addActionListener {
                getRoot().setControlView(DeviceControl(remoteDevice).getDeviceControlView())
                getRoot().getRootView().selectedIndex = 1
            }
            devicesView.add("${index - widthOffset},$height,x,x", jButton)
        }
    }

    fun getDevicesView(): DevicesView {
        return devicesView
    }



}
