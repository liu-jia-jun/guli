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