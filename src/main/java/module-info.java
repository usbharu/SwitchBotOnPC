module SwitchBotOnPc {

  requires java.desktop;
  requires darklaf.core;
  requires kotlin.stdlib;
  requires com.fasterxml.jackson.kotlin;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  exports io.github.usbharu.switchbot_on_pc;
  exports io.github.usbharu.switchbot_on_pc.model;
  opens io.github.usbharu.switchbot_on_pc.model;
}
