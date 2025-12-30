<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="寄件省" prop="senderProv">
        <el-input
          v-model="queryParams.senderProv"
          placeholder="请输入寄件省"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="寄件市" prop="senderCity">
        <el-input
          v-model="queryParams.senderCity"
          placeholder="请输入寄件市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="寄件区县" prop="senderDist">
        <el-input
          v-model="queryParams.senderDist"
          placeholder="请输入寄件区县"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="寄件详细地址" prop="senderAddrDetail">
        <el-input
          v-model="queryParams.senderAddrDetail"
          placeholder="请输入寄件详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件省" prop="receiverProv">
        <el-input
          v-model="queryParams.receiverProv"
          placeholder="请输入收件省"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件市" prop="receiverCity">
        <el-input
          v-model="queryParams.receiverCity"
          placeholder="请输入收件市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件区县" prop="receiverDist">
        <el-input
          v-model="queryParams.receiverDist"
          placeholder="请输入收件区县"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件详细地址" prop="receiverAddrDetail">
        <el-input
          v-model="queryParams.receiverAddrDetail"
          placeholder="请输入收件详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人姓名" prop="receiverName">
        <el-input
          v-model="queryParams.receiverName"
          placeholder="请输入收件人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人手机号" prop="receiverTel">
        <el-input
          v-model="queryParams.receiverTel"
          placeholder="请输入收件人手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="重量" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="体积" prop="volume">
        <el-input
          v-model="queryParams.volume"
          placeholder="请输入体积"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否危险品" prop="isDangerous">
        <el-input
          v-model="queryParams.isDangerous"
          placeholder="请输入是否危险品"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否易碎" prop="isFragile">
        <el-input
          v-model="queryParams.isFragile"
          placeholder="请输入是否易碎"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否加急" prop="isUrgent">
        <el-input
          v-model="queryParams.isUrgent"
          placeholder="请输入是否加急"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="取件码" prop="pickupCode">
        <el-input
          v-model="queryParams.pickupCode"
          placeholder="请输入取件码"
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
          v-hasPermi="['business:pack:add']"
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
          v-hasPermi="['business:pack:edit']"
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
          v-hasPermi="['business:pack:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:pack:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="packList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="包裹ID" align="center" prop="packId" />
      <el-table-column label="寄件人ID" align="center" prop="senderId" />
      <el-table-column label="寄件省" align="center" prop="senderProv" />
      <el-table-column label="寄件市" align="center" prop="senderCity" />
      <el-table-column label="寄件区县" align="center" prop="senderDist" />
      <el-table-column label="寄件详细地址" align="center" prop="senderAddrDetail" />
      <el-table-column label="收件人ID" align="center" prop="receiverId" />
      <el-table-column label="收件人姓名" align="center" prop="receiverName" />
      <el-table-column label="收件人手机号" align="center" prop="receiverTel" />
      <el-table-column label="收件省" align="center" prop="receiverProv" />
      <el-table-column label="收件市" align="center" prop="receiverCity" />
      <el-table-column label="收件区县" align="center" prop="receiverDist" />
      <el-table-column label="收件详细地址" align="center" prop="receiverAddrDetail" />
      <el-table-column label="重量" align="center" prop="weight" />
      <el-table-column label="体积" align="center" prop="volume" />
      <el-table-column label="备注" align="center" prop="note" />
      <el-table-column label="是否危险品" align="center" prop="isDangerous" />
      <el-table-column label="是否易碎" align="center" prop="isFragile" />
      <el-table-column label="是否加急" align="center" prop="isUrgent" />
      <el-table-column label="包裹状态" align="center" prop="status" />
      <el-table-column label="取件码" align="center" prop="pickupCode" />
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
            v-hasPermi="['business:pack:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:pack:remove']"
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

    <!-- 添加或修改包裹表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="寄件省" prop="senderProv">
          <el-input v-model="form.senderProv" placeholder="请输入寄件省" />
        </el-form-item>
        <el-form-item label="寄件市" prop="senderCity">
          <el-input v-model="form.senderCity" placeholder="请输入寄件市" />
        </el-form-item>
        <el-form-item label="寄件区县" prop="senderDist">
          <el-input v-model="form.senderDist" placeholder="请输入寄件区县" />
        </el-form-item>
        <el-form-item label="寄件详细地址" prop="senderAddrDetail">
          <el-input v-model="form.senderAddrDetail" placeholder="请输入寄件详细地址" />
        </el-form-item>
        <el-form-item label="收件人姓名" prop="receiverName">
          <el-input v-model="form.receiverName" placeholder="请输入收件人姓名" />
        </el-form-item>
        <el-form-item label="收件人手机号" prop="receiverTel">
          <el-input v-model="form.receiverTel" placeholder="请输入收件人手机号" />
        </el-form-item>
        <el-form-item label="收件省" prop="receiverProv">
          <el-input v-model="form.receiverProv" placeholder="请输入收件省" />
        </el-form-item>
        <el-form-item label="收件市" prop="receiverCity">
          <el-input v-model="form.receiverCity" placeholder="请输入收件市" />
        </el-form-item>
        <el-form-item label="收件区县" prop="receiverDist">
          <el-input v-model="form.receiverDist" placeholder="请输入收件区县" />
        </el-form-item>
        <el-form-item label="收件详细地址" prop="receiverAddrDetail">
          <el-input v-model="form.receiverAddrDetail" placeholder="请输入收件详细地址" />
        </el-form-item>
        <el-form-item label="重量" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入重量" />
        </el-form-item>
        <el-form-item label="体积" prop="volume">
          <el-input v-model="form.volume" placeholder="请输入体积" />
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="form.note" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否危险品" prop="isDangerous">
          <el-input v-model="form.isDangerous" placeholder="请输入是否危险品" />
        </el-form-item>
        <el-form-item label="是否易碎" prop="isFragile">
          <el-input v-model="form.isFragile" placeholder="请输入是否易碎" />
        </el-form-item>
        <el-form-item label="是否加急" prop="isUrgent">
          <el-input v-model="form.isUrgent" placeholder="请输入是否加急" />
        </el-form-item>
        <el-form-item label="取件码" prop="pickupCode">
          <el-input v-model="form.pickupCode" placeholder="请输入取件码" />
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
import { listPack, getPack, delPack, addPack, updatePack } from "@/api/business/pack"

