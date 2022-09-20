package com.proj.common;

/**
 * @Author JerryLiu
 * @Date 2022/9/2 11:33
 * @PackageName:com.proj.common
 * @ClassName: ECode
 * @Description: TODO
 * @Version 1.0
 */
public enum ErrorCode {
    E5011{
        public Integer code(){
            return 5011;
        }
        public String description(){
            return "未填写手机号！";
        }
    },
    E5012{
        public Integer code(){
            return 5012;
        }
        public String description(){
            return "手机号不正确！";
        }
    },
    E5013{
        public Integer code(){
            return 5013;
        }
        public String description(){
            return "未填写密码！";
        }
    },
    E5014{
        public Integer code(){
            return 5014;
        }
        public String description(){
            return "密码格式有误！密码应含有数字和字母并且在8-15位之间！";
        }
    },
    E5015{
        public Integer code(){
            return 5015;
        }
        public String description(){
            return "该手机号已注册！";
        }
    },
    E5021{
        public Integer code(){
            return 5021;
        }
        public String description(){
            return "账号或密码错误！";
        }
    },
    E5031{
        public Integer code(){
            return 5031;
        }
        public String description(){
            return "没有该用户信息！";
        }
    },
    E5032{
        public Integer code(){
            return 5032;
        }
        public String description(){
            return "用户未登录！";
        }
    },
    E5041{
        public Integer code(){
            return 5041;
        }
        public String description(){
            return "未找到用户！";
        }
    },
    E5051{
        public Integer code(){
            return 5051;
        }
        public String description(){
            return "用户未拥有账号，请先创建账号！";
        }
    },
    E5052{
        public Integer code(){
            return 5052;
        }
      public String description() {
          return "余额不足！";
      }
    },
    E5053{
        public Integer code(){
            return 5053;
        }
        public String description() {
            return "转账金额不能为负！";
        }
    },
    E5054{
        public Integer code(){
            return 5054;
        }
        public String description() {
            return "转账金额不能为零！";
        }
    },
    E5055{
        public Integer code(){
            return 5055;
        }
        public String description() {
            return "不能以投资账户为目标！";
        }
    },
    E5056{
        public Integer code(){
            return 5056;
        }
        public String description() {
            return "用户未拥有储蓄账户，请先创建储蓄账户！";
        }
    },
    E5057{
        public Integer code(){
            return 5057;
        }
        public String description() {
            return "该账户不是您的账户，请重新输入！";
        }
    },
    E5061{
        public Integer code(){
            return 5061;
        }
        public String description() {
            return "未找到产品信息！";
        }
    },
    E5062{
        public Integer code(){
            return 5062;
        }
        public String description() {
            return "该产品为交割产品，不可交易！";
        }
    },
    E5063{
        public Integer code(){
            return 5063;
        }
        public String description() {
            return "未持有该数量产品！";
        }
    },
    E5064{
        public Integer code(){
            return 5064;
        }
        public String description() {
            return "该账户下未持有资产！";
        }
    },
    E5065{
        public Integer code(){
            return 5065;
        }
        public String description() {
            return "产品库存不足！";
        }
    },
    E5066{
        public Integer code(){
            return 5066;
        }
        public String description() {
            return "登录已过期，请重新登录！";
        }
    },
    E5067{
        public Integer code(){
            return 5067;
        }
        public String description() {
            return "未到交割日！";
        }
    },
    E5068{
        public Integer code(){
            return 5068;
        }
        public String description() {
            return "非交割产品，不需要变更！";
        }
    },
    E5071{
        public Integer code(){
            return 5071;
        }
        public String description() {
            return "产品金额不能为负！";
        }
    },
    E5072{
        public Integer code(){
            return 5072;
        }
        public String description() {
            return "产品数量不能为负！";
        }
    },
    E5073{
        public Integer code(){
            return 5073;
        }
        public String description() {
            return "未输入交割日期！";
        }
    };
    public abstract Integer code();
    public abstract String description();
}
