# npm 
+ npm init -y 
> 初始化该文件夹，可以通过npm下载js文件
+ npm install vue
> 下载最新版本的js文件


# node
+ node + 文件路径
> 通过node.js 运行该js文件


# babel
1. 全局安装babel插件
```npm insatll -global babel-cli```
2. 创建js文并写入es6代码
3. 在项目根目录下创建```.babelrc```文件
```
{
    "presets": ["es2015"],
    "plugins": []
}
```
4. 安装es2015转码器```npm install --save-dev babel-preset-es2015```

5. 使用命令进行转码
> 根据文件转码
>> ```babel es6文件地址 -0 转换生成的文件地址```

> 根据文件夹转码
>> ```babel es6文件夹地址 -d 转换生成的文件夹地址```