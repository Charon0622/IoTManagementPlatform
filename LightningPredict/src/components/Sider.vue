<template>
    <Col span="3" class="a">
    <Menu :theme="theme2">
      <Submenu name="1">
        <template slot="title">
          <Icon type="ios-paper"></Icon>
          主页
        </template>
      </Submenu>
      <Submenu name="2">
        <template slot="title">
          <Icon type="ios-people"></Icon>
          设置
        </template>
      </Submenu>
      <Submenu name="3">
        <template slot="title">
          <Icon type="stats-bars"></Icon>
          统计
        </template>
        <MenuGroup title="使用">
          <MenuItem name="3-1">新增和启动</MenuItem>
          <MenuItem name="3-2">活跃分析</MenuItem>
          <MenuItem name="3-3">时段分析</MenuItem>
        </MenuGroup>
        <MenuGroup title="留存">
          <MenuItem name="3-4">用户留存</MenuItem>
          <MenuItem name="3-5">流失用户</MenuItem>
        </MenuGroup>
      </Submenu>
    </Menu>
    </Col>
</template>

<script>
    export default {
        name: "sider"
    }
</script>

<style scoped>
.a{
  margin-right: 40px;
}
</style>
