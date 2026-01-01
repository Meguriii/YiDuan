import request from '@/utils/request'

// 查询包裹表列表
export function listPack(query) {
  return request({
    url: '/business/pack/list',
    method: 'get',
    params: query
  })
}

// 查询包裹表列表(包含寄件人信息)
export function listPackWithSender(query) {
  return request({
    url: '/business/pack/listWithSender',
    method: 'get',
    params: query
  })
}

// 查询包裹表详细
export function getPack(packId) {
  return request({
    url: '/business/pack/' + packId,
    method: 'get'
  })
}

// 查询包裹表详细(包含寄件人信息)
export function getPackWithSender(packId) {
  return request({
    url: '/business/pack/detail/' + packId,
    method: 'get'
  })
}

// 新增包裹表
export function addPack(data) {
  return request({
    url: '/business/pack',
    method: 'post',
    data: data
  })
}

// 修改包裹表
export function updatePack(data) {
  return request({
    url: '/business/pack',
    method: 'put',
    data: data
  })
}

// 删除包裹表
export function delPack(packId) {
  return request({
    url: '/business/pack/' + packId,
    method: 'delete'
  })
}

// 发货核验
export function verifySend(packId, data) {
  return request({
    url: '/business/pack/verifySend/' + packId,
    method: 'put',
    data: data
  })
}

// 发货核验不通过
export function rejectSend(packId, data) {
  return request({
    url: '/business/pack/rejectSend/' + packId,
    method: 'put',
    data: data
  })
}

// 收货核验
export function verifyReceive(packId, data) {
  return request({
    url: '/business/pack/verifyReceive/' + packId,
    method: 'put',
    data: data
  })
}

// 收货核验不通过
export function rejectReceive(packId, data) {
  return request({
    url: '/business/pack/rejectReceive/' + packId,
    method: 'put',
    data: data
  })
}

// 生成取件码并上架
export function generatePickupCode(packId) {
  return request({
    url: '/business/pack/generatePickupCode/' + packId,
    method: 'put'
  })
}

// 取件
export function pickup(pickupCode) {
  return request({
    url: '/business/pack/pickup/' + pickupCode,
    method: 'put'
  })
}

// 超级管理员修改包裹状态
export function updatePackStatus(packId, data) {
  return request({
    url: '/business/pack/updateStatus/' + packId,
    method: 'put',
    data: data
  })
}

// 查询用户相关包裹
export function getMyPacks(userId) {
  return request({
    url: '/business/pack/myPacks/' + userId,
    method: 'get'
  })
}
