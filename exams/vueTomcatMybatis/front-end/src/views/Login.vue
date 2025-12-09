<script setup>
import {ref} from "vue";
import axios from "axios";
import router from "@/router/index.js";
import userStores from "@/stores/userStores.js";

const loginForm=ref({
  username:'',
  password:''
});

const login=()=>{
  axios.post('login',{
    username:loginForm.value.username,
    password:loginForm.value.password
  }).then((res)=>{
    if (res.data.code!==-1){
      userStores().setUser(res.data.data);
      router.push('/home');
    }else Element.Message.error('用户名或密码错误');
  })
}
</script>

<template>
  <div class="login-container">
    <div  class="login-form">
      <h2>Login</h2>

      <div class="form-group">
        <label for="username">Username</label>
        <input
            id="username"
            v-model="loginForm.username"
            type="text"
            placeholder="Enter your username"
            required
        />
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input
            id="password"
            v-model="loginForm.password"
            type="password"
            placeholder="Enter your password"
            required
        />
      </div>

      <el-button @click="login" class="w-full">Login</el-button>
    </div>
  </div>
</template>



<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f5f5;
}

.login-form {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

h2 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  color: #555;
  font-weight: 500;
}

input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
  transition: border-color 0.3s;
}

input[type="text"]:focus,
input[type="password"]:focus {
  outline: none;
  border-color: #409eff;
  box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.2);
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
  font-size: 0.9rem;
}

.checkbox-container {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.checkbox-container input {
  margin-right: 0.5rem;
}

.forgot-password {
  color: #409eff;
  text-decoration: none;
}

.forgot-password:hover {
  text-decoration: underline;
}

.login-button {
  width: 100%;
  padding: 0.75rem;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-button:hover:not(:disabled) {
  background-color: #66b1ff;
}

.login-button:disabled {
  background-color: #a0cfff;
  cursor: not-allowed;
}

.error-message {
  margin-top: 1rem;
  padding: 0.75rem;
  background-color: #fef0f0;
  color: #f56c6c;
  border-radius: 4px;
  text-align: center;
  font-size: 0.9rem;
}
</style>
