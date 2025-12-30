<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-size: 18px; font-weight: bold;">我的包裹</span>
      </div>

      <!-- 搜索区域 -->
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true">
        <el-form-item label="包裹ID" prop="packId">
          <el-input
            v-model="queryParams.packId"
            placeholder="请输入包裹ID"
            clearable
            style="width: 200px"
          />
        </el-form-item>
        <el-form-item label="包裹状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="请选择状态" clearable style="width: 150px">
            <el-option label="待揽收" value="待揽收" />
            <el-option label="寄出待核验" value="寄出待核验" />
            <el-option label="寄出已核验" value="寄出已核验" />
            <el-option label="运输中" value="运输中" />
            <el-option label="到站待核验" value="到站待核验" />
            <el-option label="入库已核验" value="入库已核验" />
            <el-option label="待取件" value="待取件" />
            <el-option label="已取件" value="已取件" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <!-- 包裹列表 -->
      <el-table v-loading="loading" :data="packList" style="margin-top: 20px">
        <el-table-column label="包裹ID" align="center" prop="packId" width="100" />
        <el-table-column label="收件人" align="center" prop="receiverName" width="100" />
        <el-table-column label="收件电话" align="center" prop="receiverTel" width="120" />
        <el-table-column label="收件地址" align="center" min-width="200">
          <template slot-scope="scope">
            {{ scope.row.receiverProv }}{{ scope.row.receiverCity }}{{ scope.row.receiverDist }}{{ scope.row.receiverAddrDetail }}
          </template>
        </el-table-column>
        <el-table-column label="重量(kg)" align="center" prop="weight" width="100" />
        <el-table-column label="包裹状态" align="center" width="120">
          <template slot-scope="scope">
            <el-tag :type="getStatusType(scope.row.status)">
              {{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="取件码" align="center" prop="pickupCode" width="100">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.pickupCode" type="success" effect="dark">
              {{ scope.row.pickupCode }}
            </el-tag>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createdAt" width="160">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="150">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-view"
              @click="handleViewFlow(scope.row)"
            >查看流转</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>

    <!-- 包裹流转对话框 -->
    <el-dialog title="包裹流转信息" :visible.sync="flowOpen" width="800px" append-to-body>
      <el-steps :active="currentStep" finish-status="success" align-center>
        <el-step
          v-for="(step, index) in flowSteps"
          :key="index"
          :title="step.title"
          :description="step.description"
        ></el-step>
      </el-steps>

      <el-divider></el-divider>

      <div class="pack-detail">
        <h3>包裹详情</h3>
        <el-descriptions :column="2" border>
          <el-descriptions-item label="包裹ID">{{ currentPack.packId }}</el-descriptions-item>
          <el-descriptions-item label="包裹状态">
            <el-tag :type="getStatusType(currentPack.status)">{{ currentPack.status }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="寄件地址" :span="2">
            {{ currentPack.senderProv }}{{ currentPack.senderCity }}{{ currentPack.senderDist }}{{ currentPack.senderAddrDetail }}
          </el-descriptions-item>
          <el-descriptions-item label="收件人">{{ currentPack.receiverName }}</el-descriptions-item>
          <el-descriptions-item label="收件电话">{{ currentPack.receiverTel }}</el-descriptions-item>
          <el-descriptions-item label="收件地址" :span="2">
            {{ currentPack.receiverProv }}{{ currentPack.receiverCity }}{{ currentPack.receiverDist }}{{ currentPack.receiverAddrDetail }}
          </el-descriptions-item>
          <el-descriptions-item label="重量(kg)">{{ currentPack.weight }}</el-descriptions-item>
          <el-descriptions-item label="体积(m³)">{{ currentPack.volume }}</el-descriptions-item>
          <el-descriptions-item label="是否易碎">
            <el-tag :type="currentPack.isFragile === 1 ? 'danger' : 'info'">
              {{ currentPack.isFragile === 1 ? '是' : '否' }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="是否加急">
            <el-tag :type="currentPack.isUrgent === 1 ? 'warning' : 'info'">
              {{ currentPack.isUrgent === 1 ? '是' : '否' }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="取件码" v-if="currentPack.pickupCode">
            <el-tag type="success" effect="dark" size="medium">{{ currentPack.pickupCode }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="备注" :span="2" v-if="currentPack.note">
            {{ currentPack.note }}
          </el-descriptions-item>
        </el-descriptions>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button @click="flowOpen = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getMyPacks } from "@/api/business/pack";

export default {
  name: "MyPacks",
  data() {
    return {
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 包裹列表
      packList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        packId: null,
        status: null
      },
      // 流转对话框
      flowOpen: false,
      // 当前包裹
      currentPack: {},
      // 流转步骤
      flowSteps: [],
      // 当前步骤
      currentStep: 0
    };
  },
  created() {
    // 获取当前用户的clientId，这里需要根据实际情况获取
    // 假设从vuex或localStorage获取
    this.clientId = this.$store.getters.userId || 1;
    this.getList();
  },
  methods: {
    /** 查询包裹列表 */
    getList() {
      this.loading = true;
      getMyPacks(this.clientId).then(response => {
        this.packList = response.rows;
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
    /** 查看流转 */
    handleViewFlow(row) {
      this.currentPack = Object.assign({}, row);
      this.generateFlowSteps(row);
      this.flowOpen = true;
    },
    /** 生成流转步骤 */
    generateFlowSteps(pack) {
      const statusList = [
        { key: "待揽收", title: "待揽收", description: "包裹等待寄出" },
        { key: "寄出待核验", title: "寄出核验", description: "驿站核验中" },
        { key: "寄出已核验", title: "寄出已核验", description: "核验通过" },
        { key: "运输中", title: "运输中", description: "正在运输" },
        { key: "到站待核验", title: "到站核验", description: "到达目的地核验中" },
        { key: "入库已核验", title: "入库已核验", description: "入库核验通过" },
        { key: "待取件", title: "待取件", description: "等待取件" },
        { key: "已取件", title: "已取件", description: "取件完成" }
      ];

      this.flowSteps = statusList;
      this.currentStep = statusList.findIndex(s => s.key === pack.status);
      if (this.currentStep === -1) {
        this.currentStep = 0;
      }
    },
    /** 获取状态类型 */
    getStatusType(status) {
      const typeMap = {
        "待揽收": "info",
        "寄出待核验": "warning",
        "寄出已核验": "success",
        "运输中": "primary",
        "到站待核验": "warning",
        "入库已核验": "success",
        "待取件": "warning",
        "已取件": "success",
        "拒收": "danger",
        "异常": "danger"
      };
      return typeMap[status] || "info";
    }
  }
};
</script>

<style scoped>
.pack-detail {
  margin-top: 20px;
}

.pack-detail h3 {
  margin-bottom: 15px;
  color: #409EFF;
}
</style>
