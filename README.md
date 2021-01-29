# AndroidPlugin
Android插件化实现方式，包括代理方式和hook方式
### PluginProxy是通过代理的方式实现插件化
/**
 * https://www.androidos.net.cn
 * 插件化：每个组件业务就是一个独立apk，然后通过主app动态加载部署业务组件apk
 *
 * 优势 ：1、业务组件解耦，能够实现业务组件热插拔
 *       2、更改产品迭代模式，可分为主app以及次业务app
 *       3、改善产品更新过程，可以在不影响用户的情况下实现业务组件更新以及bug修复
 *
 * 插件化步骤——分析主App
 *    1、主App打包完成后，会形成dex，images，xml资源
 *    2、Dex靠PathClassLoader加载
 *    3、图片以及xml资源靠Resource加载
 *
 * 插件化步骤——代码实现
 *     1、创建DexClassLoader加载插件代码
 *     2、创建Resource加载资源文件
 *     3、管理插件Activity生命周期
 * 本例子是通过主app启动插件包中的Activity
 */

### Replugin是hook方式实现
* 加载插件的类，启动插件的Activity，加载插件资源，解决资源冲突

