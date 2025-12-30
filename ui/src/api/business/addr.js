import request from '@/utils/request'

// 查询用户地址簿列表
export function listAddr(query) {
  return request({
    url: '/business/addr/list',
    method: 'get',
    params: query
  })
}

// 查询用户地址簿详细
export function getAddr(addrId) {
  return request({
    url: '/business/addr/' + addrId,
    method: 'get'
  })
}

// 新增用户地址簿
export function addAddr(data) {
  return request({
    url: '/business/addr',
    method: 'post',
    data: data
  })
}

// 修改用户地址簿
export function updateAddr(data) {
  return request({
    url: '/business/addr',
    method: 'put',
    data: data
  })
}

// 删除用户地址簿
export function delAddr(addrId) {
  return request({
    url: '/business/addr/' + addrId,
    method: 'delete'
  })
}
