<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学号" prop="leaveStudent">
        <el-input
          v-model="queryParams.leaveStudent"
          placeholder="请输入学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="leaveName">
        <el-input
          v-model="queryParams.leaveName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="请假类型" prop="leaveType">
        <el-select v-model="queryParams.leaveType" placeholder="请选择请假类型" clearable>
          <el-option
            v-for="dict in dict.type.stu_leave_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="请假原因" prop="leaveReason">
        <el-input
          v-model="queryParams.leaveReason"
          placeholder="请输入请假原因"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始日期" prop="leaveBegin">
        <el-date-picker clearable
          v-model="queryParams.leaveBegin"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期" prop="leaveEnd">
        <el-date-picker clearable
          v-model="queryParams.leaveEnd"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核结果" prop="leaveResult">
        <el-select v-model="queryParams.leaveResult" placeholder="请选择审核结果" clearable>
          <el-option
            v-for="dict in dict.type.stu_audit_result"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核员ID" prop="leaveAuditor">
        <el-input
          v-model="queryParams.leaveAuditor"
          placeholder="请输入审核员ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:leave:add']"
        >我要请假</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="handleBatchAudit"
          v-hasPermi="['system:leave:edit']"
        >批量审核</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:leave:remove']"
        >批量删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:leave:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="leaveList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="请假ID" align="center" prop="leaveId" />
      <el-table-column label="学号" align="center" prop="leaveStudent" />
      <el-table-column label="姓名" align="center" prop="leaveName" />
      <el-table-column label="请假类型" align="center" prop="leaveType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.stu_leave_type" :value="scope.row.leaveType"/>
        </template>
      </el-table-column>
      <el-table-column label="请假原因" align="center" prop="leaveReason" />
      <el-table-column label="开始日期" align="center" prop="leaveBegin" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.leaveBegin, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束日期" align="center" prop="leaveEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.leaveEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核结果" align="center" prop="leaveResult">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.stu_audit_result" :value="scope.row.leaveResult"/>
        </template>
      </el-table-column>
      <el-table-column label="审核员" align="center" prop="leaveAuditorName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleAudit(scope.row)"
            v-hasPermi="['system:leave:edit']"
          >审核</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:leave:remove']"
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

    <!-- 学生新增请假对话框 -->
    <el-dialog :title="title" :visible.sync="addOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学号" prop="leaveStudent">
          <el-input v-model="form.leaveStudent" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="请假类型" prop="leaveType">
          <el-select v-model="form.leaveType" placeholder="请选择请假类型">
            <el-option
              v-for="dict in dict.type.stu_leave_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="请假原因" prop="leaveReason">
          <el-input v-model="form.leaveReason" placeholder="请输入请假原因" />
        </el-form-item>
        <el-form-item label="开始日期" prop="leaveBegin">
          <el-date-picker clearable
            v-model="form.leaveBegin"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期" prop="leaveEnd">
          <el-date-picker clearable
            v-model="form.leaveEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结束日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 审核请假对话框 -->
    <el-dialog :title="title" :visible.sync="auditOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="审核结果" prop="leaveResult">
          <el-select v-model="form.leaveResult" placeholder="请选择审核结果">
            <el-option
              v-for="dict in dict.type.stu_audit_result"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核员ID" prop="leaveAuditor">
          <el-input v-model="form.leaveAuditor" placeholder="请输入审核员ID" />
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
import { listLeave, getLeave, delLeave, addLeave, updateLeave } from "@/api/system/leave"

export default {
  name: "Leave",
  dicts: ['stu_leave_type', 'stu_audit_result'],
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
      // 学生请假表格数据
      leaveList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 控制新增弹窗
      addOpen: false,
      // 控制审核弹窗
      auditOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        leaveStudent: null,
        leaveName: null,
        leaveType: null,
        leaveReason: null,
        leaveBegin: null,
        leaveEnd: null,
        leaveResult: null,
        leaveAuditor: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        leaveStudent: [
          { required: true, message: "学号不能为空", trigger: "blur" }
        ],
        leaveType: [
          { required: true, message: "请假类型不能为空", trigger: "change" }
        ],
        leaveBegin: [
          { required: true, message: "开始日期不能为空", trigger: "blur" }
        ],
        leaveEnd: [
          { required: true, message: "结束日期不能为空", trigger: "blur" }
        ],
        leaveAuditor: [
          { required: true, message: "审核员不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询学生请假列表 */
    getList() {
      this.loading = true
      listLeave(this.queryParams).then(response => {
        this.leaveList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.addOpen = false
      this.auditOpen = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        leaveId: null,
        leaveStudent: null,
        leaveType: null,
        leaveReason: null,
        leaveBegin: null,
        leaveEnd: null,
        leaveResult: null,
        leaveAuditor: null
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
      this.ids = selection.map(item => item.leaveId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.addOpen = true
      this.title = "添加学生请假"
    },
    /** 审核按钮操作 */
    handleAudit(row) {
      this.reset()
      const leaveId = row.leaveId || this.ids
      getLeave(leaveId).then(response => {
        this.form = response.data
        this.auditOpen = true
        this.title = "审核学生请假"
      })
    },
    /** 批量审核按钮操作 */
    handleBatchAudit(row) {
      this.reset()
      const leaveIds = this.ids
      leaveIds.forEach(leaveId => {
        getLeave(leaveId).then(response => {
           this.form = response.data;
           this.auditOpen = true;
           this.title = "审核学生 "+leaveId+" 请假";
        });
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.leaveId != null) {
            updateLeave(this.form).then(response => {
              this.$modal.msgSuccess("审核成功")
              //this.open = false
              this.auditOpen = false
              this.getList()
            })
          } else {
            addLeave(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              //this.open = false
              this.addOpen = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const leaveIds = row.leaveId || this.ids
      this.$modal.confirm('是否确认删除学生请假编号为"' + leaveIds + '"的数据项？').then(function() {
        return delLeave(leaveIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/leave/export', {
        ...this.queryParams
      }, `leave_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
