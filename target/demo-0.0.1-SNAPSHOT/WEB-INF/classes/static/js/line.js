/**
 * 标准折线图
 */
// 基于准备好的dom，初始化echarts实例
		var $v2 = $("#main") ; //$('#id')这种获取的是jQuery对象,而echarts需要的是dom对象，所以需要如下转换
		var v2 = $v2[0]; //DOM对象 
        var myChart2 = echarts.init(v2);
        //当然完全可以这样写,上面只是转换一下,多学一个知识点
        //var myChart2 = echarts.init(document.getElementById('main2'));

        // 指定图表的配置项和数据
        var option2 = {
        		title : {
        	        text: '一周内服装库存变化趋势',
        	        subtext: '纯属虚构'
        	    },
        	    //tooltip触发方式:axis以X轴线触发,item以每一个数据项触发  
                //tooltip下的trigger,当trigger为'item'时只会显示该点的数据，为'axis'时显示该列下所有坐标轴所对应的数据。
        	    tooltip : {
        	        trigger: 'axis',
        	    },
        	    legend: {
        	        data:['最高','最低']
        	    },
        	    toolbox: {//工具栏。内置有导出图片，数据视图，动态类型切换，数据区域缩放，重置五个工具。
        	        show : true,//是否显示工具栏组件。
        	        //orient: 'vertical',//工具栏 icon 的布局朝向。默认是水平'horizontal'，'vertical'是垂直。
        	        //itemSize:12,//工具栏 icon 的大小。默认是15。
        	        //itemGap:20,//工具栏 icon 每项之间的间隔。横向布局时为水平间隔，纵向布局时为纵向间隔。默认是10。
        	        //showTitle:false,//是否在鼠标 hover 的时候显示每个工具 icon 的标题。默认是true。
        	        //left:'70%',//工具栏组件离容器左侧的距离。默认是'auto'。top,right,bottom同理。
        	        //width:'50px',//工具栏组件的宽度。默认自适应。默认'auto'。
        	        //height:'50px',//工具栏组件的宽度。默认自适应。默认'auto'。
        	        //iconStyle:{},//公用的 icon 样式设置。用的时候再查吧。
        	        feature : {//feature:是各工具配置项。//除了各个内置的工具按钮外，还可以自定义工具按钮。//注意，自定义的工具名字，只能以 my 开头，
        	            mark : {show: true},//show:true是是否显示该工具
        	            dataView : {show: true, readOnly: false},//数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新。
        	            dataZoom: {},//数据区域缩放。目前只支持直角坐标系的缩放。
        	            //magicType : {show: true, type: ['line', 'bar', 'stack']},//动态类型切换 ,type:包括'line'（切换为折线图）, 'bar'（切换为柱状图）, 'stack'（切换为堆叠模式）, 'tiled'（切换为平铺模式）。
        	            restore : {show: true},//配置项还原。好像应该是用户调了一些样式，还原成加载的样子吧。
        	            saveAsImage : {show: true}//保存为图片。如果想设置保存图片的一些信息,就找{}里面设置。
        	        }
        	    },
        	    calculable : true,//是否显示拖拽用的手柄（手柄能拖拽调整选中范围）。
        	    //calculable是为了兼容 ECharts2，当 visualMap.type 未指定时，假如设置了 'calculable'，则type自动被设置为'continuous'，无视 visualMap-piecewise.splitNumber 等设置。所以，建议使用者不要不指定 visualMap.type，否则表意不清晰。
        	    xAxis : [
        	        {
        	            type : 'category',
        	            boundaryGap : false,//有类目轴和非类目轴之分,此图中X轴是类目轴'category',boundaryGap可设true和false
        	            //非类目轴，包括时间，数值，对数轴，boundaryGap是一个两个值的数组，分别表示数据最小值和最大值的延伸范围，可以直接设置数值或者相对的百分比，在设置 min 和 max 后无效。
        	            //例子:boundaryGap: ['20%', '20%']
        	            data : ['周一','周二','周三','周四','周五','周六','周日']
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'value',
        	            axisLabel : {//坐标轴刻度标签的相关设置。
        	                formatter: '{value} '
        	            }
        	        }
        	    ],
        	    series : [
        	        {
        	            name:'最高',
        	            type:'line',
        	            data:[1100, 1100, 1500, 1300, 1200, 1300, 1000],
        	            markPoint : {
        	                data : [
        	                    {type : 'max', name: '最大值'},
        	                    {type : 'min', name: '最小值'}
        	                ]
        	            },
        	            markLine : {
        	                data : [
        	                    {type : 'average', name: '平均值'}
        	                ]
        	            }
        	        },
        	        {
        	            name:'最低',
        	            type:'line',
        	            data:[100, 200, 200, 500, 300, 200, 0],
        	            markLine : {
        	                data : [
        	                    {type : 'average', name : '平均值'}
        	                ]
        	            }
        	        }
        	    ]
        };
        //清空画布,防止缓存
        myChart2.clear();
        // 使用刚指定的配置项和数据显示图表。
        myChart2.setOption(option2);
window.onresize = function() {
    myChart2.resize();
};