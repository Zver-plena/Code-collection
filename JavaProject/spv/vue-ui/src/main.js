
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from './plugins/axios.js'

const app = createApp(App)

app.use(store).use(router).use(axios)
app.mount('#app');

