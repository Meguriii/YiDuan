<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="部门ID" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入部门ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省" prop="stationProv">
        <el-input
          v-model="queryParams.stationProv"
          placeholder="请输入省"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="市" prop="stationCity">
        <el-input
          v-model="queryParams.stationCity"
          placeholder="请输入市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区县" prop="stationDist">
        <el-input
          v-model="queryParams.stationDist"
          placeholder="请输入区县"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细地址" prop="stationAddr">
        <el-input
          v-model="queryParams.stationAddr"
          placeholder="请输入详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="驿站名称" prop="stationName">
        <el-input
          v-model="queryParams.stationName"
          placeholder="请输入驿站名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人ID" prop="contactUserId">
        <el-input
          v-model="queryParams.contactUserId"
          placeholder="请输入负责人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="tel">
        <el-input
          v-model="queryParams.tel"
          placeholder="请输入联系电话"
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
          v-hasPermi="['business:station:add']"
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
          v-hasPermi="['business:station:edit']"
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
          v-hasPermi="['business:station:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:station:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="驿站ID" align="center" prop="stationId" />
<!--      <el-table-column label="部门ID" align="center" prop="deptId" />-->
      <el-table-column label="省" align="center" prop="stationProv" />
      <el-table-column label="市" align="center" prop="stationCity" />
      <el-table-column label="区县" align="center" prop="stationDist" />
      <el-table-column label="详细地址" align="center" prop="stationAddr" />
      <el-table-column label="驿站名称" align="center" prop="stationName" />
<!--      <el-table-column label="负责人ID" align="center" prop="contactUserId" />-->
      <el-table-column label="负责人姓名" align="center" prop="contactUserName" />
      <el-table-column label="联系电话" align="center" prop="tel" />
      <el-table-column label="驿站状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === '待审核'" type="warning">待审核</el-tag>
          <el-tag v-else-if="scope.row.status === '正常'" type="success">正常</el-tag>
          <el-tag v-else type="danger">停用</el-tag>
        </template>
      </el-table-column>
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status === '待审核'"
            size="mini"
            type="success"
            icon="el-icon-check"
            @click="handleAudit(scope.row)"
            v-hasPermi="['business:station:audit']"
          >审核</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:station:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:station:remove']"
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

    <!-- 添加或修改驿站信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="部门ID" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门ID" />
        </el-form-item>
        <el-form-item label="省" prop="stationProv">
          <el-input v-model="form.stationProv" placeholder="请输入省" />
        </el-form-item>
        <el-form-item label="市" prop="stationCity">
          <el-input v-model="form.stationCity" placeholder="请输入市" />
        </el-form-item>
        <el-form-item label="区县" prop="stationDist">
          <el-input v-model="form.stationDist" placeholder="请输入区县" />
        </el-form-item>
        <el-form-item label="详细地址" prop="stationAddr">
          <el-input v-model="form.stationAddr" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="驿站名称" prop="stationName">
          <el-input v-model="form.stationName" placeholder="请输入驿站名称" />
        </el-form-item>
        <el-form-item label="负责人ID" prop="contactUserId">
          <el-input v-model="form.contactUserId" placeholder="请输入负责人ID" />
        </el-form-item>
        <el-form-item label="联系电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入联系电话" />
        </el-form-item>
<!--        <el-form-item label="状态" prop="status">-->
<!--          <el-switch v-model="form.status" :active-value='1' :inactive-value='0'></el-switch>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="创建时间" prop="createdAt">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.createdAt"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择创建时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="更新时间" prop="updatedAt">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.updatedAt"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择更新时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 审核对话框 -->
    <el-dialog title="驿站审核" :visible.sync="auditOpen" width="500px" append-to-body>
      <el-form ref="auditForm" :model="auditForm" :rules="auditRules" label-width="100px">
        <el-form-item label="驿站名称">
          <el-input v-model="auditForm.stationName" :disabled="true" />
        </el-form-item>
        <el-form-item label="驿站地址">
          <el-input :value="`${auditForm.stationProv||''}${auditForm.stationCity||''}${auditForm.stationDist||''}${auditForm.stationAddr||''}`" :disabled="true" />
        </el-form-item>
        <el-form-item label="选择部门" prop="parentDeptId">
          <treeselect
            v-model="auditForm.parentDeptId"
            :options="deptOptions"
            :normalizer="normalizerDept"
            placeholder="请选择上级部门"
          />
        </el-form-item>
        <el-form-item label="审核状态" prop="status">
          <el-radio-group v-model="auditForm.status">
            <el-radio label="正常">通过</el-radio>
            <el-radio label="停用">不通过</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitAudit">确 定</el-button>
        <el-button @click="auditOpen = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStation, getStation, delStation, addStation, updateStation } from "@/api/business/station"
