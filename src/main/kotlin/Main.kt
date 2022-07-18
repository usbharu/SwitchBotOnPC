import com.github.weisj.darklaf.DarkLaf
import io.github.usbharu.switchbot_on_pc.gui.control.Root
import java.awt.*
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.UIManager
import kotlin.system.exitProcess
import kotlin.io.NoSuchFileException as NoSuchFileException1

private lateinit var root:Root

fun main(args: Array<String>) {
    println("Hello World!")

    UIManager.setLookAndFeel(DarkLaf())


    val jFrame = JFrame("Switch Bot on PC")
    jFrame.setSize(480, 640)
//    jFrame.contentPane = DeviceControl().getDeviceControlView()
    root = Root()
    jFrame.contentPane = root.getRootView()
    jFrame.isVisible = true


    jFrame.defaultCloseOperation = JFrame.HIDE_ON_CLOSE

    val image = ImageIO.read(File("C:\\Users\\haruj\\Pictures\\QMPlay2_snap_00005.bmp"))
    val icon = TrayIcon(image)
    icon.addActionListener { jFrame.isVisible = true }

    val menu = PopupMenu()
    val menuItem = MenuItem("exit")
    menuItem.addActionListener { exitProcess(0) }
    menu.add(menuItem)
    icon.popupMenu = menu


    SystemTray.getSystemTray().add(icon)
}

fun setContentPane(pane: Container) {
}

fun getRoot(): Root {
    return root
}

fun getToken():String?{
    val properties = Properties()
    try {
        properties.load(
            Files.newBufferedReader(
                Paths.get("setting.properties"),
                StandardCharsets.UTF_8
            )
        )
    }catch (e: java.nio.file.NoSuchFileException){
        return null
    }
    val property = properties.getProperty("token")
    if (property != null && !property.isBlank()) {
        return property
    }
    return null
}
