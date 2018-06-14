


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
            <Select style="width:100px" placement="top"	>
              <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
            </Col>
          </Row>
              <Row>
            <Col span="24">
            <Card dis-hover>
              <Row>
                <Col span="18">
                <Card dis-hover class="card">
                  <span class="zi">+07.34</span>
                  <span class="zi2">Kv/m</span>
                  <p class="zi2 zi3">
                    <span>转速</span>
                    <span style="margin-left: 5px">0</span>
                    <span style="margin-left: 5px">闪电</span>
                    <span style="margin-left: 5px">0</span>
                  </p>
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
          <Tabs :animated="false" type="card"
          >
            <TabPane label="大气电场实时监测列表">
              <Row>
                <Col span="14" >
              <Button class="shuaxin">刷新表格</Button>
                </Col>
              </Row>
              <Table :columns="columns0" :data="data0"  height="600"></Table>
              <Row>
                <Row>
                  <Col span="14" >
                  <Button class="shuaxintu"> 刷新图</Button>
                  </Col>
                </Row>
                <div>
              <vue-highcharts :options="options" ref="lineCharts"></vue-highcharts>
                </div>
              </Row>
              <Graph v-bind:data0="data0" class="e"></Graph>
            </TabPane>
            <TabPane label="雷达系统参数设置">
              <Row>
              <Col span="14" >
              <Button class="shuaxin" @click="getTerminalData2">刷新表格</Button>
              </Col>
            </Row>
              <Table :columns="columns2" :data="data2" @click="getTerminalData2"></Table>
            </TabPane>
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
  const asyncData = {
    name: '电流强度(千安)',
    marker: {
      symbol: 'square'
    },
    data: []
  }
  export default {
    components: {VueHighcharts,  my_header, my_sider},
    name: "dashboard",

    data () {
      return {
        options: {
          chart: {
            type: 'spline'
          },
          title: {
            text: '最新30条电流数据'
          },
          xAxis: {
            categories:[]
          },
          yAxis: {
            values:[]
          },
          tooltip: {
            crosshairs: true,
            shared: true
          },
          credits: {
            enabled: false
          },
          plotOptions: {
            spline: {
              marker: {
                radius: 4,
                lineColor: '#666666',
                lineWidth: 1
              }
            }
          },
          series: []
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
            title: 'Time',
            key: 'time'
          },
          {
            title: 'Alarm',
            key: 'alarm'
          }
        ],

        columns0: [
          {
            title: 'terminal_description',
            key: 'terminal_description'
          },
          {
            title: 'peak',
            key: 'peak'
          },

          {
            title: 'lasting_time',
            key: 'lasting_time'
          },

          {
            title: 'polarity',
            key: 'polarity'
          },
          {
            title: 'date',
            key: 'date'
          },
          {
            title: 'unit1',
            key: 'unit1'
          },
          {
            title: 'unit2',
            key: 'unit2'
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
            time: '2015-03-13 13:10:02',
            alarm: '雷电三级预警'
          },
          {
            time: '2015-03-13 13:10:02',
            alarm: '雷电三级预警'
          },
          {
            time: '2015-03-13 13:10:02',
            alarm: '雷电三级预警'
          },
          {
            time: '2015-03-13 13:10:02',
            alarm: '雷电三级预警'
          },
          {
            time: '2015-03-13 13:10:02',
            alarm: '雷电三级预警'
          },
          {
            time: '2015-03-13 13:10:02',
            alarm: '雷电三级预警'
          },
          {
            time: '2015-03-13 13:10:02',
            alarm: '雷电三级预警'
          },
        ],
        data2: [],
        data0: [],
        count: 1,
      }
    },
    methods: {
      load(){
        let lineCharts = this.$refs.lineCharts;
        lineCharts.delegateMethod('showLoading', 'Loading...');
        setTimeout(() => {
          lineCharts.addSeries(asyncData);
          lineCharts.hideLoading();
        }, 2000)
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
          // console.log(res)
          res = JSON.parse(res)
          if (res.status === 'OK') {
            this.$session.remove('username')
            this.$router.push({name: 'Login'})
          }
        }).catch(err => {
          console.error(err)
        })
      },
      getTerminalData2(){
        terminal2().then(res=>{
          this.data2 = res
          // console.log(res)
          }
        ).catch(err => {
          console.error(err)
        })
      },
      getTerminalData(){
        terminal().then(res=>{
            this.data0 = res
            // console.log(res)
          }
        ).catch(err => {
          console.error(err)
        })
      },
      timer() {
        if (this.count > 0) {
          this.count++;
        }
        var miao = []
        var miaodate = []
        // console.log(this.count)
        terminal().then(res=>{
            this.data0 = res
            // console.log(res[0].date)
            for(var i = 0; i < 30; i++){
              miao.push(res[i].peak)
              miaodate.push(res[i].date)
            }
            this.peak = miao
            console.log(this.peak)
            console.log(miaodate)
          }
        ).catch(err => {
          console.error(err)
        })
        return miaodate
      },
      timervalue() {
        if (this.count > 0) {
          this.count++;
        }
        var miao = []
        var miaodate = []
        // console.log(this.count)
        terminal().then(res=>{
            this.data0 = res
            // console.log(res[0].date)
            for(var i = 0; i < 30; i++){
              miao.push(res[i].peak)
              miaodate.push(res[i].date)
            }
            this.peak = miao
            // console.log(this.peak)
            // console.log(miaodate)
          }
        ).catch(err => {
          console.error(err)
        })
        return miao
      }
    },
    mounted () {
      this.load();
      this.getTerminalData();
      this.getTerminalData2();
      this.$nextTick(function () {
          setInterval(this.timer, 1000);
        setInterval(this.timervalue, 1000);
        })
    },
    beforeMount(){
      var date = this.timer();
      var peak = this.timervalue();
      date=date.reverse()
      console.log(date)
      console.log(peak)
      this.options['xAxis']['categories'] = date;
      //this.options['yAxis']['value'] = peak;
      asyncData.data = peak.reverse()
    },
    created () {
      // console.log(this.$session.get('groups'))
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
  .zi{
    color: blue;
    font-size: 43px;
    font-weight: 600;
  }
  .zi2{
    color: blue;
    font-size: 15px;
    font-weight: 600;
  }
  .zi3{
    margin-left: 80px;
  }
  .card{
    background-color:red;
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
  .shuaxin{
    float: left;
    margin-bottom: 10px;
  }

  .shuaxintu{
    float: left;
    margin-top: 10px;
  }

</style>
