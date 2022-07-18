package io.github.usbharu.switchbot_on_pc.model

data class GetDevicesListResponseBody(val deviceList:List<Device>, val infraredRemoteList: List<RemoteDevice>)

fun List<Device>.findByDeviceName(name:String): Device? {
    for (e in this) {
        if (e.deviceName == name) {
            return e
        }
    }
    return null;
}

fun List<Device>.findByDeviceId(id:String): Device?{
    for (device in this) {
        if (device.deviceId.equals(id)){
            return device
        }
    }
    return null
}

fun List<RemoteDevice>.findByDeviceName(name:String): RemoteDevice?{
    for (remoteDevice in this) {
        if (remoteDevice.deviceName == name){
            return remoteDevice
        }
    }
    return null
}

fun List<RemoteDevice>.findByDeviceId(id: String): RemoteDevice?{
    for (remoteDevice in this) {
        if (remoteDevice.deviceId == id) {
            return remoteDevice
        }
    }
    return null
}
