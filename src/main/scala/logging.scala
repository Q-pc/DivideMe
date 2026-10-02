import java.util.logging.*

class DividedConsoleHandler extends Handler {
  private val outHandler = new ConsoleHandler() {
    setOutputStream(System.out)
    setFormatter(new DividedFormatter(false))
    setLevel(Level.INFO)
  }

  private val errHandler = new ConsoleHandler() {
    setOutputStream(System.err)
    setFormatter(new DividedFormatter(true))
    setLevel(Level.WARNING)
  }

  override def publish(record: LogRecord): Unit = {
    if (record.getLevel.intValue >= Level.WARNING.intValue) {
      errHandler.publish(record)
    } else {
      outHandler.publish(record)
    }
  }

  override def flush(): Unit = {
    outHandler.flush()
    errHandler.flush()
  }

  override def close(): Unit = {
    outHandler.close()
    errHandler.close()
  }
}

class DividedFormatter(isError: Boolean) extends Formatter {
  private val RED = "\u001B[31m"
  private val WHITE = "\u001B[37m"
  private val RESET = "\u001B[0m"

  override def format(record: LogRecord): String = {
    val color = if (isError) RED else WHITE
    val level = record.getLevel.getName
    val message = formatMessage(record)
    val parts = record.getSourceClassName.split("\\.")
    val source = parts(parts.length - 1)
    color + "[" + level + "] " + source + ": " + message + RESET + "\n"
  }
}