import request from '@/utils/request'

// 查询驿站-管理员映射列表
export function listMap(query) {
  return request({
    url: '/business/map/list',
    method: 'get',
    params: query
  })
}

// 查询驿站-管理员映射详细
export function getMap(stationId) {
  return request({
    url: '/business/map/' + stationId,
    method: 'get'
  })
}

// 新增驿站-管理员映射
export function addMap(data) {
  return request({
    url: '/business/map',
    method: 'post',
    data: data
  })
}

// 修改驿站-管理员映射
export function updateMap(data) {
  return request({
    url: '/business/map',
    method: 'put',
    data: data
  })
}

// 删除驿站-管理员映射
export function delMap(stationId) {
  return request({
    url: '/business/map/' + stationId,
    method: 'delete'
  })
}
