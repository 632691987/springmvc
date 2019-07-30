var app = new Vue(
    {
        el: "#app",
        data: {
            message: "Hello Vue!"
        }
    }
);

var app2 = new Vue({
    el: '#app-2',
    data: {
        message: '页面加载于 ' + new Date().toLocaleString()
    }
});

var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: true
    }
});

var app4 = new Vue({
    el: '#app-4',
    data: {
        todos: [
            { text: 'Learn JavaScript', value: 1 },
            { text: 'Learn Vue', value: 2 },
            { text: 'Build something awesome', value: 3 }
        ]
    }
});

var app5 = new Vue({
    el: '#app-5',
    data: {
        message1: 'Hello Vue.js!'
    },
    methods: {
        reverseMessage: function () {
            this.message1 = this.message1.split('').reverse().join('');
        }
    }
});