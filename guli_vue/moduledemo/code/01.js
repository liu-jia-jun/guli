/**
 * 
 * es5 写法

const fun1_1 = function() {
    console.log("fun1_1")
}

const fun1_2 = function() {
    console.log("fun1_2")
}



// 通过module.exports 将01.js中的函数暴露出去，给其他js文件引入并使用
// 如果不暴露函数，其他js文件将无法引用并使用01.js文件中所定义的函数
module.exports = {
    fun1_1,
    fun1_2
}
 
 
 */

// es6写法

export function fun1() {
    console.log("es6模块化写法")
}


// 推荐
export default {
    fun2() {
        console.log("es6模块第二种写法-推荐")
    }
}