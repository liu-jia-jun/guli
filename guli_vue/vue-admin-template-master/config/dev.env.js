'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
    NODE_ENV: '"development"',
    // 将请求地址改为自己本地的请求地址
    BASE_API: '"http://localhost:8000"',
})