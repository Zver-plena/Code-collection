<template>
<div>
  <table>
    <tr>
      <td>id</td>
      <td>name</td>
      <td>author</td>

    </tr>
    <tr  v-for="book in books">
      <td >{{book.id}}</td>
      <td>{{book.name}}</td>
      <td>{{book.author}}</td>
    </tr>
  </table>
</div>
</template>

<script>
import {getCurrentInstance} from "vue";

export default {
  name: "Book.vue",
  data(){
    return {
      msg: 'hello vue',
      books:[
        {
         id: 1,
         name: 'java',
          author: 'xiaobai'
        },
        {
          id: 2,
          name: 'veu',
          author: 'xiaoguang'
        }
      ]
    }
  },
  created(){
    const {proxy} = getCurrentInstance();
    proxy.axios({
      "method":"get",	//请求方式
      "url":"http://localhost:8181/getAllBooks", //请求
    }).then(function (resp) { 	//接受服务端的响应
      console.log(resp.data);
      proxy.books = resp.data;
    }).catch(function (error) {		//接受服务端的异常
      console.log(error);
    });

  }

}
</script>

<style scoped>

</style>