export default {
  name: "Pack",
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
      // 包裹表表格数据
      packList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        senderId: null,
        senderProv: null,
        senderCity: null,
        senderDist: null,
        senderAddrDetail: null,
        receiverId: null,
        receiverName: null,
        receiverTel: null,
        receiverProv: null,
        receiverCity: null,
        receiverDist: null,
        receiverAddrDetail: null,
        weight: null,
        volume: null,
        note: null,
        isDangerous: null,
        isFragile: null,
        isUrgent: null,
        status: null,
        pickupCode: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        senderId: [
          { required: true, message: "寄件用户(biz_client)不能为空", trigger: "blur" }
        ],
        senderProv: [
          { required: true, message: "寄件省不能为空", trigger: "blur" }
        ],
        senderCity: [
          { required: true, message: "寄件市不能为空", trigger: "blur" }
        ],
        senderDist: [
          { required: true, message: "寄件区县不能为空", trigger: "blur" }
        ],
        senderAddrDetail: [
          { required: true, message: "寄件详细地址不能为空", trigger: "blur" }
        ],
        receiverName: [
          { required: true, message: "收件人姓名不能为空", trigger: "blur" }
        ],
        receiverTel: [
          { required: true, message: "收件人手机号不能为空", trigger: "blur" }
        ],
        receiverProv: [
          { required: true, message: "收件省不能为空", trigger: "blur" }
        ],
        receiverCity: [
          { required: true, message: "收件市不能为空", trigger: "blur" }
        ],
        receiverDist: [
          { required: true, message: "收件区县不能为空", trigger: "blur" }
        ],
        receiverAddrDetail: [
          { required: true, message: "收件详细地址不能为空", trigger: "blur" }
        ],
        isDangerous: [
          { required: true, message: "是否危险品不能为空", trigger: "blur" }
        ],
        isFragile: [
          { required: true, message: "是否易碎不能为空", trigger: "blur" }
        ],
        isUrgent: [
          { required: true, message: "是否加急不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "包裹状态不能为空", trigger: "change" }
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
    /** 查询包裹表列表 */
    getList() {
      this.loading = true
      listPack(this.queryParams).then(response => {
        this.packList = response.rows
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
        packId: null,
        senderId: null,
        senderProv: null,
        senderCity: null,
        senderDist: null,
        senderAddrDetail: null,
        receiverId: null,
        receiverName: null,
        receiverTel: null,
        receiverProv: null,
        receiverCity: null,
        receiverDist: null,
        receiverAddrDetail: null,
        weight: null,
        volume: null,
        note: null,
        isDangerous: null,
        isFragile: null,
        isUrgent: null,
        status: null,
        pickupCode: null,
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
      this.ids = selection.map(item => item.packId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加包裹表"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const packId = row.packId || this.ids
      getPack(packId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改包裹表"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.packId != null) {
            updatePack(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addPack(this.form).then(response => {
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
      const packIds = row.packId || this.ids
      this.$modal.confirm('是否确认删除包裹表编号为"' + packIds + '"的数据项？').then(function() {
        return delPack(packIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/pack/export', {
        ...this.queryParams
      }, `pack_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
