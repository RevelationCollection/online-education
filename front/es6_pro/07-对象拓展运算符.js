//拓展运算符（...）用于取出参数对象所有可遍历属性然后拷贝到当前对象。

let person = {name: 'Amy', age: 15}
// let someone = person //引用赋值

let someone = { ...person } //对拷拷贝
someone.name = 'Helen' 
console.log(person)  //{name: 'Amy', age: 15}
console.log(someone)  //{name: 'Helen', age: 15}