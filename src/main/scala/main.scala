import com.jme3.app.SimpleApplication
import com.jme3.bullet.BulletAppState
import com.jme3.bullet.collision.shapes.BoxCollisionShape
import com.jme3.bullet.control.RigidBodyControl
import com.jme3.material.Material
import com.jme3.math.{ColorRGBA, Vector3f}
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import com.jme3.terrain.geomipmap.TerrainQuad

import java.util.logging.LogManager

@main
def main(): Unit = {
  val logger = ColoredLog(App.getClass)
  val stream = App.getClass.getResourceAsStream("/logging.properties")
  if (stream != null) {
    LogManager.getLogManager.readConfiguration(stream)
    stream.close()
  }

  logger.info("Starting DivideMe JME application...")
  val app = new DivideMeApp()
  app.start()
}

object App {
  private val logger = ColoredLog(getClass)
}

class DivideMeApp extends SimpleApplication {
  private val logger = ColoredLog(getClass)
  private var bulletAppState: BulletAppState = null

  override def simpleInitApp(): Unit = {
    logger.info("Initializing application...")
    viewPort.setBackgroundColor(ColorRGBA.Blue)

    initPhysics()
    initTerrain()
    initBox()

    logger.info("Application initialized successfully.")
  }

  private def initPhysics(): Unit = {
    bulletAppState = new BulletAppState()
    stateManager.attach(bulletAppState)
    logger.info("Physics engine initialized.")
  }

  private def initTerrain(): Unit = {
    val matTerrain = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md")
    matTerrain.setBoolean("UseMaterialColors", true)
    matTerrain.setColor("Diffuse", ColorRGBA.Green)
    matTerrain.setColor("Ambient", ColorRGBA.DarkGray)

    val heightmap = generateHeightMap(65)

    val terrain = new TerrainQuad("terrain", 65, 65, heightmap)
    terrain.setMaterial(matTerrain)
    terrain.setLocalTranslation(0f, -10f, 0f)
    terrain.setLocalScale(4f, 1f, 4f)
    rootNode.attachChild(terrain)

    val terrainShape = new com.jme3.bullet.collision.shapes.HeightfieldCollisionShape(
      heightmap, terrain.getLocalScale
    )
    val terrainControl = new RigidBodyControl(terrainShape, 0f)
    terrain.addControl(terrainControl)
    bulletAppState.getPhysicsSpace.add(terrainControl)

    logger.info("Terrain initialized.")
  }

  private def generateHeightMap(size: Int): Array[Float] = {
    val heightmap = new Array[Float](size * size)
    for (i <- 0 until size) {
      for (j <- 0 until size) {
        val x = i.toFloat / size.toFloat * 10f
        val z = j.toFloat / size.toFloat * 10f
        heightmap(i * size + j) = (
          Math.sin(x).toFloat * 2f +
          Math.cos(z).toFloat * 2f +
          Math.sin(x * 0.5f + z * 0.3f).toFloat * 4f
        )
      }
    }
    heightmap
  }

  private def initBox(): Unit = {
    val box = new Box(1f, 1f, 1f)
    val geom = new Geometry("Box", box)
    val mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
    mat.setColor("Color", ColorRGBA.Green)
    geom.setMaterial(mat)
    geom.setLocalTranslation(0f, 10f, 0f)
    rootNode.attachChild(geom)

    val boxShape = new BoxCollisionShape(new Vector3f(1f, 1f, 1f))
    val boxControl = new RigidBodyControl(boxShape, 1f)
    boxControl.setPhysicsLocation(new Vector3f(0f, 10f, 0f))
    geom.addControl(boxControl)
    bulletAppState.getPhysicsSpace.add(boxControl)

    logger.info("Box with collision initialized.")
  }

  override def simpleUpdate(tpf: Float): Unit = {
    super.simpleUpdate(tpf)
  }
}