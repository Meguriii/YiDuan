<template>
  <div class="app-container">
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
  </div>
</template>

<script>
import { listPack, generatePickupCode } from "@/api/business/pack";

export default {
  name: "ShelveManage",
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
      // 上架管理表格数据
      shelvePackList: [],
      // 弹出层标题
      title: "",
      // 查询参数
      queryParams: {
        type: 'shelve' // 上架管理
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询上架管理列表 */
    getList() {
      this.loading = true;
      this.loading = true;
      listPack({ ...this.queryParams, status: '入库已核验' }).then(response => {
        this.shelvePackList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 生成取件码并上架按钮操作 */
    handleGeneratePickupCode(row) {
      this.$confirm('是否确认为包裹 ' + row.packId + ' 生成取件码并上架？', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return generatePickupCode(row.packId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess('取件码生成成功');
      }).catch(function() {});
    }
  }
};
</script>