import request from '@/utils/request'

// 查询驿站信息列表
export function listStation(query) {
  return request({
    url: '/business/station/list',
    method: 'get',
    params: query
  })
}

// 查询驿站信息详细
export function getStation(stationId) {
  return request({
    url: '/business/station/' + stationId,
    method: 'get'
  })
}

// 新增驿站信息
export function addStation(data) {
  return request({
    url: '/business/station',
    method: 'post',
    data: data
  })
}

// 修改驿站信息
export function updateStation(data) {
  return request({
    url: '/business/station',
    method: 'put',
    data: data
  })
}

// 删除驿站信息
export function delStation(stationId) {
  return request({
    url: '/business/station/' + stationId,
    method: 'delete'
  })
}
