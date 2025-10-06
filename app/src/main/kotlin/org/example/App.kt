package org.example

import java.io.File

data class cditem(val name: String , val cz: () -> Unit)
class cd (val name: String) {
    val item: MutableList<cditem> = mutableListOf() //总列表
    var exit: Boolean = false //声明退出值
    var daoyu:  String = "" //导语
    fun add(cditems: cditem) {
        //添加 函数
        item.add(cditems)
    }
    fun rm(name: cditem) {
        //删除 函数
        item.remove(name)
    }
    fun dh(name: cditem , index: Int) {
        //调换 函数
        val names = name //临时names
        val indexs = item.indexOf(name) //name位置
        item.set(indexs,names) //设置临时names覆盖原name
        item.add(index,name) //添加name到目标位置
        item.remove(names) //删除临时names
    }
    fun printcd() {
        //打印菜单
        println("$name 菜单")
        println(daoyu)
        val i = item.size - 1 //索引结束位
        for (n in 0..i) {
            //遍历菜单并打印
            val hao = n + 1
            println("${hao}. ${item[n].name}")
            Thread.sleep(90)
        }
    }
    fun sr(): ()-> Unit {
        //用户输入
        var czs: () -> Unit
        wai@ while (true) {
            //循环一嵌套-输入空安全
            print("选择(输入数字): ")
            val srzhishu = readln().toIntOrNull() //输入值(输入)
            if (srzhishu == null) {
                println("请输入有效数字!")} else {
                while (true) {
                    //循环二嵌套-索引空安全
                    val srzhishi = srzhishu - 1 //输入值(真实/索引)
                    when {
                        srzhishu > item.size || srzhishi < 0 -> {
                            println("选项不存在!")
                            break
                        }
                        else -> {
                            val czw = item[srzhishi].cz
                            czs = czw
                            break@wai //退出最外层嵌套循环/退出所有循环
                        }
                    }
                }
            }
        }
        return czs //返回请求到主线程处理
    }
    fun csh(daoyus:String = "") {
        //初始化
        daoyu = daoyus
        add(cditem("退出", {
            exit = true
        }))
    }
    fun addgn() {
        //添加功能
        add(cditem("打印", {
            prints()}))
        add(cditem("计算器", {
            jsq()}))
        add(cditem("test", {
            test()}))
    }
}
fun prints() {
    //功能:打印
    while (true) {
        print("输入exit退出\n输入需要打印的值: ")
        val zhi: String? = readlnOrNull()
        when (zhi) {
            null -> println("错误")
            "exit" -> return
            else -> println(zhi)
        }
    }
}
fun jsq() {
    //计算器 功能
    var lx: String //类型
    while (true) {
        var lxs: String?
        print ("选择类型(Int,Long,Double),或者输入exit退出:\n> ")
        lxs = when(readlnOrNull()) {
            "1", "Int" , "int" -> "Int"
            "2", "Long" , "long" -> "Long"
            "3","Double", "double" -> "Double"
            "exit" -> "exit"
            else -> null
        }
        if (lxs == "exit") {
            return
        }
        if (lxs == null) {
            println("类型无效!")
            continue
        }
        lx = lxs
        break
    }
    while (true) {
        print("输入计算式，或者exit退出:\n> ")
        val jss = readlnOrNull()?.split(" ")
        if (jss == null) {
            println("错误")
            continue
        }
        if (jss[0] == "exit") {
            return
        }
        if (jss.size != 3) {
            println("计算式不合法,举例:[1 + 1]")
            continue
        }
        when(lx) {
            "Int" -> {
                val z1 = jss[0].toIntOrNull()
                val z2 = jss[2].toIntOrNull()
                if (z1 == null || z2 == null) {
                    println("数值无效")
                    continue
                }
                println("正在计算......")
                val jg: Int? = runCatching {when(jss[1]) {
                        "+","&" -> z1 + z2
                        "-" -> z1 - z2
                        "*","×","•","x" -> z1 * z2
                        "/","÷" -> z1 / z2
                        else -> null
                    }
                }.getOrNull()
                if (jg == null) {
                    println("计算符或结果无效!")
                    continue
                }
                println ("= $jg")
            }
            "Long" -> {
                val z1 = jss[0].toLongOrNull()
                val z2 = jss[2].toLongOrNull()
                if (z1 == null || z2 == null) {
                    println("数值无效")
                    continue
                }
                println("正在计算......")
                val jg: Long? = runCatching{when(jss[1]) {
                        "+","&" -> z1 + z2
                        "-" -> z1 - z2
                        "*","×","•","x" -> z1 * z2
                        "/","÷" -> z1 / z2
                        else -> null
                    }
                }.getOrNull()
                if (jg == null) {
                    println("计算符或结果无效!")
                    continue
                }
                println ("= $jg")
            }
            "Double" -> {
                val z1 = jss[0].toDoubleOrNull()
                val z2 = jss[2].toDoubleOrNull()
                if (z1 == null || z2 == null) {
                    println("数值无效")
                    continue
                }
                println("正在计算......")
                val jg: Double? = runCatching{when(jss[1]) {
                        "+","&" -> z1 + z2
                        "-" -> z1 - z2
                        "*","×","•","x" -> z1 * z2
                        "/","÷" -> z1 / z2
                        else -> null
                    }
                }.getOrNull()
                if (jg == null) {
                    println("计算符或结果无效!")
                    continue
                }
                println ("= $jg")
            } else -> {
                println("错误")}
        }
        /*                if (z1 == null || z2 == null) {
                        println("数值无效")
                        continue
                        }
                println("正在计算......")
                val jg = when(jss[1]) {
                        "+","&" -> z1 + z2
                        "-" -> z1 - z2
                        "*","×","•","x" -> z1 * z2
                        "/","÷" -> z1 / z2
                        else -> null
                        }
                if (jg == null) {
                        println("计算符或结果无效!")
                        continue
                        }
                println(jg)
                }*/
    }
}
fun test() {
    while(true) {
        val testcd = cd("test菜单").apply {
            csh("test")
            //添加功能1
            add(cditem("test1", {files()}))
            //结束1
        }
        csdh("TEST")
        testcd.printcd()
        val czs1 = testcd.sr()
        czs1()
        if (testcd.exit == true) {
            println("EXIT")
            return
        }
    }
}
fun files() {
    while(true) {
        print("输入exit退出\n选择文件:\n> ")
        val sr = readlnOrNull()
        if (sr == "exit") {break}
        val file = File("$sr")
        when {
            file.exists() == false -> {
                println("文件读取失败")
                continue
            }
            file.isDirectory() == true -> {
                println("目标为文件夹!")
                continue
            }
            file.isFile() == false -> {
                println("目标不是文件!")
                continue
            }
            else -> {
                println("正在读取")
            }
        }
        val filelist: List<String> = file.readLines()
        val filelists: MutableList<String> = filelist.toMutableList()
        val filecd = cd("文件菜单").apply {
            csh("你想对这个文件干什么?\n -${file.name}")
            }
        filecd.add(cditem("读取范围行",{
            var fw: List<String>?          //范围
            var z1: Int?
            var z2: Int?
            while(true) {
                print("输入exit退出\n输入要读取的行(i..i):\n> ")
                val sr1 = readlnOrNull()
                if (sr1 == "exit") {break}
                fw = sr1?.split("..")
                if (fw == null || fw.size != 2) {
                    println("行数无效!")
                    continue
                }
                z1 = fw[0].toIntOrNull()
                z2 = fw[1].toIntOrNull()
                if (z1 == null || z2 == null) {
                    println("行数无效!")
                    continue
                }
                when {
                    (filelist.size < z1 || filelist.size < z2 || z1 < 0 || z1 < 0) -> {
                        println("行数无效!")
                        continue
                    }
                    z1 > z2 -> {
                        val s = z1
                        z1 = z2
                        z2 = s
                        println("正在操作")
                    }
                    else ->{
                        println("正在操作")
                    }
                }
                println("---")
                val fwz1 = z1 - 1   //范围
                val fwz2 = z2 - 1
                for (n in fwz1..fwz2) {
                    println("${n + 1} ${filelist[n]}")
                    Thread.sleep(10)
                }
                println("---\n读取完成")
            }
                
        }))
        filecd.printcd()
        val czs2 = filecd.sr()
        czs2()
        if (filecd.exit == true) {
        println("EXIT")
        return
        }
    return
    }
}
fun csdh (wenben: String) {
    //初始动画
    for (n in wenben) {
        //遍历传递文本
        print(n)
        Thread.sleep(90) //阻塞进程
    }
    for (n in wenben) {
        print("\b")
        Thread.sleep(90)
    }
    println()
}
fun main () {
    val main = cd("主").apply { //设置主类菜单类对象
        csh("你想进入哪个功能?") //菜单初始化
        addgn() //引入/添加功能
    }
    while (true) {
        //用户命令判断循环
        csdh("Welcome!") //初始动画
        main.printcd() //打印菜单
        val czs = main.sr() //请求用户输入并接受命令到主线程
        czs() //处理命令/进入功能函数
        if (main.exit == true) {
            println("EXIT")
            return
        } //判断结束
    }
}
