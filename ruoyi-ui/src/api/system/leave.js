import request from '@/utils/request'

// 查询学生请假列表
export function listLeave(query) {
  return request({
    url: '/system/leave/list',
    method: 'get',
    params: query
  })
}

// 查询学生请假详细
export function getLeave(leaveId) {
  return request({
    url: '/system/leave/' + leaveId,
    method: 'get'
  })
}

// 新增学生请假
export function addLeave(data) {
  return request({
    url: '/system/leave',
    method: 'post',
    data: data
  })
}

// 修改学生请假
export function updateLeave(data) {
  return request({
    url: '/system/leave',
    method: 'put',
    data: data
  })
}

// 删除学生请假
export function delLeave(leaveId) {
  return request({
    url: '/system/leave/' + leaveId,
    method: 'delete'
  })
}
