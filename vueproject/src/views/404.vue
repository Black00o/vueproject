<template>
  <div class="not-found">
    <div class="content">
      <h1 class="title">404</h1>
      <p class="message">哎呀！您走失了...</p>
      <router-link to="/" class="home-link">返回首页</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "404"
}
</script>

<style scoped>
.not-found {
  background-color: #f0f0f0;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: 'Segoe UI', sans-serif;
}

.content {
  text-align: center;
}

.title {
  font-size: 100px;
  margin: 0;
  color: #f56c6c;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
}

.message {
  font-size: 24px;
  margin: 20px 0;
  color: #333;
}

.home-link {
  display: inline-block;
  padding: 10px 20px;
  background-color: #f56c6c;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.home-link:hover {
  background-color: #ff4141;
}
</style>
