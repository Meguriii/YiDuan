import request from '@/utils/request'

// 查询包裹表列表
export function listPack(query) {
  return request({
    url: '/business/pack/list',
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
