<script setup>
import { computed, onMounted, reactive, ref, watch } from 'vue'
import { get, post } from '@/net/index.js'
import { message } from 'ant-design-vue';
const [messageApi, contextHolder] = message.useMessage();

const loading = ref(false)
const rawList = ref([])

const keyword = ref('')
const page = ref(1)
const pageSize = ref(10)

const modalOpen = ref(false)
const modalMode = ref('create')
const formRef = ref()
const form = reactive({
  id: null,
  username: '',
  email: '',
  avatar: '',
  password: ''
})

const columns = [
  { title: 'ID', dataIndex: 'id', key: 'id', width: 80 },
  { title: '用户名', dataIndex: 'username', key: 'username' },
  { title: '邮箱', dataIndex: 'email', key: 'email' },
  { title: '账号', dataIndex: 'account', key: 'account' },
  { title: '头像', dataIndex: 'avatar', key: 'avatar' },
  { title: '注册时间', dataIndex: 'time', key: 'time', width: 180 },
  { title: '操作', key: 'actions', width: 160 }
]

const rules = computed(() => {
  return {
    username: [{ required: true, message: '请输入用户名' }],
    email: [{ required: true, message: '请输入邮箱' }],
    password:
      modalMode.value === 'create'
        ? [{ required: true, message: '请输入密码' }]
        : []
  }
})

const filteredList = computed(() => {
  const list = Array.isArray(rawList.value) ? rawList.value : []
  const k = String(keyword.value || '').trim().toLowerCase()
  if (!k) return list
  return list.filter((u) => {
    const username = String(u?.username ?? '').toLowerCase()
    const email = String(u?.email ?? '').toLowerCase()
    const account = String(u?.account ?? '').toLowerCase()
    return username.includes(k) || email.includes(k) || account.includes(k)
  })
})

const total = computed(() => filteredList.value.length)
const pagedList = computed(() => {
  const start = (page.value - 1) * pageSize.value
  return filteredList.value.slice(start, start + pageSize.value)
})

watch(keyword, () => {
  page.value = 1
})

watch([total, pageSize], () => {
  const maxPage = Math.max(1, Math.ceil(total.value / pageSize.value))
  if (page.value > maxPage) page.value = maxPage
})

function formatTime(value) {
  if (!value) return ''
  const d = new Date(value)
  if (Number.isNaN(d.getTime())) return String(value)
  const pad = (n) => String(n).padStart(2, '0')
  return `${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(d.getDate())} ${pad(d.getHours())}:${pad(d.getMinutes())}:${pad(d.getSeconds())}`
}

function fetchList() {
  loading.value = true
  get(
    'api/user/getAllUser',
    null,
    (_msg, data) => {
      rawList.value = Array.isArray(data) ? data : []
      loading.value = false
    },
    (msg) => {
      messageApi.warning(msg)
      loading.value = false
    },
    () => {
      messageApi.error('网络错误')
      loading.value = false
    }
  )
}

function resetForm() {
  form.id = null
  form.username = ''
  form.email = ''
  form.avatar = ''
  form.password = ''
  formRef.value?.clearValidate?.()
}

function openCreate() {
  modalMode.value = 'create'
  resetForm()
  modalOpen.value = true
}

function openEdit(record) {
  modalMode.value = 'edit'
  resetForm()
  form.id = record?.id ?? null
  form.username = record?.username ?? ''
  form.email = record?.email ?? ''
  form.avatar = record?.avatar ?? ''
  modalOpen.value = true
}

async function submit() {
  try {
    await formRef.value?.validate?.()
  } catch {
    return
  }

  if (modalMode.value === 'create') {
    post(
      'api/auth/register',
      {
        username: form.username,
        email: form.email,
        password: form.password
      },
      (msg) => {
        messageApi.success(msg || '新增成功')
        modalOpen.value = false
        fetchList()
      },
      (msg) => {
        messageApi.warning(msg)
      },
      () => {
        messageApi.error('网络错误')
      }
    )
  } else {
    post(
      'api/user/updateUser',
      {
        id: form.id,
        username: form.username,
        email: form.email,
        avatar: form.avatar
      },
      (msg) => {
        messageApi.success(msg || '更新成功')
        modalOpen.value = false
        fetchList()
      },
      (msg) => {
        messageApi.warning(msg)
      },
      () => {
        messageApi.error('网络错误')
      }
    )
  }
}

function removeUser(id) {
  if (!id) return
  post(
    `api/user/deleteUser/${id}`,
    {},
    (msg) => {
      messageApi.success(msg || '删除成功')
      fetchList()
    },
    (msg) => {
      messageApi.warning(msg)
    },
    () => {
      messageApi.error('网络错误')
    }
  )
}

onMounted(() => {
  fetchList()
})
</script>

<template>
  <component :is="contextHolder" />
  <div class="page">
    <div class="toolbar">
      <a-input
        v-model:value="keyword"
        allow-clear
        placeholder="搜索：用户名 / 邮箱 / 账号"
        style="max-width: 320px"
      />
      <a-space>
        <a-button @click="fetchList">刷新</a-button>
        <a-button type="primary" @click="openCreate">新增用户</a-button>
      </a-space>
    </div>

    <a-table
      :columns="columns"
      :data-source="pagedList"
      :loading="loading"
      :pagination="false"
      row-key="id"
      bordered
    >
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'time'">
          {{ formatTime(record.time) }}
        </template>

        <template v-else-if="column.key === 'avatar'">
          <a v-if="record.avatar" :href="record.avatar" target="_blank" rel="noreferrer">{{ record.avatar }}</a>
          <span v-else> - </span>
        </template>

        <template v-else-if="column.key === 'actions'">
          <a-space>
            <a-button size="small" @click="openEdit(record)">编辑</a-button>
            <a-popconfirm title="确认删除该用户？" @confirm="removeUser(record.id)">
              <a-button danger size="small">删除</a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </template>
    </a-table>

    <div class="pagination">
      <a-pagination
        v-model:current="page"
        v-model:pageSize="pageSize"
        :total="total"
        show-size-changer
        show-quick-jumper
      />
    </div>

    <a-modal
      v-model:open="modalOpen"
      :title="modalMode === 'create' ? '新增用户' : '编辑用户'"
      :ok-text="modalMode === 'create' ? '创建' : '保存'"
      cancel-text="取消"
      @ok="submit"
      @cancel="() => (modalOpen = false)"
      destroy-on-close
    >
      <a-form ref="formRef" :model="form" :rules="rules" layout="vertical">
        <a-form-item label="用户名" name="username">
          <a-input v-model:value="form.username" allow-clear />
        </a-form-item>
        <a-form-item label="邮箱" name="email">
          <a-input v-model:value="form.email" allow-clear />
        </a-form-item>
        <a-form-item label="头像" name="avatar">
          <a-input v-model:value="form.avatar" allow-clear placeholder="可填 URL 或文件名" />
        </a-form-item>
        <a-form-item v-if="modalMode === 'create'" label="密码" name="password">
          <a-input-password v-model:value="form.password" allow-clear />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<style scoped>
.page {
  padding: 16px;
}

.toolbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  margin-bottom: 12px;
}

.pagination {
  display: flex;
  justify-content: flex-end;
  margin-top: 12px;
}
</style>
