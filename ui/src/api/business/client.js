import request from '@/utils/request'

// 查询业务用户列表
export function listClient(query) {
  return request({
    url: '/business/client/list',
    method: 'get',
    params: query
  })
}

// 查询业务用户详细
export function getClient(clientId) {
  return request({
    url: '/business/client/' + clientId,
    method: 'get'
  })
}

// 新增业务用户
export function addClient(data) {
  return request({
    url: '/business/client',
    method: 'post',
    data: data
  })
}

// 修改业务用户
export function updateClient(data) {
  return request({
    url: '/business/client',
    method: 'put',
    data: data
  })
}

// 删除业务用户
export function delClient(clientId) {
  return request({
    url: '/business/client/' + clientId,
    method: 'delete'
  })
}
