// 引入01.js文件中的函数并调用


/**
 * 
 *es5写法 

// 1. 引入01.js文件
const js1 = require("./01")
    // 2. 通过变量来调用方法
js1.fun1_1()
 */


// es6写法

import { fun1 } from "./01";