// pages/register/phone-register/phone-register.js
Page({
  data: {
    phoneNumber: '',
    password: '',
    confirmPassword: '',
    agree: false,
    showPassword: false,
    showPassword2:false,
    isFormValid: false,
    phoneNumberValid: false,
    passwordValid:false,
    confirmPasswordValid:false,
  },
  // 是否展示密码，确认密码
  togglePasswordVisibility: function () {
    this.setData({
      showPassword: !this.data.showPassword
    });
  },

  togglePasswordVisibility2: function () {
    this.setData({
      showPassword2: !this.data.showPassword2
    });
  },
  // 验证手机号
  validatePhoneNumber :function (phoneNumber) {
    // 中国手机号码的正则表达式
    const regex = /^(13[0-9]|14[5-9]|15[0-3,5-9]|16[6]|17[0-8]|18[0-9]|19[8,9])\d{8}$/;
    // 测试输入的手机号码是否匹配正则表达式
    const res=regex.test(phoneNumber);
    if(res===false){
      return res;
    }else if(res==true){
      return res;
    }else{
      console.warn('手机号验证报错，需要代码修复');
    }
  },
  // 手机号修改
  onPhoneNumberChange: function(e) {
    const phoneNumber = e.detail.value;
    this.setData({
      phoneNumber: phoneNumber,
      phoneNumberValid: this.validatePhoneNumber(phoneNumber)
    });
    this.validateForm();
  },
  // 验证密码
  validatePassword:function (password) {
    // 密码的正则表达式
    // 至少8位，包含数字和英文字母，不包含特殊符号
    const regex = /^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z\d]{8,}$/;
  
    // 测试输入的密码是否匹配正则表达式
    const res= regex.test(password);
    if(res===false){
      return res;
    }else if(res===true){
      return res;
    }else{
      console.warn('密码验证报错，需要代码修复');
    }
  },
  // 密码修改
  onPasswordChange: function(e) {
    const password = e.detail.value;
    this.setData({
      password: password,
      passwordValid: this.validatePassword(password)
    });
    this.setData({
      confirmPasswordValid:this.validateComfirmPassword(this.data.confirmPassword)
    });
    // console.warn('password',this.data.password,this.data.confirmPassword,this.data.passwordValid,this.data.confirmPasswordValid);
    this.validateForm();
  },
  // 验证二次密码
  validateComfirmPassword:function (password) {
    if(password===this.data.password && this.validatePassword(password)){
      return true;
    }else{
      return false;
    }
  },
  // 再次确认密码修改
  onConfirmPasswordChange: function(e) {
    const password = e.detail.value;
    this.setData({
      confirmPassword: password,
      confirmPasswordValid:this.validateComfirmPassword(password)
    });
    // console.warn('password2',this.data.password,this.data.confirmPassword,this.data.passwordValid,this.data.confirmPasswordValid);
    this.validateForm();
  },

  // 同意协议修改
  onAgreeChange: function(e) {
    // console.warn('page-phone-register',e);
    this.setData({ agree: e.detail.value[0] === 'agree' });
    // console.warn('agree?',this.data.agree);
    this.validateForm();
  },

  // 验证所有表单
  validateForm: function() {
    const { phoneNumberValid, passwordValid, confirmPasswordValid, agree } = this.data;
    // console.warn(phoneNumberValid,passwordValid,confirmPasswordValid,agree)
    const isFormValid = phoneNumberValid && passwordValid && confirmPasswordValid && agree;
    // console.warn(isFormValid);
    this.setData({ isFormValid });
  },

  // 启动注册
  onRegister: function() {
    wx.showModal({
      title: '确认',
      content: '您确定要注册吗？',
      success: function(res) {
        if (res.confirm) {
          // 发送 POST 请求到后端
          wx.request({
            url: getApp().globalData.host + '/api/users/register', // 后端 API 地址
            method: 'POST',
            data: JSON.stringify({
              phoneNumber: this.data.phoneNumber,
              password:this.data.password,
              userName:"新用户",
              int:1
            }),
            header: {
              'content-type': 'application/json' // 默认值
            },
            success: function(res) {
              console.log(res);
              if (res.statusCode === 200) {
                // 注册成功
                wx.showToast({
                  title: '注册成功',
                  icon: 'success',
                  duration: 2000,
                  complete: function() {
                    // 在提示结束后跳转到 index 页面
                    wx.switchTab({
                      url: '../../index/index'
                    });
                  }
                });
              } else {
                // 注册失败
                wx.showToast({
                  title: '注册失败',
                  icon: 'none',
                  duration: 2000
                });
              }
            },
            fail: function(error) {
              // 请求失败
              wx.showToast({
                title: '网络错误',
                icon: 'none',
                duration: 2000
              });
            }
          });
        } else if (res.cancel) {
          console.log('用户点击取消');
        }
      }.bind(this) // 注意这里绑定 this
    });
  }
});