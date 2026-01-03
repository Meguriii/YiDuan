<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>寄件信息录入</span>
      </div>

      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="寄件人姓名" prop="senderName">
              <el-input v-model="form.senderName" placeholder="寄件人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="寄件人电话" prop="senderTel">
              <el-input v-model="form.senderTel" placeholder="寄件人电话"  />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="寄件地址" prop="senderAddr">
              <el-select
                v-model="senderAddrSelected"
                placeholder="请选择寄件地址"
                filterable
                @change="handleSenderAddrChange"
                style="width: 100%">
                <el-option
                  v-for="addr in addrList"
                  :key="addr.addrId"
                  :label="addr.addrName + ' ' + addr.addrTel + ' ' + addr.addrProv + addr.addrCity + addr.addrDist + addr.addrDetail"
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

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="收件人姓名" prop="receiverName">
              <el-input v-model="form.receiverName" placeholder="请输入收件人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收件人电话" prop="receiverTel">
              <el-input v-model="form.receiverTel" placeholder="请输入收件人电话" @blur="queryReceiverIdByTel" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="收件地址" prop="receiverAddr">
              <el-select
                v-model="receiverAddrSelected"
                placeholder="请选择收件地址或手动输入"
                filterable
                allow-create
                @change="handleReceiverAddrChange"
                style="width: 100%">
                <el-option
                  v-for="addr in addrList"
                  :key="addr.addrId"
                  :label="addr.addrName + ' ' + addr.addrTel + ' ' + addr.addrProv + addr.addrCity + addr.addrDist + addr.addrDetail"
                  :value="addr.addrId">
                </el-option>
              </el-select>
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
            <el-form-item label="重量(kg)" prop="weight">
              <el-input v-model="form.weight" placeholder="请输入重量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="体积(m³)" prop="volume">
              <el-input v-model="form.volume" placeholder="请输入体积" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="备注" prop="note">
              <el-input v-model="form.note" type="textarea" placeholder="请输入备注信息" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="是否危险品" prop="isDangerous">
              <el-switch v-model="form.isDangerous" :active-value='1' :inactive-value='0'></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否易碎品" prop="isFragile">
              <el-switch v-model="form.isFragile" :active-value='1' :inactive-value='0'></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否加急" prop="isUrgent">
              <el-switch v-model="form.isUrgent" :active-value='1' :inactive-value='0'></el-switch>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button type="primary" @click="submitForm">提交寄件</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { addPack } from '@/api/business/pack'
import { listAddr } from '@/api/business/addr'
import { getUserByPhonenumber } from '@/api/system/user'

