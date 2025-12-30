<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="studentSex">
      <el-select
        v-model="queryParams.studentSex"
        placeholder="请选择性别"
        clearable
        style="width: 240px">
        <el-option v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.label" :value="dict.value" />
      </el-select>
    </el-form-item>
      <el-form-item label="生日" prop="studentBirth">
        <el-date-picker clearable
          v-model="queryParams.studentBirth"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择生日">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="电话" prop="studentPhone">
        <el-input
          v-model="queryParams.studentPhone"
          placeholder="请输入电话"
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
          v-hasPermi="['system:student:add']"
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
          v-hasPermi="['system:student:edit']"
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
          v-hasPermi="['system:student:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:student:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-leave"
          size="mini"
          @click="handleLeave"
          v-hasPermi="['system:leave:list']"
        >请假</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学号" align="center" prop="studentId" />
      <el-table-column label="姓名" align="center" prop="studentName" />
      <el-table-column label="性别" align="center">
        <template slot-scope="scope">
          <span>{{ dict.label.sys_user_sex[scope.row.studentSex] }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center">
        <template slot-scope="scope">
          <span>{{ getAge(scope.row.studentBirth) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生日" align="center" prop="studentBirth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.studentBirth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电话" align="center" prop="studentPhone" />
      <el-table-column label="更新时间" align="center" prop="studentUpdate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.studentUpdate,'{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" key="studentStatus" >
        <template slot-scope="scope">
          <el-switch v-model="scope.row.studentStatus" active-value="0" inactive-value="1" @change="handleStatusChange(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:student:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:student:remove']"
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

    <!-- 添加或修改学生信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="studentSex">
          <el-radio-group v-model="form.studentSex" >
            <el-radio v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.value"
                      :disabled="dict.disabled">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日" prop="studentBirth">
          <el-date-picker clearable
            v-model="form.studentBirth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择生日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="电话" prop="studentPhone">
          <el-input v-model="form.studentPhone" placeholder="请输入电话" />
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
import {listStudent, getStudent, delStudent, addStudent, updateStudent, changeStudentStatus} from "@/api/system/student"

export default {
  name: "Student",
  dicts: ['sys_normal_disable', 'sys_user_sex'],
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
      // 学生信息表格数据
      studentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentName: null,
        studentSex: null,
        studentBirth: null,
        studentPhone: null,
        studentUpdate: null,
        studentStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        studentBirth: [
          { required: true, message: "生日不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getAge(birthday) {
      if (!birthday)
        return '';
      const birth = new Date(birthday);
      const today = new Date();
      let age = today.getFullYear() - birth.getFullYear();
      const m = today.getMonth() - birth.getMonth();
      if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) {
        age--;
      }
      return age;
    },
    // 学生状态修改
    handleStatusChange(row) {
      let text = row.studentStatus === "0" ? "启用" : "停用"
      this.$modal.confirm('确认要"' + text + '""' + row.studentName + '"学生吗？').then(function() {
        return changeStudentStatus(row.studentId, row.studentStatus)
      }).then(() => {
        this.$modal.msgSuccess(text + "成功")
      }).catch(function() {
        row.studentStatus = row.studentStatus === "0" ? "1" : "0"
      })
    },
    /** 查询学生信息列表 */
    getList() {
      this.loading = true
      listStudent(this.queryParams).then(response => {
        this.studentList = response.rows
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
        studentId: null,
        studentName: null,
        studentSex: null,
        studentBirth: null,
        studentPhone: null,
        studentUpdate: null,
        studentStatus: null
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
      this.ids = selection.map(item => item.studentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加学生信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const studentId = row.studentId || this.ids
      getStudent(studentId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改学生信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.studentId != null) {
            updateStudent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addStudent(this.form).then(response => {
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
      const studentIds = row.studentId || this.ids
      this.$modal.confirm('是否确认删除学生信息编号为"' + studentIds + '"的数据项？').then(function() {
        return delStudent(studentIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/student/export', {
        ...this.queryParams
      }, `student_${new Date().getTime()}.xlsx`)
    },
    /** 请假按钮操作 */
    handleLeave() {
      this.$router.push('leave');
    }

  }
}
</script>
