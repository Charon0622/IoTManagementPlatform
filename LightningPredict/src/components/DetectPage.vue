<template>
  <div id="detect-page">
    <h1 id="title">{{title}}</h1>
    <div id="show-area">
      <Tabs :animated="false" id="up-table">
        <TabPane :label="'大气电场实时曲线监测'">
          <p class="peer-station">首选站点：</p>
          <Select v-model="stationName" v-on:change="stationChange" id="station-list">
            <Option v-for="station in stationList" :value="station.stationName" :key="station.stationName">{{station.stationName}}</Option>
          </Select>
        </TabPane>
        <TabPane :label="'列表监测与设置'">

        </TabPane>
        <TabPane :label="'地图监测'">

        </TabPane>
      </Tabs>
      <div id="down-left">
        <h1 id="syc-with-select">{{stationName}}</h1>
        <div id="detail-message-show"></div>
        <div class="buttons">
          <div id="level-1">一级预警</div>
          <div id="level-2">二级预警</div>
          <div id="level-3">三级预警</div>
          <div id="obstacle-of-head">探头故障</div>
          <div id="internet-high">网络高能</div>
        </div>
        <Tabs :animated="false" id="down-table">
          <TabPane :label="'预警信息查询'">

          </TabPane>
          <TabPane :label="'状态信息'" id="stateInfoTab">
            <p>网络状态：{{stateInfo.netState}}</p>
            <p>运行状态：{{stateInfo.runningState}}</p>
            <p>IO状态：{{stateInfo.IOState}}</p>
            <p>最后上线：{{stateInfo.lastOnlineTime}}</p>
          </TabPane>
          <TabPane :label="'基本信息'" id="basicInfoTab">
            <p>名称:{{basicInfo.name}}</p>
            <p>代码：{{basicInfo.symbol}}</p>
            <p>经纬度：{{basicInfo.longitude}},{{basicInfo.latitude}}</p>
            <p>区域名：{{basicInfo.domainRegionName}}</p>
            <p>地区名：{{basicInfo.subRegionName}}</p>
          </TabPane>
        </Tabs>
      </div>
    </div>
  </div>
</template>

<script>
  var data={
    //stationList数据从数据库读取
    stationList:[
      {
        stationName:'station1'
      },
      {
        stationName:'station2'
      },
      {
        stationName:'station3'
      },
      {
        stationName:'station4'
      },
      {
        stationName:'station5'
      }
    ],
    stationName:'站点名',
    stateInfo:
      {
        netState:'',
        runningState:'',
        IOState:'',
        lastOnlineTime:''
      },
    basicInfo:
      {
        name:'',
        symbol:'',
        longitude:0.0,
        latitude:0.0,
        domainRegionName:'',
        subRegionName:''
      },
    title:'雷电监测预警'
  };

  export default {
    name: "MainPage",
    data(){
      return data;
    },
    methods:{
      stationChange(){
      }
    }
  }
</script>

<style scoped>
  #detect-page
  {
    background-color:#fffafa;
  }
  #title
  {
    margin-top:50px;
    font-family:'Microsoft YaHei';
    font-size:25pt;
    background-color:lightblue;
  }
  #show-area
  {
    width:80%;
    margin-left:auto;
    margin-right:auto;
    margin-top:50px;
    border:2px double lightblue;
    overflow:hidden;
  }
  #up-table
  {
    width:100%;
    height:200px;
    overflow: scroll;
    border-bottom:5px double lightblue;
  }
  .peer-station,#station-list
  {
    float:left;
  }
  .peer-station
  {
    width:80px;
    height:10px;
  }
  #station-list
  {
    width:150px;
  }
  #down-left
  {
    width:40%;
    height:100%;
    float:left;
    border-right:5px double lightblue;
  }
  #syc-with-select
  {
    width:70%;
    /*height:70px;*/
    /*padding:2px;*/
    margin:5px;
    float:left;
  }
  #detail-message-show
  {
    width:70%;
    height:100px;
    border:2px solid lightblue;
    float:left;
    margin-left:20px;
    margin-bottom:20px;
  }
  .buttons
  {
    width:20%;
    float:right;
  }
  button
  {
    width:80%;
  }
  #down-table
  {
    clear:both;
  }
  #stateInfoTab,#basicInfoTab
  {
    text-align: left;
    padding:10px 40px 10px 40px;
    line-height: 30px;
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
