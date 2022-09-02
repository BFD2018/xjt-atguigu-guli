import request from '@/utils/request'
export default {
  //1 生成统计数据
  createStatisticsData(day) {
    return request({
      url: '/statistics/service/registerCount/'+day,
      method: 'post'
    })
  },
  //2 获取统计数据
  getStatisticsData(searchObj) {
    return request({
      url: `/statistics/service/showData/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
      method: 'get'
    })
  }
}
