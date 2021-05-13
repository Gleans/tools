export default class DateUtil{
  /**
   * 时间计算-增加月份
   * @returns {number} 计算后的时间戳
   * @param date 传进来的时间戳
   * @param monthNum 增加的月数
   */
  static addMonth(date: number,monthNum: number){
    const dateX = new Date(date);
    console.log(dateX)
    let nextMonth = dateX.getMonth()+monthNum;
    if(nextMonth>12){
      nextMonth -= 12;
      dateX.setFullYear(dateX.getFullYear()+1);
    }
    dateX.setMonth(nextMonth);
    return dateX.getTime();
  }
}
