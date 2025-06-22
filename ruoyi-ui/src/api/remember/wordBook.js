import request from '@/utils/request'

// 查询词汇书列表
export function listWordBook(query) {
  return request({
    url: '/remember/wordBook/list',
    method: 'get',
    params: query
  })
}

// 查询词汇书详细
export function getWordBook(wordBookId) {
  return request({
    url: '/remember/wordBook/' + wordBookId,
    method: 'get'
  })
}

// 新增词汇书
export function addWordBook(data) {
  return request({
    url: '/remember/wordBook',
    method: 'post',
    data: data
  })
}

// 修改词汇书
export function updateWordBook(data) {
  return request({
    url: '/remember/wordBook',
    method: 'put',
    data: data
  })
}

// 删除词汇书
export function delWordBook(wordBookId) {
  return request({
    url: '/remember/wordBook/' + wordBookId,
    method: 'delete'
  })
}
