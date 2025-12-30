import request from '@/utils/request'

// 查询包裹流转记录列表
export function listFlow(query) {
  return request({
    url: '/business/flow/list',
    method: 'get',
    params: query
  })
}

// 查询包裹流转记录详细
export function getFlow(flowId) {
  return request({
    url: '/business/flow/' + flowId,
    method: 'get'
  })
}

// 新增包裹流转记录
export function addFlow(data) {
  return request({
    url: '/business/flow',
    method: 'post',
    data: data
  })
}

// 修改包裹流转记录
export function updateFlow(data) {
  return request({
    url: '/business/flow',
    method: 'put',
    data: data
  })
}

// 删除包裹流转记录
export function delFlow(flowId) {
  return request({
    url: '/business/flow/' + flowId,
    method: 'delete'
  })
}
