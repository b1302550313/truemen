// components/comp-term-service/comp-term-service.js
Component({

  /**
   * 组件的属性列表
   */
  properties: {
  },

  /**
   * 组件的初始数据
   */
  data: {
    agree:false,
  },

  /**
   * 组件的方法列表
   */

  methods: {
    onAgreeChange: function(e) {
      // console.warn('comp-term-sevice',e);
      const res=e.detail.value[0];
      this.setData({ agree: res === 'agree' });
      this.triggerEvent('agreeChanged',e.detail); // 触发事件
    },
  }
})