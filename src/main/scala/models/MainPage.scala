package models

import com.jme3.app.SimpleApplication
import com.jme3.light.{AmbientLight, DirectionalLight}
import com.jme3.material.Material
import com.jme3.math.{ColorRGBA, Vector3f}
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import log.ColoredLog

import scala.compiletime.uninitialized

/**
 * MainPage - 主场景页面
 *
 * 本场景展示一个俯视视角的3D场景，包含多个彩色立方体和一个可交互的定向光源。
 * 光源方向跟随鼠标移动，产生动态光影效果。
 */
class MainPage extends SimpleApplication {

  // 日志记录器，用于输出程序运行信息
  private val logger = ColoredLog(getClass)

  // 定向光源（太阳光），方向会跟随鼠标移动
  private var sun: DirectionalLight = uninitialized

  // 当前光源方向，用于平滑插值
  private val currentSunDirection = new Vector3f(0.0f, -1.0f, -0.5f).normalizeLocal()

  // 光源方向平滑插值速度（每秒）
  private val lerpSpeed = 8.0f

  /**
   * 初始化应用程序
   * 设置相机、光源和场景物体
   */
  override def simpleInitApp(): Unit = {
    logger.info("Starting Main-page Program")

    // 设置视口背景颜色为灰色
    viewPort.setBackgroundColor(ColorRGBA.Gray)

    // 禁用默认飞行相机控制，避免鼠标控制镜头旋转并显示鼠标光标，方便用户交互
    flyCam.setEnabled(false)
    inputManager.setCursorVisible(true)

    // 设置固定俯视视角
    // 相机位置：正上方 (0, 20, 0)
    // 观察目标：原点 (0, 0, 0)
    // 上方向：-Z 轴（屏幕上方）
    cam.setLocation(new Vector3f(0.0f, 20.0f, 0.0f))
    cam.lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -1.0f))

    // 添加环境光，提供基础照明，避免纯黑区域
    val ambient = new AmbientLight()
    ambient.setColor(ColorRGBA.White.mult(0.4f))
    rootNode.addLight(ambient)

    // 添加定向光（主光源），模拟太阳光
    // 使用暖白色，强度较高，产生明显的明暗对比
    sun = new DirectionalLight()
    sun.setColor(new ColorRGBA(1.0f, 0.95f, 0.9f, 1.0f).mult(1.2f))
    sun.setDirection(currentSunDirection.clone())
    rootNode.addLight(sun)

    // 创建场景中的3D物体
    createSceneObjects()

    logger.info("Scene initialization completed")
  }

  /**
   * 创建场景中的3D物体
   * 包括地面平台和多个彩色立方体
   */
  private def createSceneObjects(): Unit = {

    // 创建受光照影响的材质模板
    // 使用 Lighting.j3md 材质，支持漫反射、环境光和高光
    val litMaterial = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md")
    litMaterial.setBoolean("UseMaterialColors", true)
    litMaterial.setColor("Diffuse", ColorRGBA.Blue)
    litMaterial.setColor("Ambient", ColorRGBA.DarkGray)
    litMaterial.setColor("Specular", ColorRGBA.White)
    litMaterial.setFloat("Shininess", 64.0f)

    // 创建地面平台（蓝色）
    val ground = new Geometry("Ground", new Box(6.0f, 0.2f, 4.0f))
    ground.setMaterial(litMaterial)
    ground.setLocalTranslation(0.0f, -2.0f, 0.0f)
    rootNode.attachChild(ground)

    // 创建立方体 1 - 前排左侧（红色）
    val box1 = new Geometry("Box1", new Box(0.8f, 0.8f, 0.8f))
    val mat1 = litMaterial.clone()
    mat1.setColor("Diffuse", ColorRGBA.Red)
    box1.setMaterial(mat1)
    box1.setLocalTranslation(-2.5f, 0.0f, 1.0f)
    rootNode.attachChild(box1)

    // 创建立方体 2 - 前排中间（绿色）
    val box2 = new Geometry("Box2", new Box(0.8f, 0.8f, 0.8f))
    val mat2 = litMaterial.clone()
    mat2.setColor("Diffuse", ColorRGBA.Green)
    box2.setMaterial(mat2)
    box2.setLocalTranslation(0.0f, 0.0f, 1.0f)
    rootNode.attachChild(box2)

    // 创建立方体 3 - 前排右侧（黄色）
    val box3 = new Geometry("Box3", new Box(0.8f, 0.8f, 0.8f))
    val mat3 = litMaterial.clone()
    mat3.setColor("Diffuse", ColorRGBA.Yellow)
    box3.setMaterial(mat3)
    box3.setLocalTranslation(2.5f, 0.0f, 1.0f)
    rootNode.attachChild(box3)

    // 创建立方体 4 - 后排左侧（品红色）
    val box4 = new Geometry("Box4", new Box(0.8f, 0.8f, 0.8f))
    val mat4 = litMaterial.clone()
    mat4.setColor("Diffuse", ColorRGBA.Magenta)
    box4.setMaterial(mat4)
    box4.setLocalTranslation(-2.5f, 0.0f, -1.5f)
    rootNode.attachChild(box4)

    // 创建立方体 5 - 后排中间（青色）
    val box5 = new Geometry("Box5", new Box(0.8f, 0.8f, 0.8f))
    val mat5 = litMaterial.clone()
    mat5.setColor("Diffuse", ColorRGBA.Cyan)
    box5.setMaterial(mat5)
    box5.setLocalTranslation(0.0f, 0.0f, -1.5f)
    rootNode.attachChild(box5)

    // 创建立方体 6 - 后排右侧（橙色）
    val box6 = new Geometry("Box6", new Box(0.8f, 0.8f, 0.8f))
    val mat6 = litMaterial.clone()
    mat6.setColor("Diffuse", ColorRGBA.Orange)
    box6.setMaterial(mat6)
    box6.setLocalTranslation(2.5f, 0.0f, -1.5f)
    rootNode.attachChild(box6)
  }

  /**
   * 每帧更新函数
   * @param tpf 每帧时间间隔（秒）
   */
  override def simpleUpdate(tpf: Float): Unit = {
    updateSunDirection(tpf)
  }

  /**
   * 更新定向光方向，使其跟随鼠标位置
   *
   * 计算逻辑：
   * 1. 获取鼠标在屏幕上的位置
   * 2. 将屏幕坐标转换为 [-1, 1] 范围
   * 3. 根据鼠标位置计算光源目标方向
   * 4. 使用线性插值（lerp）平滑过渡当前方向到目标方向
   *
   * @param tpf 每帧时间间隔（秒）
   */
  private def updateSunDirection(tpf: Float): Unit = {

    // 获取当前鼠标在屏幕上的坐标（左下角为原点）
    val mousePos = inputManager.getCursorPosition
    val screenWidth = cam.getWidth.toFloat
    val screenHeight = cam.getHeight.toFloat

    // 将鼠标屏幕坐标归一化到 [-1, 1] 范围
    // 屏幕中心为 (0, 0)，右下角为 (1, 1)，左上角为 (-1, -1)
    val nx = (mousePos.x / screenWidth) * 2.0f - 1.0f
    val ny = (mousePos.y / screenHeight) * 2.0f - 1.0f

    // 根据鼠标位置计算光源目标方向
    // 鼠标在屏幕中心时，光源垂直向下照射
    // 鼠标向右移动时，光源从左侧照来（产生向右的阴影）
    // 鼠标向上移动时，光源从后方照来（产生向前的阴影）
    val dirX = -nx * 2.0f
    val dirY = -1.0f
    val dirZ = ny * 2.0f

    val targetDirection = new Vector3f(dirX, dirY, dirZ).normalizeLocal()

    // 使用线性插值（lerp）平滑过渡光源方向
    // t 值越大，光源跟随越灵敏；t 值越小，过渡越平滑
    val t = math.min(lerpSpeed * tpf, 1.0f)
    currentSunDirection.interpolateLocal(targetDirection, t)
    currentSunDirection.normalizeLocal()

    // 应用新的光源方向
    sun.setDirection(currentSunDirection.clone())
  }
}