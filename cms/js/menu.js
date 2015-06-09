[{
	"title" : "车辆预登记",
	"mTitle" : "车辆预登记",
	"action" : "baseManager!proCarRegister!toPage.action",
	"icon" : "images/start.gif",
	"menus" : [ {
		"title" : "车辆预登记",
		"action" : "baseManager!proCarRegister!toPage.action",
		"icon" : "images/car.png"
	}
	,{
		"title" : "进口车登记",
		"action" : "baseManager!importProCarRegister!toPage.action",
		"icon" : "images/impCar.png"
	},
	{
		"title" : "已登记信息",
		"action" : "baseManager!proCarRegisterList!toPage.action",
		"icon" : "images/list.png"
	}
	]
},
{
	"mTitle" : "系统管理",
	"title":"打印查验单",
	"action" : "baseManager!printTemplet!toPage.action",
	"icon" : "images/start.gif",
	"menus" : [ {
		"title" : "模板配置",
		"action" : "baseManager!templetConfigList!toPage.action",
		"icon" : "images/settings.png"
	},
	{
		"title" : "打印查验单",
		"action" : "baseManager!printTemplet!toPage.action",
		"icon" : "images/print.png"
	},
	{
		"title" : "PDA设备管理",
		"action" : "baseManager!DeviceList!toPage.action",
		"icon" : "images/print.png"
	}
	]
}]