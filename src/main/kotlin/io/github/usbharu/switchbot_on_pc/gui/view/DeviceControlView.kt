package io.github.usbharu.switchbot_on_pc.gui.view

import io.github.usbharu.switchbot_on_pc.gui.util.FlexLayout
import javax.swing.JPanel

class DeviceControlView : JPanel(){
    init {
        layout = FlexLayout(FlexLayout.HORIZONTAL,100,100,20,20)
    }
}
