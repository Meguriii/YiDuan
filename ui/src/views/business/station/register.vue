<template>
  <div class="register-container">
    <el-card class="register-card">
      <div slot="header" class="clearfix">
        <span style="font-size: 20px; font-weight: bold;">驿站注册申请</span>
      </div>
      
      <el-form ref="registerForm" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="驿站名称" prop="stationName">
          <el-input v-model="form.stationName" placeholder="请输入驿站名称" />
        </el-form-item>
        
        <el-form-item label="省" prop="stationProv">
          <el-input v-model="form.stationProv" placeholder="请输入省份" />
        </el-form-item>
        
        <el-form-item label="市" prop="stationCity">
          <el-input v-model="form.stationCity" placeholder="请输入城市" />
        </el-form-item>
        
        <el-form-item label="区县" prop="stationDist">
          <el-input v-model="form.stationDist" placeholder="请输入区县" />
        </el-form-item>
        
        <el-form-item label="详细地址" prop="stationAddr">
          <el-input v-model="form.stationAddr" type="textarea" :rows="3" placeholder="请输入详细地址" />
        </el-form-item>
        
        <el-form-item label="联系电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入联系电话" maxlength="11" />
        </el-form-item>
        
        <el-form-item>
          <el-button type="primary" @click="submitForm" :loading="submitting">提交申请</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { addStationApply } from "@/api/business/station";

export default {
  name: "StationRegister",
  data() {
    return {
      submitting: false,
      form: {
        stationName: "",
        stationProv: "",
        stationCity: "",
        stationDist: "",
        stationAddr: "",
        tel: ""
      },
      rules: {
        stationName: [
          { required: true, message: "请输入驿站名称", trigger: "blur" }
        ],
        stationProv: [
          { required: true, message: "请输入省份", trigger: "blur" }
        ],
        stationCity: [
          { required: true, message: "请输入城市", trigger: "blur" }
        ],
        stationDist: [
          { required: true, message: "请输入区县", trigger: "blur" }
        ],
        stationAddr: [
          { required: true, message: "请输入详细地址", trigger: "blur" }
        ],
        tel: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { pattern: /^1[3-9]\d{9}$/, message: "请输入正确的手机号码", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submitForm() {
      this.$refs["registerForm"].validate(valid => {
        if (valid) {
          this.submitting = true;
          addStationApply(this.form).then(response => {
            this.$modal.msgSuccess("提交成功，请等待管理员审核");
            this.resetForm();
          }).finally(() => {
            this.submitting = false;
          });
        }
      });
    },
    resetForm() {
      this.$refs["registerForm"].resetFields();
    }
  }
};
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 100px);
  padding: 20px;
}

.register-card {
  width: 600px;
  max-width: 100%;
}
</style>
