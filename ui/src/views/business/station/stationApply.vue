<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="驿站名称" prop="stationName">
        <el-input
          v-model="queryParams.stationName"
          placeholder="请输入驿站名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请状态" prop="applyStatus">
        <el-select v-model="queryParams.applyStatus" placeholder="请选择申请状态" clearable>
          <el-option label="待审核" value="待审核"/>
          <el-option label="审核通过" value="审核通过"/>
          <el-option label="审核不通过" value="审核不通过"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请ID" align="center" prop="applyId" width="80" />
      <el-table-column label="驿站名称" align="center" prop="stationName" width="150"/>
      <el-table-column label="省市区" align="center" :show-overflow-tooltip="true" width="180">
        <template slot-scope="scope">
          {{ scope.row.stationProv }} {{ scope.row.stationCity }} {{ scope.row.stationDist }}
        </template>
      </el-table-column>
      <el-table-column label="详细地址" align="center" prop="stationAddr" :show-overflow-tooltip="true" />
      <el-table-column label="联系电话" align="center" prop="tel" width="120"/>
      <el-table-column label="申请状态" align="center" prop="applyStatus" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.applyStatus === '待审核'" type="warning">待审核</el-tag>
          <el-tag v-else-if="scope.row.applyStatus === '审核通过'" type="success">审核通过</el-tag>
          <el-tag v-else type="danger">审核不通过</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="申请时间" align="center" prop="createdAt" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="100">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.applyStatus === '待审核'"
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleAudit(scope.row)"
            v-hasPermi="['business:stationApply:audit']"
          >审核</el-button>
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

    <!-- 审核对话框 -->
    <el-dialog title="驿站申请审核" :visible.sync="auditOpen" width="500px" append-to-body>
      <el-form ref="auditForm" :model="auditForm" :rules="auditRules" label-width="100px">
        <el-form-item label="驿站名称">
          <el-input v-model="auditForm.stationName" :disabled="true" />
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="auditForm.fullAddress" type="textarea" :disabled="true" />
        </el-form-item>
        <el-form-item label="选择部门" prop="parentDeptId">
          <treeselect
            v-model="auditForm.parentDeptId"
            :options="deptOptions"
            :normalizer="normalizerDept"
            placeholder="请选择上级部门（审核通过时必选）"
          />
        </el-form-item>
        <el-form-item label="审核备注" prop="auditRemark">
          <el-input v-model="auditForm.auditRemark" type="textarea" placeholder="请输入审核备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="auditOpen = false">取 消</el-button>
        <el-button type="danger" @click="submitAudit(false)">拒 绝</el-button>
        <el-button type="primary" @click="submitAudit(true)">通 过</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStationApply, auditStationApply } from "@/api/business/station";
import { listDept } from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "StationApply",
  components: { Treeselect },
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      applyList: [],
      auditOpen: false,
      auditForm: {},
      deptOptions: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stationName: null,
        applyStatus: null
      },
      auditRules: {
        parentDeptId: [
          { required: false, message: "审核通过时必须选择部门", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDeptList();
  },
  methods: {
    /** 查询驿站申请列表 */
    getList() {
      this.loading = true;
      listStationApply(this.queryParams).then(response => {
        this.applyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.applyId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 获取部门列表 */
    getDeptList() {
      listDept().then(response => {
        this.deptOptions = this.handleTree(response.data, "deptId");
      });
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
        applyId: row.applyId,
        stationName: row.stationName,
        fullAddress: `${row.stationProv} ${row.stationCity} ${row.stationDist} ${row.stationAddr}`,
        parentDeptId: null,
        auditRemark: ""
      };
      this.auditOpen = true;
    },
    /** 提交审核 */
    submitAudit(pass) {
      // 如果通过审核，必须选择部门
      if (pass && !this.auditForm.parentDeptId) {
        this.$modal.msgError("审核通过时必须选择上级部门");
        return;
      }
      
      const params = {
        pass: pass,
        parentDeptId: this.auditForm.parentDeptId,
        auditRemark: this.auditForm.auditRemark
      };
      
      auditStationApply(this.auditForm.applyId, params).then(response => {
        this.$modal.msgSuccess("审核成功");
        this.auditOpen = false;
        this.getList();
      });
    }
  }
};
</script>
