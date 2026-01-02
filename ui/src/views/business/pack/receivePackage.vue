<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-size: 18px; font-weight: bold;">待取件包裹</span>
      </div>

      <el-table v-loading="loading" :data="packList" style="margin-top: 20px">
        <el-table-column label="包裹ID" align="center" prop="packId" width="100" />
        <el-table-column label="寄件人" align="center" prop="senderName" width="100" />
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
        <el-table-column label="取件码" align="center" prop="pickupCode" width="150">
          <template slot-scope="scope">
            <div v-if="scope.row.pickupCode" class="pickup-code-container">
              <div class="pickup-code">
                <span v-for="(digit, index) in scope.row.pickupCode.split('')" :key="index" class="digit">
                  {{ digit }}
                </span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="success"
              icon="el-icon-check"
              @click="handlePickup(scope.row)"
              :disabled="scope.row.status !== '待取件'"
            >
              取件
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>

    <!-- 取件确认对话框 -->
    <el-dialog title="确认取件" :visible.sync="pickupOpen" width="400px" append-to-body>
      <div style="text-align: center; padding: 20px;">
        <p style="font-size: 16px; margin-bottom: 20px;">您确定要取件吗？</p>
        <p style="font-size: 18px; font-weight: bold; color: #E6A23C;">取件码：{{ currentPickupCode }}</p>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="pickupOpen = false">取 消</el-button>
        <el-button type="primary" @click="confirmPickup">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPack, pickup, listPackByReceiverIdAndStatus } from '@/api/business/pack'

export default {
  name: "ReceivePackage",
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
        receiverId: null, // 收件人ID
        status: '待取件' // 状态为待取件
      },
      // 取件对话框
      pickupOpen: false,
      // 当前取件码
      currentPickupCode: '',
      // 当前操作的包裹
      currentPack: {}
    }
  },
  created() {
    // 设置当前用户ID作为收件人ID
    this.queryParams.receiverId = this.$store.getters.id;
    this.getList();
  },
  methods: {
    /** 查询包裹列表 */
    getList() {
      this.loading = true;
      // 查询当前用户作为收件人且状态为待取件的包裹
      listPackByReceiverIdAndStatus(this.queryParams.receiverId, this.queryParams.status).then(response => {
        this.packList = response.rows;
        this.total = response.total;
        this.loading = false;
      }).catch(error => {
        this.loading = false;
        this.$modal.msgError("获取包裹列表失败");
        console.error(error);
      });
    },

    /** 获取状态类型 */
    getStatusType(status) {
      if (status === '待取件') {
        return 'warning';
      } else if (status === '已取件') {
        return 'success';
      } else {
        return 'info';
      }
    },

    /** 处理取件操作 */
    handlePickup(row) {
      this.currentPack = Object.assign({}, row);
      this.currentPickupCode = row.pickupCode;
      this.pickupOpen = true;
    },

    /** 确认取件 */
    confirmPickup() {
      pickup(this.currentPack.pickupCode).then(response => {
        this.$modal.msgSuccess("取件成功");
        this.pickupOpen = false;
        // 刷新列表
        this.getList();
      }).catch(error => {
        this.$modal.msgError("取件失败：" + (error.message || error));
      });
    }
  }
}
</script>

<style scoped>
.box-card {
  margin: 20px;
}

.pickup-code-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.pickup-code-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.pickup-code {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  background: #f0f9ff;
  border: 2px solid #409eff;
  border-radius: 8px;
  padding: 5px;
  font-weight: bold;
  color: #409eff;
}

.digit {
  display: inline-block;
  width: 25px;
  height: 35px;
  line-height: 35px;
  text-align: center;
  margin: 0 2px;
  border-right: 1px solid #409eff;
  font-size: 18px;
}

.digit:last-child {
  border-right: none;
}
</style>
