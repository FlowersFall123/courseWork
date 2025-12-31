<script setup>
import { onMounted, reactive, ref } from 'vue'
import { get, post } from '@/net/index.js'
import { message } from 'ant-design-vue';
const [messageApi, contextHolder] = message.useMessage();

const messages=ref();
const user=ref();
const form=reactive({
  toUserId:"",
  context:""
})
const getMessage=()=>{
  get("api/user/getMessageByToUserId",null,(msg,data)=>{
    messages.value=data;
  })
}
const getAllUser=()=>{
  get("api/user/getAllUser",null,(msg,data)=>{
    user.value=data;
  })
}
const sendMessage=()=>{
  if (!form.context || !String(form.context).trim()) {
    messageApi.warning('请输入留言内容')
    return
  }
  post("api/user/InsertMessage",{
    toUserId:form.toUserId,
    context:form.context
  },(msg)=>{
    messageApi.success(msg);
    form.context = ''
    form.toUserId = ''
    getMessage()
  })
}
onMounted(()=>{
  getMessage();
  getAllUser()
})
</script>

<template>
  <component :is="contextHolder" />
  <div class="message-board"></div>
  <h2 class="message-board-title">祝福墙</h2>
  <div class="message-board-form">
    <!-- 用 v-for 渲染留言，并设置动态颜色 -->
    <p v-for="(msg, index) in messages" :key="index" :style="{ color: msg.color }" class="message">{{ msg.username }}-{{ msg.context }}</p>
  </div>

  <div class="add-message">
    <div class="add-message-row">
      <span class="add-message-label">新年祝福:</span>
      <a-input
        v-model:value="form.context"
        placeholder="请输入留言内容"
        :maxlength="200"
        allow-clear
      />
    </div>
    <div class="add-message-row">
      <span class="add-message-label">指定人员:</span>
      <a-select
        v-model:value="form.toUserId"
        placeholder="请选择"
        style="width: 100%"
        allow-clear
        :options="[
          { value: '', label: '全员' },
          ...((user || []).map(u => ({
            value: u.id ?? u.userId ?? u.uid ?? u.email ?? u.username,
            label: u.username ?? u.name ?? u.nickname ?? u.email ?? String(u.id ?? u.userId ?? '')
          })))
        ]"
      />
    </div>
    <div class="add-message-actions">
      <a-button type="primary" @click="sendMessage">提交</a-button>
      <a-button @click="() => { form.context = ''; form.toUserId = '' }">重置</a-button>
    </div>
  </div>
</template>

<style scoped>
.message-board {
  background-image: url(http://aiit.iflysse.com/web/img/sign_bg.39acaa94.png);
  background-repeat: no-repeat;
  border-radius: 10px;
  width: 1380px;
  height: 400px;
  margin-left: 0px;
  position: absolute;
  top: 50px;
  left: 170px;
}

.message-board-title {
  position: absolute;
  top: 90px;
  left: 280px;
}

.message-board-form {
  width: 1190px;
  height: 300px;
  position: absolute;
  top: 95px;
  left: 278px;
  overflow-y: hidden; /* 允许垂直滚动 */
  overflow-x: hidden; /* 禁止水平滚动 */
  padding: 10px; /* 增加一些内边距 */
}

.add-message {
  position: absolute;
  top: 470px;
  left: 600px;
  width: 520px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  z-index: 2;
}

.add-message-row {
  display: grid;
  grid-template-columns: 90px 1fr;
  align-items: center;
  column-gap: 10px;
}

.add-message-label {
  text-align: right;
  color: rgba(0, 0, 0, 0.85);
  font-size: 14px;
}

.add-message-actions {
  display: flex;
  gap: 10px;
  padding-left: 100px;
}

.message {
  position: relative;
  animation: move 10s linear infinite;
  font-size: 16px;
  color: rgb(250, 163, 28);
  white-space: nowrap;
}
.navbar {
  position: fixed;
  bottom: 0;
  width: 100%;
}

@keyframes move {
  0% {
    left: -100px;
  }
  100% {
    left: 1190px;
  }
}
</style>
