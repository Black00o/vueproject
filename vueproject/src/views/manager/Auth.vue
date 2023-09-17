<template>
  <div class="auth-container">
    <div class="auth-content">
      <h1 class="auth-title">抱歉，您没有权限访问该页面</h1>
      <p class="auth-message">请确保您已登录并具有访问权限。</p>
      <router-link to="/" class="auth-link">返回首页</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "Auth",
};
</script>

<style scoped>
.auth-container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #001529;
}

.auth-content {
  text-align: center;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
  background-color: #fff;
  color: #333;
}

.auth-title {
  font-size: 28px;
  margin-bottom: 20px;
  color: #f56c6c;
}

.auth-message {
  font-size: 18px;
  margin-bottom: 30px;
  color: #777;
}

.auth-link {
  font-size: 16px;
  color: #f56c6c;
  text-decoration: none;
  transition: color 0.3s ease-in-out;
}

.auth-link:hover {
  color: #d9534f;
}
</style>
