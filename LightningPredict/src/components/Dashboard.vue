<template>
  <div>
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
     <Row>
       <Col span="22" offset="1">
      <Tabs value="name1" type="card" class="a">
        <TabPane label="大气电场实时曲线监测" name="name1">
          <Row>
            <Col span="8">
          <Row class="c">
            <Col span="5">
              <div class="b">首选站点</div>
            </Col>
            <Col span="5" >
            <Select v-model="model1" style="width:100px" placement="top"	>
              <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
            </Col>
          </Row>
          <Row>
            <Col span="24">
            <Card dis-hover>
              <Row>
                <Col span="18">
                <Card dis-hover>
                  <p>Content of card</p>
                  <p>Content of card</p>
                  <p>Content of card</p>
                </Card>
                </Col>
                <Col span="4" offset="2">
                <div id="level-1">一级预警</div>
                <div id="level-2">二级预警</div>
                <div id="level-3">三级预警</div>
                <div id="obstacle-of-head">探头故障</div>
                <div id="internet-high">网络高能</div>
                </Col>
              </Row>
            </Card>
            </Col>
          </Row>
            <Row>
              <Col>
              <Tabs type="card" class="d">
                <TabPane label="预警信息查询">
                  <Table :columns="columns1" :data="data1" size="small"></Table>
                </TabPane>
                <TabPane label="状态信息">
                  <Card dis-hover>
                    <Row>
                      <Col span="12">
                      网络状态：
                      </Col>
                    </Row>
                    <Row>
                      <Col span="12">
                      运行状态：
                      </Col>
                    </Row>
                    <Row>
                      <Col span="12">
                      IO状态：
                      </Col>
                    </Row>
                    <Row>
                      <Col span="12">
                      最后上线：
                      </Col>
                    </Row>
                  </Card>
                </TabPane>
                <TabPane label="基本信息">
                  <Card dis-hover>
                    <Row>
                      <Col span="12">
                      网络状态：
                      </Col>
                    </Row>
                    <Row>
                      <Col span="12">
                      名称：
                      </Col>
                    </Row>
                    <Row>
                      <Col span="12">
                      代码：
                      </Col>
                    </Row>
                    <Row>
                      <Col span="12">
                      经纬度：
                      </Col>
                    </Row>
                    <Row>
                      <Col span="12">
                      区域名：
                      </Col>
                    </Row>
                    <Row>
                      <Col span="12">
                      地区名：
                      </Col>
                    </Row>
                  </Card>
                </TabPane>
              </Tabs>
              </Col>
            </Row>
            </Col>
          </Row>
        </TabPane>
        <TabPane label="列表监测与设置" name="name2">
          <Tabs type="card">
            <TabPane label="大气电场实时监测列表"><Table :columns="columns2" :data="data2"></Table></TabPane>
            <TabPane label="雷达系统参数设置">标签二的内容</TabPane>
            <TabPane label="闪电定位系统设置">标签三的内容</TabPane>
            <TabPane label="网络化预警参数设置">标签四的内容</TabPane>
          </Tabs>
        </TabPane>
        <TabPane label="地图监测" name="name3"></TabPane>
      </Tabs>
       </Col>
     </Row>
  </div>
</template>

<script>
  import graph from "./Graph.vue"
  import Graph from "./Graph";
  import { logout, isLogin } from '../service/apis'

  export default {
    components: {Graph},
    name: "dashboard",
    data () {
      return {
        cityList: [
          {
            value: 'New York',
            label: 'New York'
          },
          {
            value: 'London',
            label: 'London'
          },
          {
            value: 'Sydney',
            label: 'Sydney'
          },
          {
            value: 'Ottawa',
            label: 'Ottawa'
          },
        ],
        model1: '',
        columns1: [
          {
            title: 'Name',
            key: 'name'
          },
          {
            title: 'Date',
            key: 'date'
          }
        ],
        columns2: [
          {
            title: 'Name',
            key: 'name'
          },
          {
            title: 'Date',
            key: 'date'
          }
        ],
        data1: [
          {
            name: 'John Brown',
            date: '2016-10-03'
          },
          {
            name: 'Jim Green',
            date: '2016-10-01'
          },
          {
            name: 'Joe Black',
            date: '2016-10-02'
          },
          {
            name: 'Jon Snow',
            date: '2016-10-04'
          }
        ],
        data2: [
          {
            name: 'John Brown',
            date: '2016-10-03'
          },
          {
            name: 'Jim Green',
            date: '2016-10-01'
          },
          {
            name: 'Joe Black',
            date: '2016-10-02'
          },
          {
            name: 'Jon Snow',
            date: '2016-10-04'
          }
        ]
      }
    },
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
          console.log(res)
          res = JSON.parse(res)
          if (res.status === 'OK') {
            this.$session.remove('username')
            this.$router.push({name: 'Login'})
          }
        }).catch(err => {
          console.error(err)
        })
      }
    },
    created () {
      console.log(this.$session.get('groups'))
      // isLogin(this.$session.get('username')).then(res => {
      //   console.log(res)
      //   res = JSON.parse(res)
      //   if (res.status === 'FAIL') {
      //     this.$router.push({name: 'Login'})
      //   }
      // }).catch(err => {})
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
  .background-size{
    position:absolute;
    width: 100%;
    height: 100%;
    background-color: #fff;
  }
  .main-size{
    height: 100%;
  }
  .dashboard-search{
    margin-top: 10px;
    margin-bottom: 10px;
    width: 200px;
    align-self: flex-end;
  }
  .a{
    background-color: #fff;
    margin-top: 10px;
  }
  .b{
    margin-bottom: -10px;
    font-size: 14px;
    margin-top: 5px
  }

  .c{
    margin-bottom: 10px;
    margin-left: -5px;
  }
  .d{
    margin-top: 15px;
  }

  #level-1
  {
    background-color:yellow;
  }
  #level-2
  {
    background-color:#ffb90f;
  }
  #level-3
  {
    background-color:red;
  }
  #obstacle-of-head
  {
    background-color:#f7f7f7;
  }
  #internet-high
  {
    background-color:#d6d6d6;
  }

</style>
