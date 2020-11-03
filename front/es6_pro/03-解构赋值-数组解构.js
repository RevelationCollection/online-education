/*
解构赋值是对赋值运算符的扩展。
他是一种针对数组或者对象进行模式匹配，然后对其中的变量进行赋值。
在代码书写上简洁且易读，语义更加清晰明了；也方便了复杂对象中数据字段获取
*/
//1、数组解构
let arr = [1, 2, 3]

// 传统
let a = arr[0]
let b = arr[1]
let c = arr[2]

console.log(a, b, c)

// ES6

let [x, y, z] = arr
console.log(x, y, z)
