<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true">
      <el-form-item label="包裹ID" prop="packId">
        <el-input
          v-model="queryParams.packId"
          placeholder="请输入包裹ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="receivePackList">
      <el-table-column label="包裹ID" align="center" prop="packId" />
      <el-table-column label="寄件人" align="center" prop="senderId" />
      <el-table-column label="收件人" align="center" prop="receiverName" />
      <el-table-column label="收件地址" align="center">
        <template slot-scope="scope">
          {{ scope.row.receiverProv }}{{ scope.row.receiverCity }}{{ scope.row.receiverDist }}{{ scope.row.receiverAddrDetail }}
        </template>
      </el-table-column>
      <el-table-column label="是否易碎" align="center" prop="isFragile">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isFragile === 1 ? 'danger' : 'info'">
            {{ scope.row.isFragile === 1 ? '是' : '否' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === '到站待核验' ? 'warning' : 'success'">
            {{ scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="300">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status === '到站待核验'"
            size="mini"
            type="success"
            @click="handleVerifyReceive(scope.row)"
          >核验通过</el-button>
          <el-button
            v-if="scope.row.status === '到站待核验'"
            size="mini"
            type="danger"
            @click="handleRejectReceive(scope.row)"
          >核验不通过</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 收货核验对话框 -->
    <el-dialog title="收货核验" :visible.sync="verifyReceiveOpen" width="600px">
      <el-form ref="verifyReceiveForm" :model="verifyReceiveForm" label-width="100px">
        <el-form-item label="包裹ID">
          <el-input v-model="verifyReceiveForm.packId" :disabled="true" />
        </el-form-item>
        <el-form-item label="是否破损">
          <el-switch v-model="verifyReceiveForm.isDamaged" :active-value="1" :inactive-value="0"></el-switch>
        </el-form-item>
        <el-form-item label="包裹状态">
          <el-select v-model="verifyReceiveForm.packCondition" placeholder="请选择包裹状态">
            <el-option label="完好" value="完好"></el-option>
            <el-option label="轻微破损" value="轻微破损"></el-option>
            <el-option label="严重破损" value="严重破损"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="verifyReceiveForm.note" type="textarea" :rows="3" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="verifyReceiveOpen = false">取 消</el-button>
        <el-button type="primary" @click="submitVerifyReceive">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 核验不通过对话框 -->
    <el-dialog title="核验不通过" :visible.sync="rejectOpen" width="500px">
      <el-form ref="rejectForm" :model="rejectForm" label-width="100px">
        <el-form-item label="不通过原因">
          <el-input v-model="rejectForm.reason" type="textarea" :rows="4" placeholder="请输入不通过原因" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="rejectOpen = false">取 消</el-button>
        <el-button type="primary" @click="submitReject">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPack, verifyReceive, rejectReceive } from "@/api/business/pack";

export default {
  name: "ReceiveVerify",
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
      // 总条数
      total: 0,
      // 收货核验表格数据
      receivePackList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      verifyReceiveOpen: false,
      rejectOpen: false,
      // 查询参数
      queryParams: {
        packId: null,
        type: 'receive' // 收货核验
      },
      // 单参数
      verifyReceiveForm: {},
      rejectForm: {},
      currentRow: null
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询收货核验列表 */
    getList() {
      this.loading = true;
      this.loading = true;
      listPack({ ...this.queryParams, status: '到站待核验' }).then(response => {
        this.receivePackList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.verifyReceiveForm = {};
      this.rejectForm = {};
      this.currentRow = null;
      this.$refs['verifyReceiveForm'] && this.$refs['verifyReceiveForm'].resetFields();
      this.$refs['rejectForm'] && this.$refs['rejectForm'].resetFields();
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 收货核验通过按钮操作 */
    handleVerifyReceive(row) {
      this.currentRow = row;
      this.verifyReceiveForm = Object.assign({}, row);
      this.verifyReceiveOpen = true;
    },
    /** 提交收货核验 */
    submitVerifyReceive() {
      this.$refs["verifyReceiveForm"].validate(valid => {
        if (valid) {
          verifyReceive(this.verifyReceiveForm.packId, this.verifyReceiveForm).then(response => {
            this.$modal.msgSuccess("核验成功");
            this.verifyReceiveOpen = false;
            this.getList();
          });
        }
      });
    },
    /** 收货核验不通过按钮操作 */
    handleRejectReceive(row) {
      this.currentRow = row;
      this.rejectForm.packId = row.packId;
      this.rejectOpen = true;
    },
    /** 提交核验不通过 */
    submitReject() {
      this.$refs["rejectForm"].validate(valid => {
        if (valid) {
          rejectReceive(this.rejectForm.packId, this.rejectForm).then(response => {
            this.$modal.msgSuccess("已标记为核验不通过");
            this.rejectOpen = false;
            this.getList();
          });
        }
      });
    }
  }
};
</script>