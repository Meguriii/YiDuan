import request from '@/utils/request'

// 查询驿站列表
export function listStation(query) {
  return request({
    url: '/business/station/list',
    method: 'get',
    params: query
  })
}

// 查询驿站详细
export function getStation(stationId) {
  return request({
    url: '/business/station/' + stationId,
    method: 'get'
  })
}

// 新增驿站（注册申请）
export function addStation(data) {
  return request({
    url: '/business/station',
    method: 'post',
    data: data
  })
}

// 修改驿站
export function updateStation(data) {
  return request({
    url: '/business/station',
    method: 'put',
    data: data
  })
}

// 删除驿站
export function delStation(stationId) {
  return request({
    url: '/business/station/' + stationId,
    method: 'delete'
  })
}

// 提交驿站申请
export function addStationApply(data) {
  return request({
    url: '/business/stationApply',
    method: 'post',
    data: data
  })
}

// 查询驿站申请列表
export function listStationApply(query) {
  return request({
    url: '/business/stationApply/list',
    method: 'get',
    params: query
  })
}

// 审核驿站申请
export function auditStationApply(applyId, data) {
  return request({
    url: '/business/stationApply/audit/' + applyId,
    method: 'put',
    data: data
  })
}
