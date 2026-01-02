<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="寄件省" prop="senderProv">
        <el-input
          v-model="queryParams.senderProv"
          placeholder="请输入寄件省"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="寄件市" prop="senderCity">
        <el-input
          v-model="queryParams.senderCity"
          placeholder="请输入寄件市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="寄件区县" prop="senderDist">
        <el-input
          v-model="queryParams.senderDist"
          placeholder="请输入寄件区县"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="寄件地址" prop="senderAddrDetail">
        <el-input
          v-model="queryParams.senderAddrDetail"
          placeholder="请输入寄件详细地址"
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
      <el-form-item label="收件省" prop="receiverProv">
        <el-input
          v-model="queryParams.receiverProv"
          placeholder="请输入收件省"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件市" prop="receiverCity">
        <el-input
          v-model="queryParams.receiverCity"
          placeholder="请输入收件市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件区县" prop="receiverDist">
        <el-input
          v-model="queryParams.receiverDist"
          placeholder="请输入收件区县"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件地址" prop="receiverAddrDetail">
        <el-input
          v-model="queryParams.receiverAddrDetail"
          placeholder="请输入收件详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件姓名" prop="receiverName">
        <el-input
          v-model="queryParams.receiverName"
          placeholder="请输入收件人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件手机" prop="receiverTel">
        <el-input
          v-model="queryParams.receiverTel"
          placeholder="请输入收件人手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="重量" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="体积" prop="volume">
        <el-input
          v-model="queryParams.volume"
          placeholder="请输入体积"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否危险" prop="isDangerous">
        <el-input
          v-model="queryParams.isDangerous"
          placeholder="请输入是否危险品"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否易碎" prop="isFragile">
        <el-input
          v-model="queryParams.isFragile"
          placeholder="请输入是否易碎"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否加急" prop="isUrgent">
        <el-input
          v-model="queryParams.isUrgent"
          placeholder="请输入是否加急"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="取件码" prop="pickupCode">
        <el-input
          v-model="queryParams.pickupCode"
          placeholder="请输入取件码"
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
          v-hasPermi="['business:pack:add']"
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
          v-hasPermi="['business:pack:edit']"
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
          v-hasPermi="['business:pack:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportWithSender"
          v-hasPermi="['business:pack:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="packList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="包裹ID" align="center" prop="packId" />
<!--      <el-table-column label="寄件人ID" align="center" prop="senderId" />-->
      <el-table-column label="寄件人姓名" align="center" prop="senderName" />
      <el-table-column label="寄件省" align="center" prop="senderProv" />
      <el-table-column label="寄件市" align="center" prop="senderCity" />
      <el-table-column label="寄件区县" align="center" prop="senderDist" />
<!--      <el-table-column label="寄件详细地址" align="center" prop="senderAddrDetail" />-->
<!--      <el-table-column label="收件人ID" align="center" prop="receiverId" />-->
      <el-table-column label="收件人姓名" align="center" prop="receiverName" />
      <el-table-column label="收件省" align="center" prop="receiverProv" />
      <el-table-column label="收件市" align="center" prop="receiverCity" />
      <el-table-column label="收件区县" align="center" prop="receiverDist" />
