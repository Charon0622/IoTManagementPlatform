<template>
  <Row>
    <div>

        <vue-highcharts :options="options" ref="lineCharts"></vue-highcharts>
    </div>
  </Row>
</template>

<script>
  import VueHighcharts from 'vue2-highcharts'
  const asyncData = {
    name: '电流变化',
    marker: {
      symbol: 'square'
    },
    data: [1,2,3,4,5]
  }
  export default{
    props:{
      data0:{
        type:Array,
        required:true
      }
    },
    components: {
      VueHighcharts
    },
    data(){
      return{
        peak:[],
        date:[],
        options: {
          chart: {
            type: 'spline'
          },
          title: {
            text: '电流变化'
          },
          subtitle: {
            text: '最新30条数据'
          },
          xAxis: {
            categories: this.datae0.date
          },
          yAxis: {
            title: {
              text: '千安'
            },
            labels: {
              formatter: function () {
                return this.value ;
              }
            }
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
        }
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
        getpeak(){
          console.log(data0)
          console.log(parentmessage)
          }
    },

    mounted(){
      this.load()
      this.getpeak()
    }
  }
</script>

<style scoped>

</style>
