<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>VUE Index</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <c:url var="jsVar" value="/vue/resources/index.js"/>
    <c:url var="cssVar" value="/vue/resources/index.css"/>
    <link rel="stylesheet" href="${cssVar}"/>
</head>
<body>

<p>这个例子展示的是 Hello World</p>
    <div id="app">
        {{message}}
    </div>

<p>这个例子展示的是 message</p>
    <div id="app-2">
      <span v-bind:title="message">
        鼠标悬停几秒钟查看此处动态绑定的提示信息！
      </span>
    </div>


<p>这个例子展示的是 Conditionals</p>
    <div id="app-3">
        <span v-if="seen">Now you see me</span>
    </div>

<p>这个例子展示的是 loop</p>
<div id="app-4">
    <ol>
        <li v-for="varStr in todos">
            {{varStr.text}}
        </li>
    </ol>
</div>

<p>这个例子展示的是 button</p>
<div id="app-5">
    <p>{{ message1 }}</p>
    <button v-on:v-on:click="reverseMessage">Reverse Message</button>
</div>

<script src="${jsVar}"></script>
</body>
</html>
