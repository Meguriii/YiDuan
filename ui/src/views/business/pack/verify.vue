<template>
  <div class="app-container">
    <el-tabs v-model="activeTab" type="card">
      <!-- 发货核验 -->
      <el-tab-pane label="发货核验" name="send">
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
      </el-tab-pane>

      <!-- 收货核验 -->
      <el-tab-pane label="收货核验" name="receive">
        <el-form :model="queryParams" ref="queryForm2" size="small" :inline="true">
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
      </el-tab-pane>

      <!-- 上架管理 -->
      <el-tab-pane label="上架管理" name="shelve">
        <el-table v-loading="loading" :data="shelvePackList">
          <el-table-column label="包裹ID" align="center" prop="packId" />
          <el-table-column label="收件人" align="center" prop="receiverName" />
          <el-table-column label="收件电话" align="center" prop="receiverTel" />
          <el-table-column label="状态" align="center" prop="status">
            <template slot-scope="scope">
              <el-tag :type="scope.row.status === '入库已核验' ? 'warning' : 'success'">
                {{ scope.row.status }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="取件码" align="center" prop="pickupCode" />
          <el-table-column label="操作" align="center" width="200">
            <template slot-scope="scope">
              <el-button
                v-if="scope.row.status === '入库已核验'"
                size="mini"
                type="primary"
                @click="handleGeneratePickupCode(scope.row)"
              >生成取件码并上架</el-button>
              <span v-else-if="scope.row.pickupCode">
                取件码: <el-tag type="success">{{ scope.row.pickupCode }}</el-tag>
              </span>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>

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
import { listPack, verifySend, rejectSend, verifyReceive, rejectReceive, generatePickupCode } from "@/api/business/pack";

export default {
  name: "PackVerify",
  data() {
    return {
      // 当前标签页
      activeTab: "send",
      // 遮罩层
      loading: false,
      // 查询参数
      queryParams: {
        packId: null,
        status: null,
        pageNum: 1,
        pageSize: 10
      },
      // 发货核验列表
      sendPackList: [],
      // 收货核验列表
      receivePackList: [],
      // 上架管理列表
      shelvePackList: [],
      // 发货核验对话框
      verifySendOpen: false,
      verifySendForm: {},
      // 核验不通过对话框
      rejectOpen: false,
      rejectForm: {
        reason: ""
      },
      currentRejectType: "", // send 或 receive
      currentRejectId: null
    };
  },
  created() {
    this.getList();
  },
  watch: {
    activeTab(val) {
      this.getList();
    }
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      let status = "";
      if (this.activeTab === "send") {
        status = "寄出待核验";
      } else if (this.activeTab === "receive") {
        status = "到站待核验";
      } else if (this.activeTab === "shelve") {
        status = "入库已核验,待取件";
      }
      
      this.queryParams.status = status;
      listPack(this.queryParams).then(response => {
        if (this.activeTab === "send") {
          this.sendPackList = response.rows;
        } else if (this.activeTab === "receive") {
          this.receivePackList = response.rows;
        } else if (this.activeTab === "shelve") {
          this.shelvePackList = response.rows;
        }
        this.loading = false;
      });
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
    /** 发货核验 */
    handleVerifySend(row) {
      this.verifySendForm = Object.assign({}, row);
      this.verifySendOpen = true;
    },
    /** 提交发货核验 */
    submitVerifySend() {
      verifySend(this.verifySendForm.packId, this.verifySendForm).then(response => {
        this.$modal.msgSuccess("核验成功");
        this.verifySendOpen = false;
        this.getList();
      });
    },
    /** 发货核验不通过 */
    handleRejectSend(row) {
      this.currentRejectType = "send";
      this.currentRejectId = row.packId;
      this.rejectForm.reason = "";
      this.rejectOpen = true;
    },
    /** 收货核验 */
    handleVerifyReceive(row) {
      this.$confirm('确认包裹已核验通过?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        return verifyReceive(row.packId, {});
      }).then(() => {
        this.$modal.msgSuccess("核验成功");
        this.getList();
      });
    },
    /** 收货核验不通过 */
    handleRejectReceive(row) {
      this.currentRejectType = "receive";
      this.currentRejectId = row.packId;
      this.rejectForm.reason = "";
      this.rejectOpen = true;
    },
    /** 提交核验不通过 */
    submitReject() {
      if (!this.rejectForm.reason) {
        this.$modal.msgError("请输入不通过原因");
        return;
      }
      
      const params = { reason: this.rejectForm.reason };
      const apiFunc = this.currentRejectType === "send" ? rejectSend : rejectReceive;
      
      apiFunc(this.currentRejectId, params).then(response => {
        this.$modal.msgSuccess("操作成功");
        this.rejectOpen = false;
        this.getList();
      });
    },
    /** 生成取件码并上架 */
    handleGeneratePickupCode(row) {
      this.$confirm('确认为该包裹生成取件码并上架?', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        return generatePickupCode(row.packId);
      }).then((response) => {
        this.$modal.msgSuccess("取件码生成成功: " + response.data.pickupCode);
        this.getList();
      });
    }
  }
};
</script>
