<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { post } from '@/net/index.js'

const router = useRouter()

const activeTab = ref('login')
const loginRef = ref()
const registerRef = ref()

const loginLoading = ref(false)
const registerLoading = ref(false)

const loginForm = reactive({
  email: '',
  password: ''
})

const registerForm = reactive({
  username: '',
  email: '',
  password: '',
  confirmPassword: ''
})

const loginRules = {
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '邮箱格式不正确', trigger: ['blur', 'change'] }
  ],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

const registerRules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '邮箱格式不正确', trigger: ['blur', 'change'] }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码至少 6 位', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    {
      validator: (_rule, value, callback) => {
        if (value !== registerForm.password) callback(new Error('两次输入的密码不一致'))
        else callback()
      },
      trigger: ['blur', 'change']
    }
  ]
}

function onLogin() {
  if (!loginRef.value) return
  loginRef.value.validate((valid) => {
    if (!valid) return
    loginLoading.value = true
    post(
      'api/auth/login',
      {
        email: loginForm.email,
        password: loginForm.password
      },
      (_message, token) => {
        localStorage.setItem('authToken', token)
        ElMessage.success('登录成功')
        router.push('/')
        loginLoading.value = false
      },
      (message) => {
        ElMessage.warning(message)
        loginLoading.value = false
      },
      () => {
        ElMessage.error('网络错误，请稍后重试')
        loginLoading.value = false
      }
    )
  })
}

function onRegister() {
  if (!registerRef.value) return
  registerRef.value.validate((valid) => {
    if (!valid) return
    registerLoading.value = true
    post(
      'api/auth/register',
      {
        username: registerForm.username,
        email: registerForm.email,
        password: registerForm.password
      },
      (message) => {
        ElMessage.success(message || '注册成功')
        activeTab.value = 'login'
        loginForm.email = registerForm.email
        loginForm.password = ''
        registerForm.password = ''
        registerForm.confirmPassword = ''
        registerLoading.value = false
      },
      (message) => {
        ElMessage.warning(message)
        registerLoading.value = false
      },
      () => {
        ElMessage.error('网络错误，请稍后重试')
        registerLoading.value = false
      }
    )
  })
}
</script>

<template>
  <div class="auth-container">
    <el-card class="auth-card" shadow="hover">
      <el-tabs v-model="activeTab" stretch>
        <el-tab-pane label="登录" name="login">
          <el-form ref="loginRef" :model="loginForm" :rules="loginRules" label-position="top">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="loginForm.email" placeholder="请输入邮箱" autocomplete="username" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input
                v-model="loginForm.password"
                placeholder="请输入密码"
                type="password"
                show-password
                autocomplete="current-password"
                @keyup.enter="onLogin"
              />
            </el-form-item>
            <el-button type="primary" style="width: 100%" :loading="loginLoading" @click="onLogin">
              登录
            </el-button>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="注册" name="register">
          <el-form ref="registerRef" :model="registerForm" :rules="registerRules" label-position="top">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="registerForm.username" placeholder="请输入用户名" autocomplete="nickname" />
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="registerForm.email" placeholder="请输入邮箱" autocomplete="email" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input
                v-model="registerForm.password"
                placeholder="请输入密码"
                type="password"
                show-password
                autocomplete="new-password"
              />
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmPassword">
              <el-input
                v-model="registerForm.confirmPassword"
                placeholder="请再次输入密码"
                type="password"
                show-password
                autocomplete="new-password"
                @keyup.enter="onRegister"
              />
            </el-form-item>
            <el-button type="primary" style="width: 100%" :loading="registerLoading" @click="onRegister">
              注册
            </el-button>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<style scoped>
.auth-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 24px;
  background: linear-gradient(135deg, #f6f8ff 0%, #ffffff 60%, #f0f7ff 100%);
}

.auth-card {
  width: 420px;
  border-radius: 12px;
}
</style>