<!--      <el-table-column label="收件详细地址" align="center" prop="receiverAddrDetail" />-->
      <el-table-column label="重量" align="center" prop="weight" />
      <el-table-column label="体积" align="center" prop="volume" />
      <el-table-column label="备注" align="center" prop="note" />
      <el-table-column label="是否危险品" align="center" prop="isDangerous" >
      <template slot-scope="scope">
        <dict-tag :options="dict.type.biz_yes_no" :value="scope.row.isDangerous"/>
      </template>
      </el-table-column>
      <el-table-column label="是否易碎" align="center" prop="isFragile" >
      <template slot-scope="scope">
        <dict-tag :options="dict.type.biz_yes_no" :value="scope.row.isFragile"/>
      </template>
      </el-table-column>
      <el-table-column label="是否加急" align="center" prop="isUrgent" >
      <template slot-scope="scope">
        <dict-tag :options="dict.type.biz_yes_no" :value="scope.row.isUrgent"/>
      </template>
      </el-table-column>
      <el-table-column label="包裹状态" align="center" prop="status" width="120">
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
<!--      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="更新时间" align="center" prop="updatedAt" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="300">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            :disabled="!canEditPack(scope.row)"
            v-hasPermi="['business:pack:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleViewDetail(scope.row)"
            v-hasPermi="['business:pack:query']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit-outline"
            @click="handleUpdateStatus(scope.row)"
            :disabled="!isSuperAdmin()"
            v-hasPermi="['business:pack:editStatus']"
          >修改状态</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:pack:remove']"
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

    <!-- 添加或修改包裹表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="寄件地址" prop="senderAddr">
              <el-select
                v-model="senderAddrSelected"
                placeholder="请选择地址或手动输入"
                filterable
                allow-create
                @change="handleSenderAddrChange"
                style="width: 100%">
                <el-option
                  v-for="addr in addrList"
                  :key="addr.addrId"
                  :label="addr.addrProv + addr.addrCity + addr.addrDist + addr.addrDetail"
                  :value="addr.addrId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="寄件省" prop="senderProv">
              <el-input v-model="form.senderProv" placeholder="请输入寄件省" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="寄件市" prop="senderCity">
              <el-input v-model="form.senderCity" placeholder="请输入寄件市" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="寄件区县" prop="senderDist">
              <el-input v-model="form.senderDist" placeholder="请输入寄件区县" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="寄件地址" prop="senderAddrDetail">
              <el-input v-model="form.senderAddrDetail" placeholder="请输入寄件详细地址" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="收件地址" prop="receiverAddr">
              <el-select
                v-model="receiverAddrSelected"
                placeholder="请选择地址或手动输入"
                filterable
                allow-create
                @change="handleReceiverAddrChange"
                style="width: 100%">
                <el-option
                  v-for="addr in addrList"
                  :key="addr.addrId"
                  :label="addr.addrProv + addr.addrCity + addr.addrDist + addr.addrDetail"
                  :value="addr.addrId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="收件姓名" prop="receiverName">
              <el-input v-model="form.receiverName" placeholder="请输入收件人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收件手机" prop="receiverTel">
              <el-input v-model="form.receiverTel" placeholder="请输入收件人手机号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="收件省" prop="receiverProv">
              <el-input v-model="form.receiverProv" placeholder="请输入收件省" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收件市" prop="receiverCity">
              <el-input v-model="form.receiverCity" placeholder="请输入收件市" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="收件区县" prop="receiverDist">
              <el-input v-model="form.receiverDist" placeholder="请输入收件区县" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收件地址" prop="receiverAddrDetail">
              <el-input v-model="form.receiverAddrDetail" placeholder="请输入收件详细地址" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="重量" prop="weight">
              <el-input v-model="form.weight" placeholder="请输入重量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="体积" prop="volume">
              <el-input v-model="form.volume" placeholder="请输入体积" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="备注" prop="note">
              <el-input v-model="form.note" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="是否危险" prop="isDangerous">
              <el-switch v-model="form.isDangerous" :active-value='1' :inactive-value='0'></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否易碎" prop="isFragile">
              <el-switch v-model="form.isFragile" :active-value='1' :inactive-value='0'></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否加急" prop="isUrgent">
              <el-switch v-model="form.isUrgent" :active-value='1' :inactive-value='0'></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 包裹详情对话框 -->
    <el-dialog title="包裹详情" :visible.sync="detailOpen" width="800px" append-to-body>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="寄件人姓名">{{ currentPack.senderName }}</el-descriptions-item>
        <el-descriptions-item label="寄件人电话">{{ currentPack.senderTel }}</el-descriptions-item>
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
        <el-descriptions-item label="是否危险品">
          <el-tag :type="currentPack.isDangerous === 1 ? 'danger' : 'info'">
            {{ currentPack.isDangerous === 1 ? '是' : '否' }}
          </el-tag>
        </el-descriptions-item>
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
        <el-descriptions-item label="包裹状态">
          <el-tag :type="getStatusType(currentPack.status)">{{ currentPack.status }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="取件码" v-if="currentPack.pickupCode">
          <el-tag type="success" effect="dark" size="medium">{{ currentPack.pickupCode }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="创建时间">
          <span>{{ parseTime(currentPack.createdAt, '{y}-{m}-{d} {h}:{i}') }}</span>
        </el-descriptions-item>
        <el-descriptions-item label="更新时间">
          <span>{{ parseTime(currentPack.updatedAt, '{y}-{m}-{d} {h}:{i}') }}</span>
        </el-descriptions-item>
        <el-descriptions-item label="备注" :span="2" v-if="currentPack.note">
          {{ currentPack.note }}
        </el-descriptions-item>
      </el-descriptions>
      <div slot="footer" class="dialog-footer">
        <el-button @click="detailOpen = false">关 闭</el-button>
      </div>
    </el-dialog>

    <!-- 修改包裹状态对话框 -->
    <el-dialog :title="statusDialogTitle" :visible.sync="statusOpen" width="500px" append-to-body>
      <el-form ref="statusForm" :model="statusForm" :rules="statusRules" label-width="80px">
        <el-form-item label="包裹ID" prop="packId">
          <el-input v-model="statusForm.packId" disabled />
        </el-form-item>
        <el-form-item label="当前状态" prop="currentStatus">
          <el-input v-model="statusForm.currentStatus" disabled />
        </el-form-item>
        <el-form-item label="新状态" prop="newStatus">
          <el-select v-model="statusForm.newStatus" placeholder="请选择新状态" style="width: 100%">
            <el-option label="待揽收" value="待揽收" />
            <el-option label="寄出待核验" value="寄出待核验" />
            <el-option label="寄出已核验" value="寄出已核验" />
            <el-option label="运输中" value="运输中" />
            <el-option label="到站待核验" value="到站待核验" />
            <el-option label="入库已核验" value="入库已核验" />
            <el-option label="待上架" value="待上架" />
            <el-option label="待取件" value="待取件" />
            <el-option label="已取件" value="已取件" />
            <el-option label="拒收" value="拒收" />
            <el-option label="退件" value="退件" />
            <el-option label="异常" value="异常" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="statusForm.note" type="textarea" placeholder="请输入备注信息" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitStatusForm">确 定</el-button>
        <el-button @click="cancelStatus">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPack, getPack, delPack, addPack, updatePack, updatePackStatus, getPackWithSender } from "@/api/business/pack"
import { listAddr, addAddr } from "@/api/business/addr"

export default {
  name: "Pack",
  dicts: ['biz_yes_no'],
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
      // 包裹表表格数据
      packList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 地址列表
      addrList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        senderId: null,
        senderProv: null,
        senderCity: null,
        senderDist: null,
        senderAddrDetail: null,
        receiverId: null,
        receiverName: null,
        receiverTel: null,
        receiverProv: null,
        receiverCity: null,
        receiverDist: null,
        receiverAddrDetail: null,
        weight: null,
        volume: null,
        note: null,
        isDangerous: null,
        isFragile: null,
        isUrgent: null,
        status: null,
        pickupCode: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {
        isDangerous: 0,
        isFragile: 0,
        isUrgent: 0
      },
      // 选中的寄件地址
      senderAddrSelected: null,
      // 选中的收件地址
      receiverAddrSelected: null,
      // 表单校验
      rules: {
        senderId: [
          {required: true, message: "寄件用户(biz_client)不能为空", trigger: "blur"}
        ],
        senderProv: [
          {required: true, message: "寄件省不能为空", trigger: "blur"}
        ],
        senderCity: [
          {required: true, message: "寄件市不能为空", trigger: "blur"}
        ],
        senderDist: [
          {required: true, message: "寄件区县不能为空", trigger: "blur"}
        ],
        senderAddrDetail: [
          {required: true, message: "寄件详细地址不能为空", trigger: "blur"}
        ],
        receiverName: [
          {required: true, message: "收件人姓名不能为空", trigger: "blur"}
        ],
        receiverTel: [
          {required: true, message: "收件人手机号不能为空", trigger: "blur"}
        ],
        receiverProv: [
          {required: true, message: "收件省不能为空", trigger: "blur"}
        ],
        receiverCity: [
          {required: true, message: "收件市不能为空", trigger: "blur"}
        ],
        receiverDist: [
          {required: true, message: "收件区县不能为空", trigger: "blur"}
        ],
        receiverAddrDetail: [
          {required: true, message: "收件详细地址不能为空", trigger: "blur"}
        ],
        isDangerous: [
          {required: true, message: "是否危险品不能为空", trigger: "blur"}
        ],
        isFragile: [
          {required: true, message: "是否易碎不能为空", trigger: "blur"}
        ],
        isUrgent: [
          {required: true, message: "是否加急不能为空", trigger: "blur"}
        ],
        status: [
          {required: true, message: "包裹状态不能为空", trigger: "change"}
        ],
        createdAt: [
          {required: true, message: "创建时间不能为空", trigger: "blur"}
        ],
        updatedAt: [
          {required: true, message: "更新时间不能为空", trigger: "blur"}
        ]
      },
      // 修改包裹状态相关数据
      statusOpen: false,
      statusDialogTitle: "",
      statusForm: {
        packId: null,
        currentStatus: null,
        newStatus: null,
        note: null
      },
      statusRules: {
        newStatus: [
          {required: true, message: "新状态不能为空", trigger: "change"}
        ]
      },
      // 包裹详情相关数据
      detailOpen: false,
      currentPack: {}
    }
  },
  created() {
      this.getList()
      this.getAddrList()
    },
  methods: {
      /** 查询地址列表 */
      getAddrList()
      {
        listAddr({pageNum: 1, pageSize: 1000}).then(response => {
          this.addrList = response.rows
        })
      }
    ,
      /** 查询包裹表列表(包含寄件人信息) */
      getPackListWithSender(queryParams)
      {
        this.loading = true
        // 使用新的API端点获取包含寄件人信息的包裹列表
        import('@/api/business/pack').then(module => {
          module.listPackWithSender(queryParams).then(response => {
            this.packList = response.rows;
            this.total = response.total;
            this.loading = false;
          })
        })
      }
      ,
      /** 查询包裹表列表 */
      getList()
      {
        this.loading = true
        // 使用新的API获取包含寄件人信息的包裹列表
        this.getPackListWithSender(this.queryParams);
      }
    ,
      // 取消按钮
      cancel()
      {
        this.open = false
        this.reset()
      }
    ,
      // 表单重置
      reset()
      {
        this.form = {
          packId: null,
          senderId: null,
          senderProv: null,
          senderCity: null,
          senderDist: null,
          senderAddrDetail: null,
          receiverId: null,
          receiverName: null,
          receiverTel: null,
          receiverProv: null,
          receiverCity: null,
          receiverDist: null,
          receiverAddrDetail: null,
          weight: null,
          volume: null,
          note: null,
          isDangerous: null,
          isFragile: null,
          isUrgent: null,
          status: null,
          pickupCode: null,
          createdAt: null,
          updatedAt: null
        }
        this.senderAddrSelected = null
        this.receiverAddrSelected = null
        this.resetForm("form")
      }
    ,
      /** 寄件地址选择改变 */
      handleSenderAddrChange(val)
      {
        if (val) {
          const addr = this.addrList.find(item => item.addrId === val)
          if (addr) {
            this.form.senderProv = addr.addrProv
            this.form.senderCity = addr.addrCity
            this.form.senderDist = addr.addrDist
            this.form.senderAddrDetail = addr.addrDetail
          }
        }
      }
    ,
      /** 收件地址选择改变 */
      handleReceiverAddrChange(val)
      {
        if (val) {
          const addr = this.addrList.find(item => item.addrId === val)
          if (addr) {
            this.form.receiverProv = addr.addrProv
            this.form.receiverCity = addr.addrCity
            this.form.receiverDist = addr.addrDist
            this.form.receiverAddrDetail = addr.addrDetail
            this.form.receiverName = addr.addrName
            this.form.receiverTel = addr.addrTel
          }
        }
      }
    ,
      /** 搜索按钮操作 */
      handleQuery()
      {
        this.queryParams.pageNum = 1
        this.getList()
      }
    ,
      /** 重置按钮操作 */
      resetQuery()
      {
        this.resetForm("queryForm")
        this.handleQuery()
      }
    ,
      // 多选框选中数据
      handleSelectionChange(selection)
      {
        this.ids = selection.map(item => item.packId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      }
    ,
      /** 新增按钮操作 */
      handleAdd()
      {
        this.reset()
        this.open = true
        this.title = "添加包裹表"
      }
    ,
      /** 修改按钮操作 */
      handleUpdate(row)
      {
        this.reset()
        const packId = row.packId || this.ids
        getPack(packId).then(response => {
          this.form = response.data
          this.open = true
          this.title = "修改包裹表"
        })
      }
    ,
      /** 提交按钮 */
      submitForm()
      {
        this.$refs["form"].validate(valid => {
          if (valid) {
            // 先保存新地址（如果需要）
            const promises = []

            // 检查是否需要保存新的寄件地址
            if (this.form.senderProv && this.form.senderCity && this.form.senderDist && this.form.senderAddrDetail) {
              const existingSender = this.addrList.find(addr =>
                addr.addrProv === this.form.senderProv &&
                addr.addrCity === this.form.senderCity &&
                addr.addrDist === this.form.senderDist &&
                addr.addrDetail === this.form.senderAddrDetail
              )
              if (!existingSender) {
                const newSenderAddr = {
                  addrProv: this.form.senderProv,
                  addrCity: this.form.senderCity,
                  addrDist: this.form.senderDist,
                  addrDetail: this.form.senderAddrDetail,
                  addrName: '寄件地址',
                  addrTel: '',
                  isDefault: 0
                }
                promises.push(addAddr(newSenderAddr))
              }
            }

            // 检查是否需要保存新的收件地址
            if (this.form.receiverProv && this.form.receiverCity && this.form.receiverDist && this.form.receiverAddrDetail) {
              const existingReceiver = this.addrList.find(addr =>
                addr.addrProv === this.form.receiverProv &&
                addr.addrCity === this.form.receiverCity &&
                addr.addrDist === this.form.receiverDist &&
                addr.addrDetail === this.form.receiverAddrDetail
              )
              if (!existingReceiver) {
                const newReceiverAddr = {
                  addrProv: this.form.receiverProv,
                  addrCity: this.form.receiverCity,
                  addrDist: this.form.receiverDist,
                  addrDetail: this.form.receiverAddrDetail,
                  addrName: this.form.receiverName || '收件地址',
                  addrTel: this.form.receiverTel || '',
                  isDefault: 0
                }
                promises.push(addAddr(newReceiverAddr))
              }
            }

            // 等待地址保存完成后再保存包裹
            Promise.all(promises).then(() => {
              // 刷新地址列表
              if (promises.length > 0) {
                this.getAddrList()
              }

              // 保存包裹
              if (this.form.packId != null) {
                updatePack(this.form).then(response => {
                  this.$modal.msgSuccess("修改成功")
                  this.open = false
                  this.getList()
                })
              } else {
                addPack(this.form).then(response => {
                  this.$modal.msgSuccess("新增成功")
                  this.open = false
                  this.getList()
                })
              }
            }).catch(error => {
              this.$modal.msgError("地址保存失败")
              console.error(error)
            })
          }
        })
      }
    ,
      /** 删除按钮操作 */
      handleDelete(row)
      {
        const packIds = row.packId || this.ids
        this.$modal.confirm('是否确认删除包裹表编号为"' + packIds + '"的数据项？').then(function () {
          return delPack(packIds)
        }).then(() => {
          this.getList()
          this.$modal.msgSuccess("删除成功")
        }).catch(() => {
        })
      }
    ,
      // /** 导出按钮操作 */
      // handleExport()
      // {
      //   this.download('business/pack/export', {
      //     ...this.queryParams
      //   }, `pack_${new Date().getTime()}.xlsx`)
      // }
      // ,
      /** 导出按钮操作(包含寄件人信息) */
      handleExportWithSender()
      {
        this.download('business/pack/exportWithSender', {
          ...this.queryParams
        }, `pack_with_sender_${new Date().getTime()}.xlsx`)
      }
    ,
      /** 修改包裹状态按钮操作 */
      handleUpdateStatus(row)
      {
        this.resetStatusForm();
        this.statusForm.packId = row.packId;
        this.statusForm.currentStatus = row.status;
        this.statusDialogTitle = "修改包裹状态";
        this.statusOpen = true;
      }
    ,
      // 修改包裹状态表单重置
      resetStatusForm()
      {
        this.statusForm = {
          packId: null,
          currentStatus: null,
          newStatus: null,
          note: null
        };
        this.resetForm("statusForm");
      },
      // 取消修改包裹状态
      cancelStatus()
      {
        this.statusOpen = false;
        this.resetStatusForm();
      },
      // 提交修改包裹状态
      submitStatusForm()
      {
        this.$refs["statusForm"].validate(valid => {
          if (valid) {
            const data = {
              status: this.statusForm.newStatus,
              note: this.statusForm.note
            };
            updatePackStatus(this.statusForm.packId, data).then(response => {
              if (response.code === 200) {
                this.$modal.msgSuccess("修改包裹状态成功");
                this.statusOpen = false;
                this.getList();
              } else {
                this.$modal.msgError("修改包裹状态失败: " + response.msg);
              }
            });
          }
        });
      }
    ,
      /** 查看包裹详情 */
      handleViewDetail(row)
      {
        // 获取包含寄件人信息的包裹详情
        getPackWithSender(row.packId).then(response => {
          this.currentPack = response.data;
          this.detailOpen = true;
        });
      }
    ,
      /** 获取状态类型 */
      getStatusType(status)
      {
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
      },
      isSuperAdmin()
      {
        const userRoles = this.$store.state.user.roles;
        // console.log('当前用户角色:', userRoles);
        return userRoles && Array.isArray(userRoles) && userRoles.includes('admin');
      },
      canEditPack(row)
      {
        const isSuperAdmin = this.isSuperAdmin();
        if (isSuperAdmin) {
          return true;
        }
        const highStatuses = ["寄出已核验", "运输中", "到站待核验", "入库已核验", "待取件", "已取件", "拒收", "退件", "异常"];
        return !highStatuses.includes(row.status);
      },
    },
}
</script>
