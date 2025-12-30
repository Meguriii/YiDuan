<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="addrName">
        <el-input
          v-model="queryParams.addrName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="addrTel">
        <el-input
          v-model="queryParams.addrTel"
          placeholder="请输入电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省" prop="addrProv">
        <el-input
          v-model="queryParams.addrProv"
          placeholder="请输入省"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="市" prop="addrCity">
        <el-input
          v-model="queryParams.addrCity"
          placeholder="请输入市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区县" prop="addrDist">
        <el-input
          v-model="queryParams.addrDist"
          placeholder="请输入区县"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细地址" prop="addrDetail">
        <el-input
          v-model="queryParams.addrDetail"
          placeholder="请输入详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否默认地址" prop="isDefault">
        <el-input
          v-model="queryParams.isDefault"
          placeholder="请输入是否默认地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdAt">
        <el-date-picker clearable
          v-model="queryParams.createdAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedAt">
        <el-date-picker clearable
          v-model="queryParams.updatedAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['business:addr:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:addr:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:addr:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:addr:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="addrList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="地址ID" align="center" prop="addrId" />
      <el-table-column label="所属用户" align="center" prop="clientId" />
      <el-table-column label="姓名" align="center" prop="addrName" />
      <el-table-column label="电话" align="center" prop="addrTel" />
      <el-table-column label="省" align="center" prop="addrProv" />
      <el-table-column label="市" align="center" prop="addrCity" />
      <el-table-column label="区县" align="center" prop="addrDist" />
      <el-table-column label="详细地址" align="center" prop="addrDetail" />
      <el-table-column label="是否默认地址" align="center" prop="isDefault" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:addr:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:addr:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户地址簿对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="addrName">
          <el-input v-model="form.addrName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="电话" prop="addrTel">
          <el-input v-model="form.addrTel" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="省" prop="addrProv">
          <el-input v-model="form.addrProv" placeholder="请输入省" />
        </el-form-item>
        <el-form-item label="市" prop="addrCity">
          <el-input v-model="form.addrCity" placeholder="请输入市" />
        </el-form-item>
        <el-form-item label="区县" prop="addrDist">
          <el-input v-model="form.addrDist" placeholder="请输入区县" />
        </el-form-item>
        <el-form-item label="详细地址" prop="addrDetail">
          <el-input v-model="form.addrDetail" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="是否默认地址" prop="isDefault">
          <el-input v-model="form.isDefault" placeholder="请输入是否默认地址" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdAt">
          <el-date-picker clearable
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedAt">
          <el-date-picker clearable
            v-model="form.updatedAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAddr, getAddr, delAddr, addAddr, updateAddr } from "@/api/business/addr"

export default {
  name: "Addr",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户地址簿表格数据
      addrList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clientId: null,
        addrName: null,
        addrTel: null,
        addrProv: null,
        addrCity: null,
        addrDist: null,
        addrDetail: null,
        isDefault: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        clientId: [
          { required: true, message: "所属用户(biz_client)不能为空", trigger: "blur" }
        ],
        addrName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        addrTel: [
          { required: true, message: "电话不能为空", trigger: "blur" }
        ],
        addrProv: [
          { required: true, message: "省不能为空", trigger: "blur" }
        ],
        addrCity: [
          { required: true, message: "市不能为空", trigger: "blur" }
        ],
        addrDist: [
          { required: true, message: "区县不能为空", trigger: "blur" }
        ],
        addrDetail: [
          { required: true, message: "详细地址不能为空", trigger: "blur" }
        ],
        isDefault: [
          { required: true, message: "是否默认地址不能为空", trigger: "blur" }
        ],
        createdAt: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updatedAt: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询用户地址簿列表 */
    getList() {
      this.loading = true
      listAddr(this.queryParams).then(response => {
        this.addrList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        addrId: null,
        clientId: null,
        addrName: null,
        addrTel: null,
        addrProv: null,
        addrCity: null,
        addrDist: null,
        addrDetail: null,
        isDefault: null,
        createdAt: null,
        updatedAt: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.addrId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加用户地址簿"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const addrId = row.addrId || this.ids
      getAddr(addrId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改用户地址簿"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.addrId != null) {
            updateAddr(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAddr(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const addrIds = row.addrId || this.ids
      this.$modal.confirm('是否确认删除用户地址簿编号为"' + addrIds + '"的数据项？').then(function() {
        return delAddr(addrIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/addr/export', {
        ...this.queryParams
      }, `addr_${new Date().getTime()}.xlsx`)
    },
    // 检查是否为超级管理员
    isSuperAdmin() {
      // 获取当前用户角色信息
      const userRoles = this.$store.state.user.roles;
      // 超级管理员roleID为1
      return userRoles.some(role => role.roleId === 1);
    },
    /** 修改状态按钮操作 */
    handleUpdateStatus(row) {
      const addrIds = row.addrId || this.ids;
      this.$modal.confirm('请确认是否修改地址簿编号为"' + addrIds + '"的状态？').then(function() {
        // 这里需要后端提供修改状态的API
        // updateAddrStatus(addrIds).then(response => {
        //   this.$modal.msgSuccess("状态修改成功");
        //   this.getList();
        // });
      }).catch(() => {});
    }
  }
}
</script>
