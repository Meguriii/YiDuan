import request from '@/utils/request'

// 查询拒收/退件记录列表
export function listRejection(query) {
  return request({
    url: '/business/rejection/list',
    method: 'get',
    params: query
  })
}

// 查询拒收/退件记录详细
export function getRejection(rejectId) {
  return request({
    url: '/business/rejection/' + rejectId,
    method: 'get'
  })
}

// 新增拒收/退件记录
export function addRejection(data) {
  return request({
    url: '/business/rejection',
    method: 'post',
    data: data
  })
}

// 修改拒收/退件记录
export function updateRejection(data) {
  return request({
    url: '/business/rejection',
    method: 'put',
    data: data
  })
}

// 删除拒收/退件记录
export function delRejection(rejectId) {
  return request({
    url: '/business/rejection/' + rejectId,
    method: 'delete'
  })
}
