package io.github.usbharu.switchbot_on_pc.gui.view

import io.github.usbharu.switchbot_on_pc.gui.util.FlexLayout
import javax.swing.JPanel

class DevicesView() : JPanel() {
    init {
        val flexLayout = FlexLayout(FlexLayout.HORIZONTAL,100,100,5,5)
        layout = flexLayout
    }
}
