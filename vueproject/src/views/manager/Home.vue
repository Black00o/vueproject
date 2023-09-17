<template>
<div>
  <div style="box-shadow: 0 0 10px rgba(0,0,0,.1); padding: 10px 20px; border-radius: 5px; margin-bottom: 10px">
    早安，{{user.name}},祝你开心每一天！
  </div>

  <div style="display: flex;justify-content: flex-end;align-items: center">
    <el-card style="width: 30%;display: flex">
      <div slot="header" class="clearfix">
        <span>Time</span>
      </div>
      <CurrentTime></CurrentTime>
    </el-card>
  </div>

</div>
</template>

<script>
import CurrentTime from '@/componenets/CurrentTime'
export default {
  name: "Home",
  components: {CurrentTime},

  data(){
    return {
      user:JSON.parse(localStorage.getItem('honey-user') || '{}'),
    }
  }
}
</script>

<style scoped>

</style>