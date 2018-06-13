


<template>
  <div>
    <my_header></my_header>
     <Row>
       <my_sider></my_sider>
       <Col span="18" offset="1">
        <Tabs :animated="false" value="name1" type="card" class="a">
        <TabPane label="大气电场实时曲线监测" name="name1">
          <Row>
            <Col span="9">
              <Row class="c">
            <Col span="5">
              <div class="b">首选站点</div>
            </Col>
            <Col span="8" >
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
              <Tabs :animated="false" type="card" class="d">
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
            <Col span="14" offset="1">
            </Col>
          </Row>
        </TabPane>
        <TabPane label="列表监测与设置" name="name2">
          <Tabs :animated="false" type="card">
            <TabPane label="大气电场实时监测列表">
              <Table :columns="columns2" :data="data2"></Table>
              <Row>
                <div>
              <vue-highcharts :options="options" ref="lineCharts"></vue-highcharts>
                </div>
              </Row>
              <Graph v-bind:data0="data0" class="e"></Graph>
            </TabPane>
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
  import VueHighcharts from 'vue2-highcharts'
  //import graph from "./Graph.vue"
  //import Graph from "./Graph"
  import my_header from "./Header.vue"
  import my_sider from "./Sider.vue"
  import { logout, isLogin, terminal2, terminal } from '../service/apis'
  export default {
    components: {VueHighcharts,  my_header, my_sider},
    name: "dashboard",

    data () {
      return {
        date:[],
        peak:[],
        options: {
          title: {
            text: 'Monthly Average Temperature',
            x: -20 //center
          },
          xAxis: {
            categories: this.date
          },
          yAxis: {
            title: {
              text: '电流强度 (千安)'
            },
            plotLines: [{
              value: 0,
              width: 1,
              color: '#808080'
            }]
          },
          tooltip: {
            valueSuffix: '°C'
          },
          legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
          },
          series: [{
            name: '电流强度',
            data: this.peak
          }]
        },
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
            title: 'terminal_description',
            key: 'terminal_description'
          },
          {
            title: 'resistance',
            key: 'resistance'
          },
          {
            title: 'earthing',
            key: 'earthing'
          },
          {
            title: 'kk',
            key: 'kk'
          },
          {
            title: 'humidity',
            key: 'humidity'
          },
          {
            title: 'ib',
            key: 'ib'
          },
          {
            title: 'ub',
            key: 'ub'
          },
          {
            title: 'ia',
            key: 'ia'
          },
          {
            title: 'ua',
            key: 'ua'
          },
          {
            title: 'irA',
            key: 'irA'
          },
          {
            title: 'irB',
            key: 'irB'
          },
          {
            title: 'irC',
            key: 'irC'
          },
          {
            title: 'spd',
            key: 'spd'
          },
          {
            title: 'uc',
            key: 'uc'
          },
          {
            title: 'ic',
            key: 'ic'
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
        data2: [],
        data0: [],

      }
    },
    methods: {
      updateCredits: function() {
        var chart = this.$refs.highcharts.chart;
        chart.credits.update({
          style: {
            color: '#' + (Math.random() * 0xffffff | 0).toString(16)
          }
        });
      },
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
      },
      getTerminalData(){
        terminal2().then(res=>{
          this.data2 = res
          console.log(res)
          }
        ).catch(err => {
          console.error(err)
        })
      },
      getTerminal1Data(){
        terminal().then(res=>{
          this.data0 = res
          console.log(res[0].date)
          var miao = []
          var miaodate = []
          for(var i = 0; i < 30; i++){
            miao.push(res[i].peak)
            miaodate.push(res[i].date)
          }
          this.date = miaodate
          this.peak = miao
          console.log(this.peak)
          console.log(this.date)
          }
        ).catch(err => {
          console.error(err)
        })
      }
    },
    mounted () {
      this.getTerminalData()
      this.getTerminal1Data()
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
  .e{
    margin-top: 20px;
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
