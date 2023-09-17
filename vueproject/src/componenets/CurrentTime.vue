<template>
  <div class="container">
    <div class="date-time">
      <p class="date">当前日期：{{ currentDate }}</p>
      <p class="time">当前时间：{{ currentTime }}</p>
    </div>
    <div class="additional-div">
      <!-- 12个数字 -->
      <div
          class="hour-label"
          v-for="(hour, index) in hours"
          :key="index"
          :style="hourLabelStyle(index)"
      >
        {{ hour }}
      </div>

      <div class="clock">
        <div class="hour">
          <div class="hr" :style="{ transform: 'rotate(' + hourDegrees + 'deg)' }"></div>
        </div>
        <div class="min">
          <div class="mn" :style="{ transform: 'rotate(' + minuteDegrees + 'deg)' }"></div>
        </div>
        <div class="sec">
          <div class="sc" :style="{ transform: 'rotate(' + secondDegrees + 'deg)' }"></div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: 'CurrentTime',
  data() {
    return {
      currentDate: '', // 存储当前日期的数据属性
      currentTime: '', // 存储当前时间的数据属性
      hourDegrees: 0, // 时针旋转角度
      minuteDegrees: 0, // 分针旋转角度
      secondDegrees: 0, // 秒针旋转角度
      hours: ['12', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11'],
    };
  },
  methods: {
    updateDateTime() {
      // 创建一个 Date 对象来获取当前日期和时间
      const now = new Date();

      // 格式化日期为 "年-月-日"
      const formattedDate = now.toLocaleDateString();

      // 格式化时间为 "小时:分钟:秒"，你可以根据需要自定义格式
      const formattedTime = now.toLocaleTimeString();

      // 计算时钟指针的旋转角度
      const hours = now.getHours();
      const minutes = now.getMinutes();
      const seconds = now.getSeconds();

      // 更新数据属性，显示当前日期和时间
      this.currentDate = formattedDate;
      this.currentTime = formattedTime;

      // 计算指针的旋转角度
      this.hourDegrees = (hours % 12) * 30 + (minutes / 60) * 30;
      this.minuteDegrees = minutes * 6 + (seconds / 60) * 6;
      this.secondDegrees = seconds * 6;
    },
    hourLabelStyle(index) {
      // 根据索引计算小时数字的位置
      const totalHours = this.hours.length;
      const rotationAngle = (index / totalHours) * 360; // 计算每个小时数字的角度
      const radius = 60; // 时钟半径
      const radians = (rotationAngle - 90) * (Math.PI / 180); // 角度转弧度
      const x = radius * Math.cos(radians);
      const y = radius * Math.sin(radians);

      return {
        transform: `translate(${x}px, ${y}px)`,
      };
    },
  },
  mounted() {
    // 使用定时器每秒更新一次当前日期和时间
    setInterval(this.updateDateTime, 1000);
    // 初始化时钟指针的位置
    this.updateDateTime();
    },
};
</script>

<style scoped>
/* 这里可以添加组件的样式 */
.container {
  display: flex; /* 使用 Flexbox 布局 */
  align-items: center; /* 垂直居中对齐 */
}

.date-time {
  flex: 1; /* 占据剩余空间 */
}

.date {
  font-size: 20px;
  color: #333;
}

.time {
  font-size: 20px;
  color: #3498db;
}

.additional-div {
  flex: 1; /* 占据剩余空间 */
  height: 112.8px; /* 设置高度，可以根据需要自定义 */
  background-color: white; /* 设置背景颜色 */
  display: flex; /*弹性布局*/
  align-items: center;
  justify-content: center;
}

.clock {
  width: 150px;
  height: 150px;
  /* 利用flex布局，将时针，分针，秒针定位到钟的水平垂直居中的位置 */
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  background-color:  rgba(250, 200, 200, .9);
  /* 添加阴影效果 */
  box-shadow: 0 -1em 1em rgba(0, 0, 0, 0.3) inset,
  0 0.5em 1em rgba(255, 255, 255, 0.1) inset,
  0 -0.5em 1em rgba(255, 255, 255, 0.2),
  0 1em 1em rgba(0, 0, 0, 0.3);
}

/* 利用伪元素在钟的中心添加一个点 */
.clock:before {
  content: '';
  position: absolute;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  z-index: 100;
  box-shadow: 0 0.1em 0.1em rgba(255, 255, 255, 0.3) inset,
  0 -0.1em 0.1em rgba(0, 0, 0, 0.3) inset,
  0 0.2em 0.2em rgba(0, 0, 0, 0.3);
}

.clock:after {
  content: '';
  display: block;
  border-radius: 50%;
  position: absolute;
  box-shadow: 0 0 1em 0.5em rgb(0, 0, 0) inset,
  0 -0.5em 1em 0.5em rgba(0, 0, 0, 0.8);
  width: 125px;
  height: 125px;
  background-color: rgb(226, 214, 214);
  z-index: -99;

}

.clock .hour,
.clock .min,
.clock .sec {
  position: absolute;
}

.clock .hour,
.hr {
  width: 80px;
  height: 80px;
}

.clock .min,
.mn {
  width: 95px;
  height: 95px;
}

.clock .sec,
.sc {
  width: 115px;
  height: 115px;
}

.hr,
.mn,
.sc {
  display: flex;
  justify-content: center;
  position: absolute;
}

.hr:before,
.mn:before,
.sc:before {
  box-shadow: 2px -0.6px 2px rgba(255, 255, 255, 0.1) inset,
  2px 0.5px 2px rgba(0, 0, 0, 0.2) inset,
  -3px 10px 10px rgba(0, 0, 0, 0.3);
}

.hr:before {
  content: '';
  z-index: 10;
  background: red;
  border-radius: 0.5em;
  width: 4px;
  height: 25px;
}

.mn:before {
  content: '';
  z-index: 11;
  background: skyblue ;
  border-radius: 0.4em;
  width: 4px;
  height: 35px;
}

.sc:before {
  content: '';
  z-index: 12;
  background: greenyellow;
  border-radius: 0.3em;
  width: 2px;
  height: 50px;
}

.hour-label {
  position: absolute;
  font-size: 12px;
  font-weight: bold;
  color: #333;
  transform-origin: center bottom;
}



</style>