export default {
  name: "SendPackage",
  data() {
    return {
      // 表单参数
      form: {
        senderName: '',
        senderTel: '',
        senderProv: '',
        senderCity: '',
        senderDist: '',
        senderAddrDetail: '',
        receiverId: null, // 收件用户ID
        receiverName: '',
        receiverTel: '',
        receiverProv: '',
        receiverCity: '',
        receiverDist: '',
        receiverAddrDetail: '',
        weight: '',
        volume: '',
        note: '',
        isDangerous: 0,
        isFragile: 0,
        isUrgent: 0
      },
      // 地址列表
      addrList: [],
      // 选中的寄件地址
      senderAddrSelected: null,
      // 选中的收件地址
      receiverAddrSelected: null,
      // 表单校验
      rules: {
        senderName: [
          { required: true, message: "寄件人姓名不能为空", trigger: "blur" }
        ],
        senderTel: [
          { required: true, message: "寄件人电话不能为空", trigger: "blur" },
          { pattern: /^1[3-9]\d{9}$/, message: "请输入正确的手机号码", trigger: "blur" }
        ],
        senderProv: [
          { required: true, message: "寄件省不能为空", trigger: "blur" }
        ],
        senderCity: [
          { required: true, message: "寄件市不能为空", trigger: "blur" }
        ],
        senderDist: [
          { required: true, message: "寄件区县不能为空", trigger: "blur" }
        ],
        senderAddrDetail: [
          { required: true, message: "寄件详细地址不能为空", trigger: "blur" }
        ],
        receiverName: [
          { required: true, message: "收件人姓名不能为空", trigger: "blur" }
        ],
        receiverTel: [
          { required: true, message: "收件人电话不能为空", trigger: "blur" },
          { pattern: /^1[3-9]\d{9}$/, message: "请输入正确的手机号码", trigger: "blur" }
        ],
        receiverProv: [
          { required: true, message: "收件省不能为空", trigger: "blur" }
        ],
        receiverCity: [
          { required: true, message: "收件市不能为空", trigger: "blur" }
        ],
        receiverDist: [
          { required: true, message: "收件区县不能为空", trigger: "blur" }
        ],
        receiverAddrDetail: [
          { required: true, message: "收件详细地址不能为空", trigger: "blur" }
        ],
        weight: [
          { required: true, message: "重量不能为空", trigger: "blur" },
          { pattern: /^\d+(\.\d+)?$/, message: "请输入正确的重量", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getCurrentUserInfo();
    this.getAddrList();
  },
  methods: {
    /** 获取当前用户信息 */
    getCurrentUserInfo() {
      // 从vuex获取当前用户信息
      const userId = this.$store.getters.id;
      const userName = this.$store.getters.name;
      const userPhone = this.$store.getters.phonenumber || '未填写';

      // 设置寄件人信息（仅用于显示，实际寄件人ID由后端自动设置）
      this.form.senderName = userName;
      this.form.senderTel = userPhone;
    },

    /** 获取地址列表 */
    getAddrList() {
      // 查询当前用户的地址列表
      listAddr({ userId: this.$store.getters.id }).then(response => {
        this.addrList = response.rows;
        // 如果当前没有选择寄件地址，尝试设置默认地址
        if (this.addrList && this.addrList.length > 0 && !this.senderAddrSelected) {
          const defaultAddr = this.addrList.find(addr => addr.isDefault === 1) || this.addrList[0];
          if (defaultAddr) {
            this.senderAddrSelected = defaultAddr.addrId;
            this.handleSenderAddrChange(defaultAddr.addrId);
          }
        }
      });
    },

    /** 处理寄件地址选择 */
    handleSenderAddrChange(addrId) {
      const selectedAddr = this.addrList.find(addr => addr.addrId === addrId);
      if (selectedAddr) {
        this.form.senderName = selectedAddr.addrName;
        this.form.senderTel = selectedAddr.addrTel;
        // 设置寄件地址信息
        this.form.senderProv = selectedAddr.addrProv;
        this.form.senderCity = selectedAddr.addrCity;
        this.form.senderDist = selectedAddr.addrDist;
        this.form.senderAddrDetail = selectedAddr.addrDetail;
      }
    },

    /** 处理收件地址选择 */
    handleReceiverAddrChange(addrId) {
      const selectedAddr = this.addrList.find(addr => addr.addrId === addrId);
      if (selectedAddr) {
        this.form.receiverName = selectedAddr.addrName;
        this.form.receiverTel = selectedAddr.addrTel;
        this.form.receiverProv = selectedAddr.addrProv;
        this.form.receiverCity = selectedAddr.addrCity;
        this.form.receiverDist = selectedAddr.addrDist;
        this.form.receiverAddrDetail = selectedAddr.addrDetail;
      }
    },

    /** 提交表单 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 构建包裹数据
          const packData = {
            // 不设置senderId，后端会自动使用当前用户ID
            // 寄件地址信息（如果用户选择了寄件地址，则使用选择的地址；否则使用当前用户的默认地址）
            senderProv: this.form.senderProv || '',
            senderCity: this.form.senderCity || '',
            senderDist: this.form.senderDist || '',
            senderAddrDetail: this.form.senderAddrDetail || '',
            receiverId: this.form.receiverId || null, // 设置收件人ID，如果未找到对应用户则为null
            receiverName: this.form.receiverName,
            receiverTel: this.form.receiverTel,
            receiverProv: this.form.receiverProv,
            receiverCity: this.form.receiverCity,
            receiverDist: this.form.receiverDist,
            receiverAddrDetail: this.form.receiverAddrDetail,
            weight: this.form.weight,
            volume: this.form.volume,
            note: this.form.note,
            isDangerous: this.form.isDangerous,
            isFragile: this.form.isFragile,
            isUrgent: this.form.isUrgent,
            status: '待揽收' // 默认状态为待揽收
          };

          addPack(packData).then(response => {
            this.$modal.msgSuccess("寄件成功");
            this.resetForm();
          }).catch(error => {
            this.$modal.msgError("寄件失败：" + error.message);
          });
        }
      });
    },

    /** 根据收件人电话查询用户ID */
    queryReceiverIdByTel() {
      if (this.form.receiverTel) {
        getUserByPhonenumber(this.form.receiverTel).then(response => {
          if (response.code === 200) {
            // 找到用户，设置receiverId
            this.form.receiverId = response.data.userId;
            // 如果收件人姓名为空，使用用户昵称
            if (!this.form.receiverName) {
              this.form.receiverName = response.data.nickName || response.data.userName;
            }
            this.$message.success('已找到对应用户');
          } else {
            // 未找到用户，设置receiverId为null
            this.form.receiverId = null;
            this.$message.info('未找到对应用户，将作为普通收件人处理');
          }
        }).catch(error => {
          console.error('查询用户失败:', error);
          this.form.receiverId = null;
          this.$message.error('查询用户失败');
        });
      } else {
        this.form.receiverId = null;
      }
    },

    /** 重置表单 */
    resetForm() {
      this.$refs["form"].resetFields();
      this.senderAddrSelected = null;
      this.receiverAddrSelected = null;
      // 重置寄件地址字段
      this.form.senderProv = '';
      this.form.senderCity = '';
      this.form.senderDist = '';
      this.form.senderAddrDetail = '';
      this.getCurrentUserInfo();
    }
  }
}
</script>

<style scoped>
.box-card {
  margin: 20px;
}
</style>