import { listDept } from "@/api/system/dept"
import Treeselect from "@riophae/vue-treeselect"
import "@riophae/vue-treeselect/dist/vue-treeselect.css"

export default {
  name: "Station",
  components: { Treeselect },
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
      // 驿站信息表格数据
      stationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 审核对话框
      auditOpen: false,
      // 审核表单
      auditForm: {},
      // 部门树选项
      deptOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        stationProv: null,
        stationCity: null,
        stationDist: null,
        stationAddr: null,
        stationName: null,
        contactUserId: null,
        status: null,
        tel: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 联系人姓名
      contactUserName: '',
      // 表单校验
      rules: {
        deptId: [
          { required: true, message: "部门ID不能为空", trigger: "blur" }
        ],
        stationProv: [
          { required: true, message: "省不能为空", trigger: "blur" }
        ],
        stationCity: [
          { required: true, message: "市不能为空", trigger: "blur" }
        ],
        stationDist: [
          { required: true, message: "区县不能为空", trigger: "blur" }
        ],
        stationAddr: [
          { required: true, message: "详细地址不能为空", trigger: "blur" }
        ],
        stationName: [
          { required: true, message: "驿站名称不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "驿站状态不能为空", trigger: "change" }
        ],
        createdAt: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updatedAt: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      },
      // 审核表单校验
      auditRules: {
        parentDeptId: [
          { required: true, message: "请选择部门", trigger: "change" }
        ],
        status: [
          { required: true, message: "请选择审核状态", trigger: "change" }
        ]
      },
      watch: {
        'form.contactUserId': function(newVal, oldVal) {
          if (newVal !== oldVal) {
            this.updateContactUserName();
          }
        }
      }
    }
  },
  created() {
    this.getList()
    this.getDeptList()
  },
  methods: {
    /** 查询驿站信息列表 */
    getList() {
      this.loading = true
      listStation(this.queryParams).then(response => {
        this.stationList = response.rows
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
        stationId: null,
        deptId: null,
        stationProv: null,
        stationCity: null,
        stationDist: null,
        stationAddr: null,
        stationName: null,
        contactUserId: null,
        status: null,
        tel: null,
        createdAt: null,
        updatedAt: null
      }
      this.contactUserName = '';
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
      this.ids = selection.map(item => item.stationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加驿站信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const stationId = row.stationId || this.ids
      getStation(stationId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改驿站信息"
        this.updateContactUserName()
      })
    },
    /** 更新联系人姓名 */
    updateContactUserName() {
      if (this.form.contactUserId) {
        this.getUserInfo(this.form.contactUserId).then(userName => {
          this.contactUserName = userName || '未找到用户';
        }).catch(() => {
          this.contactUserName = '获取失败';
        });
      } else {
        this.contactUserName = '';
      }
    },
    /** 获取用户信息 */
    getUserInfo(userId) {
      return new Promise((resolve, reject) => {
        if (!userId) {
          resolve('');
          return;
        }
        import('@/api/system/user').then(module => {
          module.getUser(userId).then(response => {
            if (response && response.data && response.data.nickName) {
              resolve(response.data.nickName);
            } else if (response && response.data && response.data.userName) {
              resolve(response.data.userName);
            } else {
              resolve('');
            }
          }).catch(error => {
            reject(error);
          });
        }).catch(error => {
          reject(error);
        });
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.stationId != null) {
            updateStation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addStation(this.form).then(response => {
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
      const stationIds = row.stationId || this.ids
      this.$modal.confirm('是否确认删除驿站信息编号为"' + stationIds + '"的数据项？').then(function() {
        return delStation(stationIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/station/export', {
        ...this.queryParams
      }, `station_${new Date().getTime()}.xlsx`)
    },
    /** 获取部门列表 */
    getDeptList() {
      listDept().then(response => {
        this.deptOptions = this.handleTree(response.data, "deptId")
      })
    },
    /** 转换部门数据结构 */
    normalizerDept(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.deptId,
        label: node.deptName,
        children: node.children
      };
    },
    /** 审核按钮操作 */
    handleAudit(row) {
      this.auditForm = {
        stationId: row.stationId,
        stationName: row.stationName,
        stationProv: row.stationProv,
        stationCity: row.stationCity,
        stationDist: row.stationDist,
        stationAddr: row.stationAddr,
        parentDeptId: null,
        status: "正常"
      }
      this.auditOpen = true
    },
    /** 提交审核 */
    submitAudit() {
      this.$refs["auditForm"].validate(valid => {
        if (valid) {
          const params = {
            status: this.auditForm.status,
            parentDeptId: this.auditForm.parentDeptId
          }
          auditStation(this.auditForm.stationId, params).then(response => {
            this.$modal.msgSuccess("审核成功")
            this.auditOpen = false
            this.getList()
          })
        }
      })
    }
  }
}
</script>
