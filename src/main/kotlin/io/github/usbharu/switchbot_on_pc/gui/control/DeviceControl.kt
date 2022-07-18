package io.github.usbharu.switchbot_on_pc.gui.control

import io.github.usbharu.switchbot_on_pc.gui.view.DeviceControlView
import io.github.usbharu.switchbot_on_pc.model.*
import javax.swing.JButton

class DeviceControl(device: IDevice) {
    private var device: Device? =null
    private var remoteDevice: RemoteDevice? = null
    private val deviceControlView:DeviceControlView = DeviceControlView()
    init {
        if (device is Device) {
            this.device = device
            for (deviceCommand in device.deviceType.deviceCommand) {
                val jButton = JButton(deviceCommand.command)
                jButton.addActionListener { device.commands(deviceCommand) }
                deviceControlView.add(jButton)
            }
            deviceControlView.name=device.deviceName
        }else if (device is RemoteDevice){
            this.remoteDevice = device
            for (remoteDeviceCommand in device.remoteType.remoteDeviceCommand) {
                val jButton = JButton(remoteDeviceCommand.command)
                jButton.addActionListener { device.commands(remoteDeviceCommand) }
                deviceControlView.add(jButton)
            }
            deviceControlView.name=device.deviceName
        }
    }

    fun getDeviceControlView():DeviceControlView{
        return deviceControlView
    }

    fun getDeviceName():String{
        return device?.deviceName ?: ""
    }
}
