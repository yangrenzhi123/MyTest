fun main(args: Array<String>) {
    //var t = KtTest()
    //t.test3()

    var testModel = TestModel()

    var setBook = setOf(TestModel(1), TestModel(2))
    setBook.forEach { println(it.id) }

    //var a = 1_0000;
}

class KtTest {

    //NULL检查
    fun test3() {
        //类型后面加?表示可为空
        //var age: String? = "23"
        var age = null;
        //抛出空指针异常
        //val ages = age!!.toInt()
        //不做处理返回 null
        //val ages1 = age?.toInt()
        //age为空返回-1
        val ages1 = age?.toInt() ?: -1

        println(ages1)
    }

    //字符串模板
    fun test2() {
        var a = 1
        // 模板中的简单名称：
        val s1 = "a is $a"

        a = 2
        // 模板中的任意表达式：
        val s2 = "${s1.replace("is", "was")}, but now is $a"
        println(s2)
    }

    //变量定义
    fun test() {
        val a: Int = 1
        val b = 1       //系统自动推断变量类型为Int
        val c: Int      //如果不在声明时初始化则必须提供变量类型
        c = 1           //明确赋值
        var x = 5        //系统自动推断变量类型为Int
        x += 1           //变量可修改
    }

    fun sum2(a: Int, b: Int) = a + b

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    //无返回值，Unit可以省略
    fun printSum(a: Int, b: Int): Unit {
        print(a + b)
    }

    //参数可变长
    fun vars(vararg v: Int) {
        for (vt in v) {
            println(vt)
        }
    }

    // 匿名函数
    fun noName() {
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        println(sumLambda(1, 2))  // 输出 3
    }
}