package io.github.usbharu.switchbot_on_pc

import com.github.weisj.darklaf.DarkLaf
import io.github.usbharu.switchbot_on_pc.gui.control.Root
import io.github.usbharu.switchbot_on_pc.util.GetResource
import java.awt.*
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.UIManager
import kotlin.system.exitProcess

private lateinit var root: Root

fun main(args: Array<String>) {
    try {


        println("Hello World!")

        UIManager.setLookAndFeel(DarkLaf())


        val jFrame = JFrame("Switch Bot on PC")
        jFrame.setSize(480, 640)
//    jFrame.contentPane = DeviceControl().getDeviceControlView()
        root = Root()
        jFrame.contentPane = root.getRootView()
        jFrame.isVisible = true


        jFrame.defaultCloseOperation = JFrame.HIDE_ON_CLOSE

        val image = ImageIO.read(GetResource().kotlinGetResourceAtStream("icon.png"))
        val icon = TrayIcon(image)
        icon.addActionListener { jFrame.isVisible = true }

        val menu = PopupMenu()
        val menuItem = MenuItem("exit")
        menuItem.addActionListener { exitProcess(0) }
        menu.add(menuItem)
        icon.popupMenu = menu


        SystemTray.getSystemTray().add(icon)

    }catch (e:Exception){
        e.printStackTrace()
    }
}

fun setContentPane(pane: Container) {
}

fun getRoot(): Root {
    return root
}

fun getToken(): String? {
    val properties = Properties()
    try {
        properties.load(
            Files.newBufferedReader(
                Paths.get("setting.properties"),
                StandardCharsets.UTF_8
            )
        )
    } catch (e: java.nio.file.NoSuchFileException) {
        return null
    }
    val property = properties.getProperty("token")
    if (property != null && !property.isBlank()) {
        return property
    }
    return null
}
