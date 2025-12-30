<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>大学快递驿站后台系统</h2>
        <p>
          随着高校校园的规模不断扩大和科技发展，师生的网络购物、文件寄送等需求日益增长，快递业务量呈持续上升趋势。传统校园快递服务信息化程度低、管理效率差、包裹分拣与核验环节容易出错，也常常因为管理原因发生学生与驿站的冲突。尤其在高峰期（如开学季、双十一、节假日前后），驿站内包裹堆积严重，取件排队时间长、管理混乱、包裹丢失或误取事件时有发生，严重影响了学生与管理人员的使用体验与工作效率。
          <br>
          为了解决上述问题，本项目拟开发一套大学快递系统，实现校园快递业务的数字化与智能化管理。通过完善的权限分级设计与数据库支持，系统能够实现驿站信息统一管理、包裹全流程追踪、用户取件码自动生成与验证等核心功能，从而提高快递流转效率、规范化驿站管理、保障数据安全与业务透明化。
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-cloudy"
            plain
            @click="goTarget('https://github.com/Meguriii/YiDuan')"
            >Github项目库</el-button
          >
          <el-button
            size="mini"
            icon="el-icon-s-home"
            plain
            @click="goTarget('https://vcnfzvrvujwh.feishu.cn/wiki/Hj36wCdGJiB2i2kUIBwcZsbJnmh')"
            >飞书研究报告</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="18" :sm="18" :md="9" :lg="6">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>
          <div class="update-log-container">
            <el-collapse accordion>
            <el-collapse-item title="v0.9.1 - 2025-12-31">
              <ol>
                <li>优化管理员对包裹管理权限</li>
                <li>优化包裹管理界面，添加包裹详情弹窗</li>
                <li>重构修改包裹信息界面</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.9.0 - 2025-12-30">
              <ol>
                <li>重构主页UI</li>
                <li>优化过长的更新日志</li>
                <li>优化驿站审批后管理员绑定逻辑</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.8.4 - 2025-12-28">
              <ol>
                <li>实现超级管理员审核驿站功能</li>
                <li>完善审核操作日志记录</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.8.3 - 2025-12-26">
              <ol>
                <li>实现驿站管理员注册功能</li>
                <li>新增驿站信息填写和验证流程</li>
                <li>完成负责人与驿站地址信息管理</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.8.2 - 2025-12-24">
              <ol>
                <li>优化驿站数据库表结构</li>
                <li>实现驿站管理功能</li>
                <li>新增驿站库存包裹信息查询接口</li>
                <li>完善本驿站数据访问权限控制</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.8.1 - 2025-12-22">
              <ol>
                <li>实现发货核验功能</li>
                <li>添加包裹核验流程（易碎品、危险品检查）</li>
                <li>实现包裹特殊标签标记和状态更新</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.8.0 - 2025-12-20">
              <ol>
                <li>实现收货核验功能</li>
                <li>新增包裹破损检查和状态管理</li>
                <li>完善不合格包裹处理流程</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.7.4 - 2025-12-18">
              <ol>
                <li>实现取件码生成与验证机制</li>
                <li>新增包裹分类和存储空间检查功能</li>
                <li>完成包裹上架流程管理</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.7.3 - 2025-12-16">
              <ol>
                <li>重构用户注册和登录功能</li>
                <li>新增用户个人信息管理模块</li>
                <li>完善多地址管理功能</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.7.2 - 2025-12-14">
              <ol>
                <li>实现用户寄送快递登记功能</li>
                <li>新增寄件人、收件地址、货物类型信息录入</li>
                <li>完成核验通过后自动入库流程</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.7.1 - 2025-12-12">
              <ol>
                <li>实现快递运输环节追踪功能</li>
                <li>新增包裹状态查询和位置跟踪</li>
                <li>完善个人相关包裹信息展示</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.7.0 - 2025-12-10">
              <ol>
                <li>实现用户取快递功能</li>
                <li>新增取件提醒和取件码验证机制</li>
                <li>完善取件操作流程</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.6.3 - 2025-12-08">
              <ol>
                <li>实现包裹流转记录功能</li>
                <li>新增biz_pack_flow表记录包裹状态</li>
                <li>添加寄出核验、入库核验等流转动作</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.6.2 - 2025-12-06">
              <ol>
                <li>完善驿站管理员权限控制</li>
                <li>新增驿站管理员分配功能</li>
                <li>实现驿站管理员操作权限管理</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.6.1 - 2025-12-02">
              <ol>
                <li>优化驿站信息管理界面</li>
                <li>修复驿站申请表单验证bug</li>
                <li>完善驿站地址信息管理</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.6.0 - 2025-11-30">
              <ol>
                <li>实现驿站管理后台功能</li>
                <li>新增驿站信息数据库访问接口</li>
                <li>完善驿站异常行为处理机制</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.5.1 - 2025-11-28">
              <ol>
                <li>实现用户管理后台功能</li>
                <li>新增用户信息数据库访问接口</li>
                <li>完善用户异常行为处理机制</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.5.0 - 2025-11-26">
              <ol>
                <li>重构数据库表结构设计</li>
                <li>新增包裹管理功能</li>
                <li>实现包裹信息录入和查询</li>
                <li>添加包裹状态跟踪功能</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.4.2 - 2025-11-24">
              <ol>
                <li>完善权限管理系统</li>
                <li>新增驿站管理员角色权限</li>
                <li>实现细粒度权限控制</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.4.1 - 2025-11-22">
              <ol>
                <li>优化数据库连接配置</li>
                <li>改进Druid连接池参数</li>
                <li>修复数据库事务处理问题</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.4.0 - 2025-11-20">
              <ol>
                <li>新增数据字典管理功能</li>
                <li>实现驿站状态、包裹状态字典</li>
                <li>完善字典数据配置界面</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.3.3 - 2025-11-17">
              <ol>
                <li>优化界面UI布局</li>
                <li>修复一些显示bug</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.3.2 - 2025-11-16">
              <ol>
                <li>完善安全验证机制</li>
                <li>修复JWT Token处理问题</li>
                <li>增强用户登录安全验证</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.3.1 - 2025-11-14">
              <ol>
                <li>实现用户注册登录流程</li>
                <li>完善用户权限验证</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.3.0 - 2025-11-12">
              <ol>
                <li>重构用户管理模块</li>
                <li>实现用户角色权限分配</li>
                <li>完善用户信息维护功能</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.2.2 - 2025-11-10">
              <ol>
                <li>新增Redis缓存管理</li>
                <li>实现数据缓存和会话管理</li>
                <li>优化系统性能和响应速度</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.2.1 - 2025-11-08">
              <ol>
                <li>完善系统配置管理</li>
                <li>进一步优化项目架构结构</li>
                <li>修复一些已知bug</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.2.0 - 2025-11-06">
              <ol>
                <li>数据库转为阿里云部署</li>
                <li>新增持久层实体类</li>
                <li>给界面加入奶龙元素</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v0.1.0 - 2025-11-04">
              <ol>
                <li>初始demo上线</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="36" :sm="36" :md="18" :lg="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>服务器信息</span>
          </div>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%; table-layout: fixed;">
              <tbody>
              <tr>
                <td class="el-table__cell is-leaf"><div class="cell">CPU 核心数</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.cpu">{{ server.cpu.cpuNum }}</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">CPU 使用率</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.cpu">{{ server.cpu.used }}%</div></td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf"><div class="cell">总内存</div></td>
                <td class="el-table__cell is-leaf"><div class="cell" v-if="server.mem">{{ server.mem.total }}G</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">已用内存</div></td>
                <td class="el-table__cell is-leaf"><div class="cell" v-if="server.mem">{{ server.mem.used }}G</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">内存使用率</div></td>
                <td class="el-table__cell is-leaf"><div class="cell" v-if="server.mem">{{ server.mem.usage }}%</div></td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf"><div class="cell">JVM总内存</div></td>
                <td class="el-table__cell is-leaf"><div class="cell" v-if="server.jvm">{{ server.jvm.total }}M</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">JVM已用内存</div></td>
                <td class="el-table__cell is-leaf"><div class="cell" v-if="server.jvm">{{ server.jvm.used }}M</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">JVM内存使用率</div></td>
                <td class="el-table__cell is-leaf"><div class="cell" v-if="server.jvm">{{ server.jvm.usage }}%</div></td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf"><div class="cell">服务器名称</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.sys">{{ server.sys.computerName }}</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">服务器IP</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.sys">{{ server.sys.computerIp }}</div></td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf"><div class="cell">操作系统</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.sys">{{ server.sys.osName }}</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">系统架构</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.sys">{{ server.sys.osArch }}</div></td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf"><div class="cell">Java名称</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.jvm">{{ server.jvm.name }}</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">Java版本</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.jvm">{{ server.jvm.version }}</div></td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf"><div class="cell">安装路径</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.jvm">{{ server.jvm.home }}</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">项目路径</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.sys">{{ server.sys.userDir }}</div></td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf"><div class="cell">Java 启动时间</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.jvm">{{ server.jvm.startTime }}</div></td>
                <td class="el-table__cell is-leaf"><div class="cell">运行时长</div></td>
                <td colspan="2" class="el-table__cell is-leaf"><div class="cell" v-if="server.jvm">{{ server.jvm.runTime }}</div></td>
              </tr>
              </tbody>
            </table>
          </div>

        </el-card>
      </el-col>
      <el-col :xs="18" :sm="18" :md="9" :lg="6">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>个人信息</span>
        </div>
        <div>
          <div class="text-center">
            <userAvatar />
          </div><br>
          <ul class="list-group list-group-striped">
            <li class="list-group-item">
              <svg-icon icon-class="user" />用户名称
              <div class="pull-right">{{ user.userName }}</div>
            </li>
            <li class="list-group-item">
              <svg-icon icon-class="phone" />手机号码
              <div class="pull-right">{{ user.phonenumber }}</div>
            </li>
            <li class="list-group-item">
              <svg-icon icon-class="email" />用户邮箱
              <div class="pull-right">{{ user.email }}</div>
            </li>
            <li class="list-group-item">
              <svg-icon icon-class="tree" />所属部门
              <div class="pull-right" v-if="user.dept">{{ user.dept.deptName }} / {{ postGroup }}</div>
            </li>
            <li class="list-group-item">
              <svg-icon icon-class="peoples" />所属角色
              <div class="pull-right">{{ roleGroup }}</div>
            </li>
          </ul>
        </div>
      </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getUserProfile } from "@/api/system/user"
import userAvatar from "@/views/system/user/profile/userAvatar.vue"
import { getServer } from "@/api/monitor/server"

export default {
  name: "Index",
  components: {userAvatar},
  created() {
    getUserProfile().then(res => {
      this.user = res.data
      this.roleGroup = res.roleGroup
      this.postGroup = res.postGroup
    })
    this.fetchServer()
  },
  data() {
    return {
      user: {},
      roleGroup: '',
      postGroup: '',
      server: {},
      version: "0.9.1"
    }
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank")
    },
    fetchServer() {
      getServer().then(res => {
        this.server = res.data || {}
      })
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    .update-log-container {
      max-height: 350px;
      overflow-y: auto;

      &::-webkit-scrollbar {
        width: 6px;
        height: 6px;
      }

      &::-webkit-scrollbar-track {
        background: #f1f1f1;
        border-radius: 3px;
      }

      &::-webkit-scrollbar-thumb {
        background: #c1c1c1;
        border-radius: 3px;

        &:hover {
          background: #a8a8a8;
        }
      }
    }

    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

