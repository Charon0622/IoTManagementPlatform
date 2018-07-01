<template>
  <Header>
    <Menu mode="horizontal" @on-select="dealSelect">
      <div class="demo-avatar user-div">
        <span class="greeting">Hello, Ygritte</span>
      </div>
      <div class="layout-nav">
        <MenuItem name="logout">
          <Icon type="person"></Icon>
          Logout
        </MenuItem>
      </div>
    </Menu>
  </Header>
</template>

<script>
    import { logout } from "../service/apis";

    export default {
      name: "header",
      methods: {
        dealSelect (name) {
          switch (name) {
            case 'logout':
              this.userLogout()
              break
          }
        },
        userLogout () {
          logout().then(res => {
            // console.log(res)
            res = JSON.parse(res)
            if (res.status === 'OK') {
              this.$session.remove('username')
              this.$router.push({name: 'Login'})
            }
          }).catch(err => {
            console.error(err)
          })
        }
      }
    }
</script>

<style scoped>
  .layout{
    background: #fff;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
    height: 100%;
  }

  .layout-nav{
    margin: 0 auto;
    margin-right: 20px;
    float: right;
  }
  .ivu-layout-header{
    background: #ffffff;
  }
  .mybread{
    float: left;
  }
  .ivu-menu-horizontal {
    height: 64px;
    line-height: 64px;
  }
  .user-avatar{
    color: #f56a00;
    background-color: #fde3cf;
  }
  .user-div{
    float: left;
    top:50%;
  }
  .greeting{
    margin-left: 10px;
  }
</style>
