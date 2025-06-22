import request from '@/utils/request'

// 查询这是词, 和词汇做链接列表
export function listVocabulary(query) {
  return request({
    url: '/remember/vocabulary/list',
    method: 'get',
    params: query
  })
}

// 查询这是词, 和词汇做链接详细
export function getVocabulary(vocabularyId) {
  return request({
    url: '/remember/vocabulary/' + vocabularyId,
    method: 'get'
  })
}

// 新增这是词, 和词汇做链接
export function addVocabulary(data) {
  return request({
    url: '/remember/vocabulary',
    method: 'post',
    data: data
  })
}

// 修改这是词, 和词汇做链接
export function updateVocabulary(data) {
  return request({
    url: '/remember/vocabulary',
    method: 'put',
    data: data
  })
}

// 删除这是词, 和词汇做链接
export function delVocabulary(vocabularyId) {
  return request({
    url: '/remember/vocabulary/' + vocabularyId,
    method: 'delete'
  })
}
