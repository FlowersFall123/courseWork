<script setup>
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import {message} from "ant-design-vue";
import { Plus, ArrowLeft } from '@element-plus/icons-vue';

const tableData=ref();
const isShowAdd=ref(false);
const [messageApi, contextHolder] = message.useMessage();


// do not use same name with ref
const form = reactive({
  name: '',
  region: '',
  date1: '',
  delivery: false,
  type: '',
  resource: '',
  desc: '',
})
const getAllPet=()=>{
  axios.get('get').then((res)=>{
    tableData.value=res.data;
  })
}
const onSubmit=()=>{
  axios.post('pet',{
    petName:form.name,
    species:form.region,
    sex:form.resource,
    time:form.date1,
    option:form.type,
    notes:form.desc
  }).then((res)=>{
    // 检查响应中的 code 字段
    if (res.data && res.data === -1){
      messageApi.error('添加失败')
    } else {
      message.success('添加成功');
      isShowAdd.value=false;
      // 重置表单
      form.name = '';
      form.region = '';
      form.date1 = '';
      form.delivery = false;
      form.type = '';
      form.resource = '';
      form.desc = '';
      getAllPet();
    }
  }).catch((error)=>{
    messageApi.error('添加失败: ' + (error.response?.data?.message || error.message));
  })
}
onMounted(()=>{
  getAllPet();
})
</script>

<template>
  <div class="home-container">
    <div class="header-section">
      <el-button 
        v-if="!isShowAdd" 
        @click="isShowAdd=true" 
        type="primary"
      >
        <el-icon><Plus /></el-icon>
        添加宠物
      </el-button>
      <el-button 
        v-if="isShowAdd" 
        @click="isShowAdd=false"
      >
        <el-icon><ArrowLeft /></el-icon>
        返回
      </el-button>
    </div>

    <el-card v-if="!isShowAdd" shadow="never">
      <el-table 
        :data="tableData" 
        style="width: 100%"
        stripe
      >
        <el-table-column prop="id" label="ID" width="120" align="center"/>
        <el-table-column prop="petName" label="名称" width="150" align="center"/>
        <el-table-column prop="species" label="品种" width="150" align="center"/>
        <el-table-column prop="sex" label="性别" width="120" align="center"/>
        <el-table-column prop="time" label="生日" width="180" align="center"/>
        <el-table-column prop="option" label="选项" width="150" align="center"/>
        <el-table-column prop="notes" label="备注" min-width="200" align="center"/>
      </el-table>
    </el-card>

    <el-card v-if="isShowAdd" shadow="never">
      <el-form 
        v-if="isShowAdd" 
        label-width="100px" 
        :model="form"
        class="pet-form"
      >
        <el-form-item label="宠物名称">
          <el-input 
            v-model="form.name" 
            placeholder="请输入宠物名称"
            clearable
          />
        </el-form-item>
        <el-form-item label="宠物品种">
          <el-select 
            v-model="form.region" 
            placeholder="请选择宠物品种"
            style="width: 100%"
            clearable
          >
            <el-option label="泰迪" value="泰迪" />
            <el-option label="中华黒犬" value="中华黒犬" />
          </el-select>
        </el-form-item>
        <el-form-item label="宠物生日">
          <el-date-picker
            v-model="form.date1"
            type="date"
            placeholder="请选择生日"
            style="width: 100%"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
          />
        </el-form-item>
        <el-form-item label="是否住院">
          <el-switch v-model="form.delivery" />
        </el-form-item>
        <el-form-item label="看病选项">
          <el-radio-group v-model="form.type" class="radio-group">
            <el-radio value="打吊针" label="打吊针" />
            <el-radio value="购买药物" label="购买药物" />
            <el-radio value="住院" label="住院" />
          </el-radio-group>
        </el-form-item>
        <el-form-item label="宠物性别">
          <el-radio-group v-model="form.resource" class="radio-group">
            <el-radio value="boy" label="雄" />
            <el-radio value="girl" label="雌" />
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注">
          <el-input 
            v-model="form.desc" 
            type="textarea" 
            :rows="3"
            placeholder="请输入备注信息"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">
            提交
          </el-button>
          <el-button @click="isShowAdd=false">
            取消
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.home-container {
  padding: 20px;
}

.header-section {
  margin-bottom: 20px;
}

.pet-form {
  max-width: 600px;
}

.radio-group {
  display: flex;
  gap: 20px;
}
</style>