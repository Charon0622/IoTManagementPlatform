<template>
  <div class="backgroud">
      <Row type="flex" justify="center">
        <i-col span="6" class="box">
          <Card class="login">
            <div style="text-align:center">
              <h1 class="title">Lightning</h1>
              <Tabs active-key="key1" class="tab">
                <Tab-pane label="Sign In" key="key1">
                  <div class="id">
                    <i-input icon="ios-person" placeholder="ID" style="width: 188px;" v-model="username"></i-input>
                  </div>
                  <div class="password">
                    <i-input icon="key" placeholder="Password" style="width: 188px;" type="password" v-model="password"></i-input>
                  </div>
                  <i-button type="primary" class="button" @click="login">Sign In</i-button>
                </Tab-pane>
                <Tab-pane label="Sign Up" key="key2">
                  <div class="id" style="margin-top: 20px !important;">
                    <i-input icon="ios-person" placeholder="ID" style="width: 188px;" v-model="newUsername"></i-input>
                  </div>
                  <div class="password">
                    <i-input icon="key" placeholder="Password" style="width: 188px;" type="password" v-model="newPassword"></i-input>
                  </div>
                  <div class="re-password" style="margin-top: 30px">
                    <i-input icon="key" placeholder="Confirm Password" style="width: 188px;" type="password" v-model="reNewPassword"></i-input>
                  </div>
                  <i-button type="primary" class="button" @click="register">Sign Up</i-button>
                </Tab-pane>
              </Tabs>
            </div>
          </Card>
        </i-col>
      </Row>
  </div>
</template>

<script>

    import { login, isLogin, register } from '../service/apis'

    export default {
      name: "login",
      data () {
        return {
          username: '',
          password: '',
          newUsername: '',
          newPassword: '',
          reNewPassword: ''
        }
      },
      methods: {
        login () {
          if (this.username === '') {
            this.$Notice.warning({
              title: 'Input username'
            })
          } else if (this.password === '') {
            this.$Notice.warning({
              title: 'Input password'
            })
          }
          login(this.username, this.password).then(res => {
            console.log(res)
            res = JSON.parse(res)
            if (res.status === 'OK') {
              if (res.msg === 'SUCCESS') {
                this.$Notice.success({
                  title: 'Login Success',
                  description: 'Welcome, ' + this.username
                })
                this.$session.set('username', this.username)
                this.$session.set('groups', res.detail.groups)
                this.$router.push({name: 'Dashboard'})
              }
            } else {

            }
          }).catch(err => {
            console.error(err)
          })
        },
        register () {
          if (this.newUsername === '') {
            this.$Notice.warning({
              title: 'Input username'
            })
            return
          } else if (this.newPassword === '') {
            this.$Notice.warning({
              title: 'Input password'
            })
            return
          } else if (this.newPassword !== this.reNewPassword) {
            this.$Notice.warning({
              title: 'Password doesn\'t match'
            })
            return
          }

          register(this.newUsername, this.newPassword).then(res => {
            console.log(res)
            res = JSON.parse(res)
            if (res.status === 'OK') {
              this.$Notice.success({
                title: this.newUsername + ' created'
              })
              this.username = this.newUsername
              this.password = this.newPassword
              this.login()
            } else if (this.msg === 'FAIL') {
              this.$Notice.warning({
                title: this.newUsername + ' exists'
              })
            }
            if (res.status === 'FAIL') {
              this.$Notice.error({
                title: 'Register Fail'
              })
            }
          }).catch(err => {
            console.error(err)
          })

        }
      },
      created () {
        isLogin(this.$session.get('username')).then(res => {
          console.log(res)
          res = JSON.parse(res)
          if (res.status === 'OK') {
            this.$router.push({name: 'Dashboard'})
          }
        }).catch(err => {})
      }
    }
</script>

<style scoped>
  .backgroud{
    position:absolute;
    height: 100%;
    width: 100%;
    background: #f5f7f9;
  }
  .box{
    height: 470px;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    width: 400px;
  }
  .login{
    height: 100%;
    width: 100%;
    background: #ffffff;
  }
  .title{
    margin-top: 45px;
    color: #3399ff;
    font-size: 250%;
  }
  .font{
    font-size: 15px;
    color: #8590a6;
    font-weight: bold;
  }
  .id{
    margin-top: 45px;
  }
  .password{
    margin-top: 30px;
  }
  .button{
    margin-top: 35px;
    width: 188px;
    font-weight: bold;
  }
  .tab{
    margin-top: 20px;
    width: 188px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
