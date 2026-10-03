package log

import org.slf4j.{Logger, Marker}

// 神秘bug（可运行）
class ColoredLog(delegate: Logger) extends Logger {
  private val RED = "\u001B[31m"
  private val WHITE = "\u001B[37m"
  private val RESET = "\u001B[0m"

  private def out(msg: String, color: String): Unit = {
    System.out.println(color + msg + RESET)
  }

  private def err(msg: String, color: String): Unit = {
    System.err.println(color + msg + RESET)
  }

  override def getName: String = delegate.getName

  // Trace
  override def isTraceEnabled: Boolean = delegate.isTraceEnabled
  override def isTraceEnabled(marker: Marker): Boolean = delegate.isTraceEnabled(marker)
  override def trace(msg: String): Unit = delegate.trace(msg)
  override def trace(format: String, arg: AnyRef): Unit = delegate.trace(format, arg)
  override def trace(format: String, arg1: AnyRef, arg2: AnyRef): Unit = delegate.trace(format, arg1, arg2)
  def trace(format: String, arguments: Array[? <: AnyRef]): Unit = delegate.trace(format, arguments)
  override def trace(msg: String, t: Throwable): Unit = delegate.trace(msg, t)
  override def trace(marker: Marker, msg: String): Unit = delegate.trace(marker, msg)
  override def trace(marker: Marker, format: String, arg: AnyRef): Unit = delegate.trace(marker, format, arg)
  override def trace(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef): Unit = delegate.trace(marker, format, arg1, arg2)
  def trace(marker: Marker, format: String, argArray: Array[? <: AnyRef]): Unit = delegate.trace(marker, format, argArray)
  override def trace(marker: Marker, msg: String, t: Throwable): Unit = delegate.trace(marker, msg, t)

  // Debug
  override def isDebugEnabled: Boolean = delegate.isDebugEnabled
  override def isDebugEnabled(marker: Marker): Boolean = delegate.isDebugEnabled(marker)
  override def debug(msg: String): Unit = delegate.debug(msg)
  override def debug(format: String, arg: AnyRef): Unit = delegate.debug(format, arg)
  override def debug(format: String, arg1: AnyRef, arg2: AnyRef): Unit = delegate.debug(format, arg1, arg2)
  def debug(format: String, arguments: Array[? <: AnyRef]): Unit = delegate.debug(format, arguments)
  override def debug(msg: String, t: Throwable): Unit = delegate.debug(msg, t)
  override def debug(marker: Marker, msg: String): Unit = delegate.debug(marker, msg)
  override def debug(marker: Marker, format: String, arg: AnyRef): Unit = delegate.debug(marker, format, arg)
  override def debug(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef): Unit = delegate.debug(marker, format, arg1, arg2)
  def debug(marker: Marker, format: String, argArray: Array[? <: AnyRef]): Unit = delegate.debug(marker, format, argArray)
  override def debug(marker: Marker, msg: String, t: Throwable): Unit = delegate.debug(marker, msg, t)

  // Info
  override def isInfoEnabled: Boolean = delegate.isInfoEnabled
  override def isInfoEnabled(marker: Marker): Boolean = delegate.isInfoEnabled(marker)
  override def info(msg: String): Unit = out("[INFO] " + getName.split("\\.").last + ": " + msg, WHITE)
  override def info(format: String, arg: AnyRef): Unit = info(String.format(format, arg))
  override def info(format: String, arg1: AnyRef, arg2: AnyRef): Unit = info(String.format(format, arg1, arg2))
  def info(format: String, arguments: Array[? <: AnyRef]): Unit = info(String.format(format, arguments*))
  override def info(msg: String, t: Throwable): Unit = { info(msg); t.printStackTrace(System.out) }
  override def info(marker: Marker, msg: String): Unit = info(msg)
  override def info(marker: Marker, format: String, arg: AnyRef): Unit = info(format, arg)
  override def info(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef): Unit = info(format, arg1, arg2)
  def info(marker: Marker, format: String, argArray: Array[? <: AnyRef]): Unit = info(format, argArray)
  override def info(marker: Marker, msg: String, t: Throwable): Unit = info(msg, t)

  // Warn
  override def isWarnEnabled: Boolean = delegate.isWarnEnabled
  override def isWarnEnabled(marker: Marker): Boolean = delegate.isWarnEnabled(marker)
  override def warn(msg: String): Unit = err("[WARN] " + getName.split("\\.").last + ": " + msg, RED)
  override def warn(format: String, arg: AnyRef): Unit = warn(String.format(format, arg))
  override def warn(format: String, arg1: AnyRef, arg2: AnyRef): Unit = warn(String.format(format, arg1, arg2))
  def warn(format: String, arguments: Array[? <: AnyRef]): Unit = warn(String.format(format, arguments*))
  override def warn(msg: String, t: Throwable): Unit = { warn(msg); t.printStackTrace(System.err) }
  override def warn(marker: Marker, msg: String): Unit = warn(msg)
  override def warn(marker: Marker, format: String, arg: AnyRef): Unit = warn(format, arg)
  override def warn(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef): Unit = warn(format, arg1, arg2)
  def warn(marker: Marker, format: String, argArray: Array[? <: AnyRef]): Unit = warn(format, argArray)
  override def warn(marker: Marker, msg: String, t: Throwable): Unit = warn(msg, t)

  // Error
  override def isErrorEnabled: Boolean = delegate.isErrorEnabled
  override def isErrorEnabled(marker: Marker): Boolean = delegate.isErrorEnabled(marker)
  override def error(msg: String): Unit = err("[ERROR] " + getName.split("\\.").last + ": " + msg, RED)
  override def error(format: String, arg: AnyRef): Unit = error(String.format(format, arg))
  override def error(format: String, arg1: AnyRef, arg2: AnyRef): Unit = error(String.format(format, arg1, arg2))
  def error(format: String, arguments: Array[? <: AnyRef]): Unit = error(String.format(format, arguments*))
  override def error(msg: String, t: Throwable): Unit = { error(msg); t.printStackTrace(System.err) }
  override def error(marker: Marker, msg: String): Unit = error(msg)
  override def error(marker: Marker, format: String, arg: AnyRef): Unit = error(format, arg)
  override def error(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef): Unit = error(format, arg1, arg2)
  def error(marker: Marker, format: String, argArray: Array[? <: AnyRef]): Unit = error(format, argArray)
  override def error(marker: Marker, msg: String, t: Throwable): Unit = error(msg, t)
}

object ColoredLog {
  def apply(clazz: Class[?]): Logger = {
    new ColoredLog(org.slf4j.LoggerFactory.getLogger(clazz))
  }

  def apply(name: String): Logger = {
    new ColoredLog(org.slf4j.LoggerFactory.getLogger(name))
  }
}