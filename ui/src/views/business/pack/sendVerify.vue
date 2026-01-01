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

    <el-table v-loading="loading" :data="sendPackList">
      <el-table-column label="包裹ID" align="center" prop="packId" />
      <el-table-column label="寄件人" align="center" prop="senderId" />
      <el-table-column label="寄件地址" align="center">
        <template slot-scope="scope">
          {{ scope.row.senderProv }}{{ scope.row.senderCity }}{{ scope.row.senderDist }}{{ scope.row.senderAddrDetail }}
        </template>
      </el-table-column>
      <el-table-column label="收件人" align="center" prop="receiverName" />
      <el-table-column label="收件地址" align="center">
        <template slot-scope="scope">
          {{ scope.row.receiverProv }}{{ scope.row.receiverCity }}{{ scope.row.receiverDist }}{{ scope.row.receiverAddrDetail }}
        </template>
      </el-table-column>
      <el-table-column label="重量(kg)" align="center" prop="weight" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === '寄出待核验' ? 'warning' : 'success'">
            {{ scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="300">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status === '寄出待核验'"
            size="mini"
            type="success"
            @click="handleVerifySend(scope.row)"
          >核验通过</el-button>
          <el-button
            v-if="scope.row.status === '寄出待核验'"
            size="mini"
            type="danger"
            @click="handleRejectSend(scope.row)"
          >核验不通过</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 发货核验对话框 -->
    <el-dialog title="发货核验" :visible.sync="verifySendOpen" width="600px">
      <el-form ref="verifySendForm" :model="verifySendForm" label-width="100px">
        <el-form-item label="包裹ID">
          <el-input v-model="verifySendForm.packId" :disabled="true" />
        </el-form-item>
        <el-form-item label="是否危险品">
          <el-switch v-model="verifySendForm.isDangerous" :active-value="1" :inactive-value="0"></el-switch>
        </el-form-item>
        <el-form-item label="是否易碎品">
          <el-switch v-model="verifySendForm.isFragile" :active-value="1" :inactive-value="0"></el-switch>
        </el-form-item>
        <el-form-item label="是否加急">
          <el-switch v-model="verifySendForm.isUrgent" :active-value="1" :inactive-value="0"></el-switch>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="verifySendForm.note" type="textarea" :rows="3" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="verifySendOpen = false">取 消</el-button>
        <el-button type="primary" @click="submitVerifySend">确 定</el-button>
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
import { listPack, verifySend, rejectSend } from "@/api/business/pack";

export default {
  name: "SendVerify",
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
      // 发货核验表格数据
      sendPackList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      verifySendOpen: false,
      rejectOpen: false,
      // 查询参数
      queryParams: {
        packId: null,
        type: 'send' // 发货核验
      },
      // 表单参数
      verifySendForm: {},
      rejectForm: {},
      currentRow: null
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询发货核验列表 */
    getList() {
      this.loading = true;
      this.loading = true;
      listPack({ ...this.queryParams, status: '寄出待核验' }).then(response => {
        this.sendPackList = response.rows;
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
      this.verifySendForm = {};
      this.rejectForm = {};
      this.currentRow = null;
      this.$refs['verifySendForm'] && this.$refs['verifySendForm'].resetFields();
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
    /** 发货核验通过按钮操作 */
    handleVerifySend(row) {
      this.currentRow = row;
      this.verifySendForm = Object.assign({}, row);
      this.verifySendOpen = true;
    },
    /** 提交发货核验 */
    submitVerifySend() {
      this.$refs["verifySendForm"].validate(valid => {
        if (valid) {
          verifySend(this.verifySendForm.packId, this.verifySendForm).then(response => {
            this.$modal.msgSuccess("核验成功");
            this.verifySendOpen = false;
            this.getList();
          });
        }
      });
    },
    /** 发货核验不通过按钮操作 */
    handleRejectSend(row) {
      this.currentRow = row;
      this.rejectForm.packId = row.packId;
      this.rejectOpen = true;
    },
    /** 提交核验不通过 */
    submitReject() {
      this.$refs["rejectForm"].validate(valid => {
        if (valid) {
          rejectSend(this.rejectForm.packId, this.rejectForm).then(response => {
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