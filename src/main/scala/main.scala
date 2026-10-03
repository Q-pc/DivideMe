import log.ColoredLog
import models.MainPage

@main
def main(): Unit = {
  val logger = ColoredLog("Main")
  logger.info("Starting DivideMe JME application...")
  val app = new MainPage()
  app.start()
}



//class DivideMeApp extends SimpleApplication {
//  private val logger = ColoredLog(getClass)
//  private var bulletAppState: BulletAppState = uninitialized
//
//  override def simpleInitApp(): Unit = {
//    logger.info("Initializing application...")
//    viewPort.setBackgroundColor(ColorRGBA.Gray)
//    {
//      initPhysics()
//    }
//    logger.info("Application initialized successfully.")
//  }
//
//  private def initPhysics(): Unit = {
//    bulletAppState = new BulletAppState()
//    stateManager.attach(bulletAppState)
//    logger.info("Physics engine initialized.")
//  }
//
//  override def simpleUpdate(tpf: Float): Unit = {
//    super.simpleUpdate(tpf)
//  }
//}