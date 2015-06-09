//用原来的控件读取条码
function readbarcode(){
  //var strbarcode=vehPrinter.GetBarcodeItem(tmri.barcode.value);
  var strbarcode=vehPrinter.GetBarcodeItem("");
  //alert(strbarcode);
  if (strbarcode==""){
    return 0;
  }
  if (strbarcode=="-1") {
    alert("条码信息有误!");
    return 0;
  }
  parsebarcode(strbarcode);
}

function readYxQrtext(){
  var barcodetemp ="";
  var strbarcode=vehPrinter.GetQrText();
 
  if (strbarcode==""){
    return 0;
  }
  if (strbarcode=="-1") {
    alert("条码信息有误!");
    return 0;
  }
  parseyxbarcode(strbarcode);
}

function parseyxbarcode(strbarcode){
    
  var barArray = strbarcode.split("|");
  strBarCodeType=barArray[0];
  strBarCodeType=strBarCodeType.substring(0,strBarCodeType.indexOf("_"));  
  //互联网预选信息
  if (strBarCodeType=="QTZS51" && tmrisub.clsbdh.value != "" && 
      tmrisub.clsbdh.value != "--" && tmrisub.xhvalid.value=="1" && tmrisub.yxxh.value=="" ){
    if(tmrisub.clsbdh.value==barArray[5]){        
        tmrisub.yxjmxx.value = barArray[1];
        tmrisub.yxxh.value = barArray[2];
        tmri.yxhpzl.value = barArray[3];
        tmri.yxhphm.value = barArray[4];
        tmri.yxsfzmmc.value = barArray[6];
        tmri.yxsfzmhm.value = barArray[7];
        tmri.yxsyr.value = barArray[8];
        tmri.yxsj.value = barArray[12];
        tmrisub.yxhpzl.value = barArray[3];
        tmrisub.yxhphm.value = barArray[4];
        tmrisub.yxsfzmmc.value = barArray[6];
        tmrisub.yxsfzmhm.value = barArray[7];
        tmrisub.yxsyr.value = barArray[8];
        tmrisub.yxsj.value = barArray[12];
        tmrisub.yxfzjg.value = barArray[13];
        tmrisub.yxglbm.value = barArray[14];
        tmrisub.blts.value = barArray[19];
        tmrisub.buttonOK.disabled = false;
    }else{
        alert("预选凭证信息和当前业务流水信息不匹配！");
    }
  }
  return 1;
}
//使用监控程序读取条码，定时读取看是否有条码信息
function readQrtext(){
  var barcodetemp ="";
  var strbarcode=vehPrinter.GetQrText();
  
  if (strbarcode==""){
    return 0;
  }
  if (strbarcode=="-1") {
    alert("条码信息有误!");
    return 0;
  }
  parsebarcode(strbarcode);
}

function parsebarcode(strbarcode){
  //  alert(strbarcode);
  var barArray = strbarcode.split("|");
  var i;
  var indx_=0;
  var qx_jh_dp ="";
  var ywlx_B="0";  
  var dykjbbh="";
  strBarCodeType=barArray[0];
  
  var strBarCodeTypeArray=strBarCodeType.split("_");
  if (strBarCodeTypeArray.length>1){
	  dykjbbh=strBarCodeTypeArray[1];
  }
  strBarCodeType=strBarCodeTypeArray[0];
  //strBarCodeType=strBarCodeType.substring(0,strBarCodeType.indexOf("_"));  
  
  //    该条码组成为：
  //    HGFOZ_<版本>  条码头 0
  //    Hgfo_Hg 海关 1
  //    Hgfo_Zmsbh 证明书编号 2
  //    Hgfo_Cpxh 厂牌型号 3
  //    Hgfo_Csys 车身颜色 4
  //    Hgfo_Fdjh 发动机号 5
  //    Hgfo_Clsbdh 车辆识别代号 6
  //    Hgfo_Ccrq 出厂日期 7
  //    Hgfo_Cd 产地 制造国/地区 8
  //    Hgfo_Wsbh 裁定没收法律文书编号 9
  //    Hgfo_Qfbmdh 签发部门电话 10
  //    Hgfo_Qfrq

  if (strBarCodeType=="HGFOZ"){
    tmri.jkpz.value="2";
    tmri.jkpzhm.value=barArray[2];
    tmri.gcjk.value="E";
    reSetSelectOption(tmri.jkpzhm_1,"No.,ⅨⅦ");
    tmri.jkpzhm_1.value="No.";
    tmri.jkpzhm_2.value=tmri.jkpzhm.value.substring(3);
    tmri.jkccpxh.value=barArray[3];
    //tmri.tempclxh.value=barArray[3];
    tmri.jkccsys.value=barArray[4];
    tmri.fdjh.value=barArray[5];
    tmri.clsbdh.value=barArray[6];
    tmri.ccrq.value=barArray[7];
    //tmri.zzg.value=barArray[6];
    tmri.qfrq.value=barArray[11];
    tmri.jkpz.disabled=true;
    tmri.jkpzhm.readOnly=true;
    tmri.jkccpxh.readOnly=true;
    tmri.jkccsys.readOnly=true;
    tmri.fdjh.readOnly=true;
    tmri.clsbdh.readOnly=true;
    //tmri.ccrq.readOnly=true;
    tmri.qfrq.readOnly=true;
    paramForm.isSetClsbdh.value="0";
    paramForm.isHgzh.value="0";
    return 1;
  }


  //读取整车合格证信息
  if(strBarCodeType=="ZCCCHGZ"){
    qx_jh_dp = barArray[1];
    //------------------------------------------------------------------//
    //读取的是全项合格证
    if(qx_jh_dp=="QX"){
      if(tmri.v_hgzzt){
        if(tmri.v_hgzzt.value!="0"||tmri.zzcmc.value!=""){
          alert("请先清空前一辆车的信息!");
          return 0;
        }else{
          if (tmri.clsbdh.value!="" && tmri.clsbdh.value !=(barArray[13]+barArray[14])){
        	  alert("扫描的合格证信息与当前机动车信息不一致，请先清空当前机动车的信息!");
              return 0; 
          }else{
        	  tmri.clsbdh.value="";
              tmri.hgzbh.value="";
              tmri.fdjh.value="";	
              tmri.v_hgzzt.value="1";
          }	
        }
      }
      
      
      /*
      if (barArray[3]>="2009-08-31" && barArray.length<64){
    	  alert("该合格证存在伪造嫌疑，请与生产厂家进一步核实！");
    	  return 0;
      }
      */
      
      
      tmri.hgzbh.value=barArray[2];
      if(tmri.zzcmc.value==""){
        tmri.zzcmc.value=barArray[4];}
      if(tmri.ccrq.value==""){
        tmri.ccrq.value=barArray[3];}
      //tmri.cllx.value=barArray[6];
      if(tmri.tempclpp1.value=="" && barArray[7].indexOf("/")>0){
        var vehClpp = barArray[7].split("/");
        tmri.tempclpp1.value=vehClpp[0];
        tmri.clpp1.value=vehClpp[0];
        tmri.clpp2.value=vehClpp[1];
      }
      if(tmri.tempclpp1.value==""){
        tmri.tempclpp1.value=barArray[7];
        tmri.clpp1.value=barArray[7];
      }
      if(tmri.tempclxh.value==""){
    	tmri.tempclxh.value=barArray[8];
    	tmri.clxh.value=barArray[8];}
      //tmri.csys.value=barArray[9];
      if(tmri.clsbdh.value==""){
    	tmri.clsbdh.value=barArray[13]+barArray[14];}
      if(tmri.fdjh.value==""){
    	tmri.fdjh.value=barArray[15];
        /*indx_=tmri.fdjh.value.lastIndexOf(" ");
        if (indx_>0){
          tmri.fdjh.value= tmri.fdjh.value.substr(indx_+1);
        }*/
      }
      if(tmri.fdjxh.value==""){
        reSetSelectOption(tmri.fdjxh,barArray[16]);
        tmri.fdjxh.value=barArray[16];
      }
      if(tmri.rlzl1.value=="Y" && tmri.rlzl2.value=="Y" && barArray[17].length>0){
        tmri.rlzl1.value=barArray[17].substring(0,1);
        if( barArray[17].length>1){
          tmri.rlzl2.value=barArray[17].substring(1,2);
        }
      }
      if(tmri.hbdbqk.value==""){
        tmri.hbdbqk.value=barArray[18]; }
      if(tmri.pl.value==""){
        tmri.pl.value=barArray[19];}
      if(tmri.gl.value==""){
        if (barArray[20]!=null && barArray[20].indexOf("/")>0){
          tmri.gl.value=barArray[20].substring(0,barArray[20].indexOf("/"));
        }else{
          tmri.gl.value=barArray[20];
        }
      }
      if(tmri.zxxs.value=="0"){
        tmri.zxxs.value=barArray[21];
      }
      
      if(tmri.qlj.value==""){
        if (barArray[22]!=null && barArray[22].indexOf("/")>0){
          tmri.qlj.value=barArray[22].substring(0,barArray[22].indexOf("/"));
        }else{
          tmri.qlj.value=barArray[22];
        }
      }
      //
      if(tmri.lts.value==""){
        tmri.lts.value=barArray[24];
      }
      if(tmri.ltgg.value==""){
        tmri.ltgg.value=barArray[25];
        if (tmri.ltgg.value.length>20){
          tmri.ltgg.value=tmri.ltgg.value.substring(0,20);
        }
      }
      if(tmri.gbthps.value==""){
        var zxzs=1;
        if (barArray[22].indexOf("/") > -1) {
            var qljArray = barArray[22].split("/");
            zxzs=qljArray.length;
        }
        //if (barArray.length>55) {
        //  zxzs=parseInt(barArray[55]);
        //}
        
        if(barArray[6]=="挂车"){
          tmri.gbthps.value=getGbthps("-/"+barArray[26],1);
        }else{
          tmri.gbthps.value=getGbthps(barArray[26],zxzs);
        }
      }
      if(tmri.zj.value==""){
        tmri.zj.value=getZj(barArray[27]);}
      if(tmri.zs.value==""){
        tmri.zs.value=barArray[29];}
      if(tmri.hlj.value==""){
        tmri.hlj.value=getHlj(barArray[23],tmri.zs.value);
      }

      if(tmri.cwkc.value==""){
        tmri.cwkc.value=barArray[30];}
      if(tmri.cwkk.value==""){
        tmri.cwkk.value=barArray[31];}
      if(tmri.cwkg.value==""){
        tmri.cwkg.value=barArray[32];}
      if(tmri.hxnbcd.value==""){
        tmri.hxnbcd.value=barArray[33];}
      if(tmri.hxnbkd.value==""){
        tmri.hxnbkd.value=barArray[34];}
      if(tmri.hxnbgd.value==""){
        tmri.hxnbgd.value=barArray[35];}
      if(tmri.zzl.value==""){
        tmri.zzl.value=barArray[36];}
      if(tmri.hdzzl.value==""){
        tmri.hdzzl.value=barArray[37]; }
      if(tmri.zbzl.value==""){
        tmri.zbzl.value=barArray[38];}
      if(tmri.zqyzl.value==""){
        tmri.zqyzl.value=barArray[40];}
      if(tmri.hdzk.value==""){
        if (barArray[41]!=null && barArray[41].indexOf("/")>0){
          tmri.hdzk.value=barArray[41].substring(0,barArray[41].indexOf("/"));
        }else{
          tmri.hdzk.value=barArray[41];
        }
      }
      if(tmri.qpzk.value=="" && tmri.qpzk.value==""){
    	if(barArray[43]!=null && barArray[43].indexOf("+")>0){
          var jsszk = barArray[43].split("+");
          tmri.qpzk.value=jsszk[0];
          tmri.hpzk.value=jsszk[1];
    	}else{
          tmri.qpzk.value=barArray[43];
    	}
      }
      tmri.barcode.value="";
      
      if (dykjbbh !=""){
    	  dykjbbh=dykjbbh.substring(1,2);
    	  tmri.dykjbbh.value=dykjbbh;
      }
      
      if(dykjbbh=="3"){
    	  tmri.udxlh.value=barArray[63];
    	  tmri.dywybh.value=barArray[62];
    	  
      }else if (dykjbbh=="2"){
    	  tmri.hgzdysj.value=barArray[58];
    	 
      }
      
      

    }
    //---------------------------------------------------------//
    //读取的是简化合格证
    if(qx_jh_dp=="JH"){
      if(tmri.v_hgzzt){
        if((tmri.v_hgzzt.value!="0"||tmri.zzcmc.value!="")){
          alert("请先清空前一辆车的信息!");
          return 0;
        }else{
        	if (tmri.clsbdh.value!="" && tmri.clsbdh.value !=(barArray[13]+barArray[14])){
          	  	alert("扫描的合格证信息与当前机动车信息不一致，请先清空当前机动车的信息!");
                return 0; 
            }else{
            	tmri.clsbdh.value="";
                tmri.hgzbh.value="";
                tmri.fdjh.value="";	
                tmri.v_hgzzt.value="2";
            }	
	
        }
      }
      tmri.hgzbh.value=barArray[2];
      tmri.zzcmc.value=barArray[4];
      tmri.ccrq.value=barArray[3];
      //tmri.cllx.value=barArray[6];
      if(barArray[7].indexOf("/")>0){
        var vehClpp = barArray[7].split("/");
        tmri.tempclpp1.value=vehClpp[0];
        tmri.clpp1.value=vehClpp[0];
        tmri.clpp2.value=vehClpp[1];
      }else{
        tmri.tempclpp1.value=barArray[7];
        tmri.clpp1.value=barArray[7];
      }
      tmri.tempclxh.value=barArray[8];
      tmri.clxh.value=barArray[8];

      tmri.clsbdh.value=barArray[13]+barArray[14];

      tmri.cwkc.value=barArray[30];
      tmri.cwkk.value=barArray[31];
      tmri.cwkg.value=barArray[32];
      tmri.hxnbcd.value=barArray[33];
      tmri.hxnbkd.value=barArray[34];
      tmri.hxnbgd.value=barArray[35];
      tmri.zzl.value=barArray[36];
      tmri.hdzzl.value=barArray[37];
      tmri.zbzl.value=barArray[38];
      tmri.zqyzl.value=barArray[40];
      //tmri.hdzk.value=barArray[41];
      if (barArray[41]!=null && barArray[41].indexOf("/")>0){
        tmri.hdzk.value=barArray[41].substring(0,barArray[41].indexOf("/"));
      }else{
        tmri.hdzk.value=barArray[41];
      }


      if(barArray[43]!=null && barArray[43].indexOf("+")>0){
        var jsszk = barArray[43].split("+");
        tmri.qpzk.value=jsszk[0];
        tmri.hpzk.value=jsszk[1];
      }else{
        tmri.qpzk.value=barArray[43];
      }
      alert("请继续读取底盘合格证信息！");
      tmri.barcode.value="";
      tmri.barcode.parentNode.focus();
    }
    //-------------------------------------------------------------------//
    //读取的是底盘合格证
    if(qx_jh_dp=="DP"){
      if(tmri.v_hgzzt){
        if(tmri.v_hgzzt.value!="2"){
          alert("请先读取整车合格证信息!");
          return 0;
        }else{
          tmri.v_hgzzt.value="3";
        }
      }
      tmri.dphgzbh.value=barArray[2];
      tmri.fdjh.value=barArray[12];
      reSetSelectOption(tmri.fdjxh,barArray[13]);
      tmri.fdjxh.value=barArray[13];
      tmri.rlzl1.value=barArray[14].substring(0,1);
      if( barArray[14].length>1){
        tmri.rlzl2.value=barArray[14].substring(1,2);
      }
      tmri.hbdbqk.value=barArray[15];
      tmri.pl.value=barArray[16];
      tmri.zxxs.value=barArray[18];
      //tmri.gl.value=barArray[17];
      if (barArray[17]!=null && barArray[17].indexOf("/")>0){
        tmri.gl.value=barArray[17].substring(0,barArray[17].indexOf("/"));
      }else{
        tmri.gl.value=barArray[17];
      }
      //    	tmri.qlj.value=barArray[19];
      if (barArray[19]!=null && barArray[19].indexOf("/")>0){
        tmri.qlj.value=barArray[19].substring(0,barArray[19].indexOf("/"));
      }else{
        tmri.qlj.value=barArray[19];
      }

      tmri.lts.value=barArray[21];
      tmri.ltgg.value=barArray[22];
      if (tmri.ltgg.value.length>20){
        tmri.ltgg.value=tmri.ltgg.value.substring(0,20);
      }
      var dpzxzs=1;
	  if (barArray[19].indexOf("/") > -1) {
	      var dpqljArray = barArray[19].split("/");
	      dpzxzs=dpqljArray.length;
	  }
      if(barArray[6]=="挂车"){
        tmri.gbthps.value=getGbthps("-/"+barArray[23],1);
      }else{
        tmri.gbthps.value=getGbthps(barArray[23],dpzxzs);
      }
      //tmri.gbthps.value=getGbthps(barArray[23]);
      tmri.zj.value=getZj(barArray[24]);
      tmri.zs.value=barArray[26];
      tmri.hlj.value=getHlj(barArray[20],tmri.zs.value);
      //if(tmri.zqyzl.value==""){tmri.zqyzl.value=barArray[32];}
      if(barArray[6]=="二类底盘" && tmri.qpzk.value==""){
        if(barArray[34]!=null && barArray[34].indexOf("+")>0){
          var jsszk = barArray[34].split("+")
          tmri.qpzk.value=jsszk[0];
          tmri.hpzk.value=jsszk[1];
        }else{
          tmri.qpzk.value=barArray[34];
        }
      }
      tmri.barcode.value="";
      paramForm.isSetClsbdh.value="0";
    }
    //发动机号去除发动机型号

    tmri.ccrq.value=tmri.ccrq.value.replace("年","-");
    tmri.ccrq.value=tmri.ccrq.value.replace("月","-");
    tmri.ccrq.value=tmri.ccrq.value.replace("日","");
    tmri.fdjh.value=tmri.fdjh.value.replace(tmri.fdjxh.value+" ", "");
    tmri.fdjh.readOnly=true;
    tmri.clsbdh.readOnly=true;
    //tmri.ccrq.readOnly=true;
    tmri.hgzbh.readOnly=true;
    tmri.zzcmc.readOnly=true;
    tmri.ccrq.readOnly=true;
    tmri.tempclpp1.readOnly=true;
    tmri.tempclxh.readOnly=true;
    tmri.clsbdh.readOnly=true;
    tmri.fdjh.readOnly=true;
    tmri.fdjxh.readOnly=true;
    tmri.rlzl1.readOnly=true;
    tmri.rlzl2.readOnly=true;
    //    tmri.hbdbqk.readOnly=true;
    tmri.pl.readOnly=true;
    tmri.gl.readOnly=true;
    tmri.zxxs.readOnly=true;
    tmri.qlj.readOnly=true;
    tmri.hlj.readOnly=true;
    tmri.lts.readOnly=true;
    //tmri.ltgg.readOnly=true;
    //tmri.gbthps.readOnly=true;
    tmri.zj.readOnly=true;
    tmri.zs.readOnly=true;
    tmri.cwkc.readOnly=true;
    tmri.cwkk.readOnly=true;
    tmri.cwkg.readOnly=true;
    tmri.hxnbcd.readOnly=true;
    tmri.hxnbkd.readOnly=true;
    tmri.hxnbgd.readOnly=true;
    tmri.zzl.readOnly=true;
    tmri.hdzzl.readOnly=true;
    tmri.zbzl.readOnly=true;
    tmri.zqyzl.readOnly=true;
    tmri.hdzk.readOnly=true;
    tmri.qpzk.readOnly=true;
    tmri.hpzk.readOnly=true;

    //排量大于四位，则不读取
    var float_pl=parseFloat(tmri.pl.value);
    tmri.pl.value =Math.round(float_pl);
    if (isNaN(tmri.pl.value)) tmri.pl.value="";
    //    if (tmri.pl.value.length>4) tmri.pl.value="";
    //
    if(barArray[6]=="两轮摩托车和两轮轻便摩托车" ||barArray[6]=="三轮摩托车和三轮轻便摩托车"
      ||barArray[6]=="低速货车"||barArray[6]=="三轮汽车"){
      tmri.ltgg.readOnly=false;
      tmri.lts.readOnly=false;
      tmri.zs.readOnly=false;
      tmri.zzl.readOnly=false;
      tmri.hdzk.readOnly=false;
      tmri.hdzzl.readOnly=false;
      tmri.qpzk.readOnly=false;
      tmri.hpzk.readOnly=false;
    }
  }
  if (strBarCodeType=="QTZS21"){
    getYDJXT(barArray);
    if (barArray[98]=="AAA") ywlx_B="1";
  }

  if (strBarCodeType=="INSPECTION"){
    getINSPECTION(barArray);

  }
 
  if(strBarCodeType!="QTZS51" && qx_jh_dp!="JH" && ywlx_B !="1" && strBarCodeType!="SFZXX" && strBarCodeType!="INSPECTION"){
    paramForm.isHgzh.value="1";
    showParameter('clxh','1');
  }
  
  if (strBarCodeType=="SFZXX"){
    //    alert(strbarcode);
	  
	var isAgent='0';
	try{
		isAgent=frmAgent.isAgent.value;
	} catch(e){
		
	}
	var isOpenAgent="0";
	try{
		isAgent=tmri.isOpenAgent.value;
	} catch(e){
		isOpenAgent="0";
	}
	
	
	if (isAgent=="1"){
		
		if(document.title=="代理人录入"){
			
			frmAgent.sfzmmc.value="A";
			frmAgent.sfzmhm.value=barArray[6];
			frmAgent.xm.value=barArray[1];
			frmAgent.dqedz.value="1";
			getAgentInfo();
		}else{
			try{
				vehAgentWin.document.frmAgent.sfzmmc.value="A";
				vehAgentWin.document.frmAgent.sfzmhm.value=barArray[6];
				vehAgentWin.document.frmAgent.xm.value=barArray[1];
				vehAgentWin.document.frmAgent.dqedz.value="1";
				vehAgentWin.document.getAgentInfo();
			}catch(e){
				isOpenAgent="0";
			}
			
			
		}
		
		
	}else if (isOpenAgent=="0" ){
		
		tmri.isreadID.value="1";
	    if (tmri.ywlx.value=="A" || tmri.ywlx.value=="I"){
	      tmri.sfzmmc.value="A";
	      tmri.sfzmhm.value=barArray[6];
	      tmri.syr.value=barArray[1];
	      tmri.zsxxdz.value=barArray[5];
	      getXzqhbyQfjg(barArray[7],tmri.zsxzqh);
	    }else{
	      tmri.tmp_sfzmmc.value="A";
	      tmri.tmp_sfzmhm.value=barArray[6];
	      tmri.tmp_syr.value=barArray[1];
	      tmri.tmp_zsxxdz.value=barArray[5];
	      getXzqhbyQfjg(barArray[7],tmri.tmp_zsxzqh);
	    }
	}

    
  }
  //互联网预选信息
  if (strBarCodeType=="QTZS51"){    
    if(tmri.clsbdh.value==barArray[5] && tmri.buttonOK.disabled==false){
        tmri.sfzmmc.value = barArray[6];
        tmri.sfzmhm.value = barArray[7];
        tmri.syr.value = barArray[8];
        tmri.zsxxdz.value = barArray[9];
        tmri.zzxxdz.value = barArray[10];
        tmri.lxdh.value = barArray[11];
        tmri.zzz.value = barArray[15];
        tmri.dzyx.value = barArray[16];
        tmri.sjhm.value = barArray[17];
        tmri.yzbm1.value = barArray[18];
        if(intHphmInfo!=undefined){
            intHphmInfo.style.display="block";
        }
    }   
  }
  return 1;
}


//m_zxzs  转向轴数


function getGbthps(m_SourceValue,m_zxzs) {

  //是否为数字
  var newgbthps;
  var i_gbthps =0;
  if (m_SourceValue.length==0){
    return "";
  }
  //m_SourceValue="-/"+m_SourceValue;
  var paraArray = m_SourceValue.split(","); //根据","分割成字符串数组
  var i;
  var j;
  var k
  for (i = 0; i < paraArray.length; i++) {
    if (isNum_Para(paraArray[i])) {
      //如为数字，直接判断
      i_gbthps= parseInt(paraArray[i]);
    }
    else {
      i_gbthps = 0;
      if (paraArray[i].indexOf("/") > -1) {

        for(k = 0;k<m_zxzs;k++){
          paraArray[i] = paraArray[i].substr(paraArray[i].indexOf("/")+1)
        }
        paraArray[i] += "/";

        while (paraArray[i].indexOf("/") > -1) {
          newgbthps = paraArray[i].substring(0, paraArray[i].indexOf("/"));
          if (isNum_Para(newgbthps)) {
            i_gbthps +=  parseInt(newgbthps);
          }else{
            if (newgbthps.indexOf("+") > 0) {
              var valueSplit = newgbthps.split("+");
              for (j=0;j<valueSplit.length;j++){
                //为了应对钢板弹簧片数形式为‘-/8+-’的情况，所以增加valueSplit[j]是否为数字的判断
                if (isNum_Para(valueSplit[j])){
                  i_gbthps +=parseInt(valueSplit[j]);
                }
              }
              //                 i_gbthps = i_gbthps+(parseInt(valueSplit[0]) + parseInt(valueSplit[1]));
            }
          }
          paraArray[i] = paraArray[i].substr(paraArray[i].indexOf("/")+1);
        }
      }else{
        if (paraArray[i].indexOf("+") > 0) {
          var valueSplit = paraArray[i].split("+");
          for (j=0;j<valueSplit.length;j++){
            if (isNum_Para(valueSplit[j])){
              i_gbthps +=parseInt(valueSplit[j]);
            }
          }
          //              i_gbthps=parseInt(valueSplit[0]) + parseInt(valueSplit[1]);
        }
      }
    }
  }
  if (i_gbthps==0) {
    return "";
  }
  else{
    return i_gbthps;
  }
}

function getZj(m_SourceValue) {

  //是否为数字
  var newgbthps;
  var i_zj =0;
  if (m_SourceValue.length==0){
    i_zj =0;
  }
  var paraArray = m_SourceValue.split("+"); //根据","分割成字符串数组
  var i;
  for (i = 0; i < paraArray.length; i++) {
    if (isNum_Para(paraArray[i])) {
      //如为数字，直接判断
      i_zj += parseInt(paraArray[i]);
    }
  }
  return i_zj;
}

//后轮距
function getHlj(m_SourceValue,m_zs) {
  //是否为数字
  var newgbthps;
  var i_lj =0;

  if (m_SourceValue.length==0){
    return "";
  }
  var i_zs = parseInt(m_zs);
  if (m_SourceValue.indexOf("/") > 0) {
    var paraArray = m_SourceValue.split("/"); //根据","分割成字符串数组
    if (i_zs==3){
      i_lj = parseInt(paraArray[0]);
    }else{
      i_lj = parseInt(paraArray[paraArray.length-2]);
    }
  }else{
    if (m_SourceValue.indexOf("+") > 0) {
      var paraArray = m_SourceValue.split("+"); //根据","分割成字符串数组
      var i;
      for (i = 0; i < paraArray.length; i++) {
        if (isNum_Para(paraArray[i])) {
          //如为数字，直接判断
          i_lj += parseInt(paraArray[i]);
        }
      }
    }else{
      i_lj = parseInt(m_SourceValue);
    }
  }
  return i_lj;
}

function getYDJXT(barArray){
  var i=0;

  if (barArray[98]=="AAA"){
    //转移登记
    show_Form('bussine');
    if (barArray[44]=="000000"){
      tmri.move[2].checked=true;
    }else{
      tmri.move[1].checked=true;
    }
    move_checkbox();
    tmri.tmp_sfzmmc.value = barArray[40];
    tmri.tmp_sfzmhm.value = barArray[41];
    tmri.tmp_syr.value = barArray[42];
    tmri.tmp_lxdh.value = barArray[43];
    tmri.tmp_zsxzqh.value = barArray[44];
    tmri.tmp_zsxxdz.value = barArray[45];
    tmri.tmp_zzz.value = barArray[46];
    tmri.tmp_yzbm1.value = barArray[47];
    tmri.tmp_syxz.value = barArray[48];
    tmri.tmp_syq.value = barArray[49];
    tmri.tmp_hdfs.value = barArray[50];
    //tmri.tmp_llpz1.value = barArray[53];
    // tmri.tmp_pzbh1.value = barArray[54];
    //tmri.tmp_llpz2.value = barArray[55];
    // tmri.tmp_pzbh2.value = barArray[56];
    tmri.tmp_jcjgzms.value = barArray[25];
    if (barArray[44]=="000000"){
      tmri.province.value=barArray[69].substr(0,1);
      get_zcd();
      //tmri.province.value=barArray[68];
      tmri.zcd.value=barArray[69];
    }
    tmri.tmp_zzxzqh.value=barArray[99];
    tmri.tmp_zzxxdz.value=barArray[100];
    tmri.tmp_sjhm.value=barArray[101];
    tmri.tmp_dzyx.value=barArray[102];


  }else{
    //注册登记
    tmri.hgzbh.value = barArray[2] ;
    tmri.tempclxh.value = barArray[3] ;
    tmri.clxh.value = barArray[3] ;
    tmri.tempclpp1.value = barArray[4] ;
    tmri.clpp1.value = barArray[4] ;
    tmri.clpp2.value = barArray[5] ;
    tmri.cllx.value = barArray[6];
    tmri.clsbdh.value = barArray[7];
    tmri.fdjh.value= barArray[8] ;
    tmri.zzcmc.value = barArray[9];

    SetSelectCsys(barArray[10],"");

    tmri.ccrq.value = barArray[11];
    tmri.gcjk.value = barArray[12];
    tmri.zzg.value = barArray[13];
    reSetSelectOption(tmri.fdjxh, barArray[14]);
    tmri.pl.value = barArray[15];
    tmri.gl.value = barArray[16];
    SetSelectRlzl(barArray[17],"");
    tmri.cwkc.value = barArray[18];
    tmri.cwkk.value = barArray[19];
    tmri.cwkg.value = barArray[20];
    tmri.zxxs.value = barArray[21];
    tmri.hxnbcd.value = barArray[22];
    tmri.hxnbkd.value = barArray[23];
    tmri.hxnbgd.value = barArray[24];
    tmri.hbdbqk.value = barArray[25];
    tmri.gbthps.value = barArray[26];
    tmri.zs.value = barArray[27];
    tmri.zj.value = barArray[28];
    tmri.lts.value = barArray[29];
    tmri.ltgg.value = barArray[30];
    if (tmri.ltgg.value.length>20){
      tmri.ltgg.value=tmri.ltgg.value.substring(0,20);
    }
    tmri.qlj.value = barArray[31];
    tmri.hlj.value = barArray[32];
    tmri.zzl.value = barArray[33];
    tmri.zbzl.value = barArray[34];
    tmri.hdzk.value = barArray[35];
    tmri.zqyzl.value = barArray[36];
    tmri.hdzzl.value = barArray[37];
    tmri.qpzk.value = barArray[38];
    tmri.hpzk.value = barArray[39];
    tmri.sfzmmc.value = barArray[40];
    tmri.sfzmhm.value = barArray[41];
    tmri.syr.value = barArray[42];
    tmri.lxdh.value = barArray[43];
    tmri.zsxzqh.value = barArray[44];
    tmri.zsxxdz.value = barArray[45];
    tmri.zzz.value = barArray[46];
    tmri.yzbm1.value = barArray[47];
    tmri.syxz.value = barArray[48];
    tmri.syq.value = barArray[49];
    tmri.hdfs.value = barArray[50];
    tmri.xzqh.value = barArray[51];
    tmri.hpzl.value = barArray[52];
    tmri.llpz1.value = barArray[53];
    tmri.pzbh1.value = barArray[54];
    tmri.llpz2.value = barArray[55];
    tmri.pzbh2.value = barArray[56];
    tmri.nszm.value = barArray[57];
    tmri.nszmbh.value = barArray[58];
    tmri.bxpzh.value = barArray[59];
    tmri.sxrq.value = barArray[60];
    tmri.zzrq.value = barArray[61];
    tmri.bxgs.value = barArray[62];
    tmri.jkpz.value = barArray[63];
    tmri.jkpzhm.value = barArray[64];
    tmri.qfrq.value = barArray[65];
    tmri.jkccpxh.value = barArray[66];
    tmri.jkccsys.value = barArray[67];
    tmri.veh_impawn_tempzhth.value = barArray[68];
    tmri.veh_impawn_tempdyhth.value = barArray[69];
    tmri.veh_impawn_tempsfzmmc.value = barArray[70];
    tmri.veh_impawn_tempsfzmhm.value = barArray[71];
    tmri.veh_impawn_tempdyqr.value = barArray[72];
    tmri.veh_impawn_templxdh.value = barArray[73];
    //tmri.veh_impawn_tempxzqh.value = barArray[74];
    tmri.bz.value = barArray[75];
    if(tmri.zzxzqh)
    {
      tmri.zzxzqh.value=barArray[98];
    }
    if(tmri.zzxxdz)
    {
      tmri.zzxxdz.value=barArray[99];
    }
    if(tmri.sjhm)
    {
      tmri.sjhm.value = barArray[100];
    }
    if(tmri.dzyx)
    {
      tmri.dzyx.value = barArray[101];
    }
    if(tmri.veh_impawn_tempzhth)
    {
      tmri.veh_impawn_tempzhth.value=barArray[68];
    }
    if(tmri.veh_impawn_tempdyhth)
    {
      tmri.veh_impawn_tempdyhth.value=barArray[69];
    }
    if(tmri.veh_impawn_tempsfzmmc)
    {
      tmri.veh_impawn_tempsfzmmc.value=barArray[70];
    }
    if(tmri.veh_impawn_tempsfzmhm)
    {
      tmri.veh_impawn_tempsfzmhm.value=barArray[71];
    }
    if(tmri.veh_impawn_templxdh)
    {
      tmri.veh_impawn_templxdh.value=barArray[73];
    }
    if(tmri.veh_impawn_tempdyqr)
    {
      tmri.veh_impawn_tempdyqr.value=barArray[72];
    }
    if(tmri.veh_impawn_tempyzbm)
    {
      tmri.veh_impawn_tempyzbm.value=barArray[75];
    }
    if(tmri.veh_impawn_tempxxdz)
    {
      tmri.veh_impawn_tempxxdz.value=barArray[74];
    }

    if (tmri.jkpz.value !="" ){
      changeJkpzhm();
      //alert(tmri.jkpzhm.value);
      tmri.jkpzhm.value=barArray[64];
      if (tmri.jkpz.value=="1"){
        if(tmri.jkpzhm.value.indexOf("P") >= 0){
          tmri.jkpzhm_1.value="P";
          tmri.jkpzhm_2.value=tmri.jkpzhm.value.substr(1);
        }else{
          tmri.jkpzhm_1.value="ⅩⅨ";
          tmri.jkpzhm_2.value=tmri.jkpzhm.value.substr(2);
        }
      }
      if (tmri.jkpz.value=="2"){
        if(tmri.jkpzhm.value.indexOf("No.") >= 0){
          tmri.jkpzhm_1.value="No.";
          tmri.jkpzhm_2.value=tmri.jkpzhm.value.substr(3);
        }else{
          tmri.jkpzhm_1.value="ⅨⅦ";
          tmri.jkpzhm_2.value=tmri.jkpzhm.value.substr(2);
        }
      }
      if (tmri.jkpz.value=="3"){
        tmri.jkpzhm_1.value="";
        tmri.jkpzhm_2.value=tmri.jkpzhm.value;
      }
      if (tmri.qfrq.value !=""){
        tmri.qfrq.value=tmri.qfrq.value.substring(0,10);
      }


    }




    //设置文本不可写
    tmri.fdjh.readOnly=true;
    tmri.clsbdh.readOnly=true;
    tmri.hgzbh.readOnly=true;
    tmri.zzcmc.readOnly=true;
    tmri.ccrq.readOnly=true;
    tmri.tempclpp1.readOnly=true;
    tmri.tempclxh.readOnly=true;
    tmri.clsbdh.readOnly=true;
    tmri.fdjh.readOnly=true;
    tmri.fdjxh.readOnly=true;
    tmri.rlzl1.readOnly=true;
    tmri.rlzl2.readOnly=true;
    tmri.hbdbqk.readOnly=true;
    tmri.pl.readOnly=true;
    tmri.gl.readOnly=true;
    tmri.zxxs.readOnly=true;
    tmri.qlj.readOnly=true;
    tmri.hlj.readOnly=true;
    tmri.lts.readOnly=true;
    tmri.ltgg.readOnly=true;
    //tmri.gbthps.readOnly=true;
    tmri.zj.readOnly=true;
    tmri.zs.readOnly=true;
    tmri.cwkc.readOnly=true;
    tmri.cwkk.readOnly=true;
    tmri.cwkg.readOnly=true;
    tmri.hxnbcd.readOnly=true;
    tmri.hxnbkd.readOnly=true;
    tmri.hxnbgd.readOnly=true;
    tmri.zzl.readOnly=true;
    tmri.hdzzl.readOnly=true;
    tmri.zbzl.readOnly=true;
    tmri.zqyzl.readOnly=true;
    tmri.hdzk.readOnly=true;
    tmri.qpzk.readOnly=true;
    tmri.hpzk.readOnly=true;
    paramForm.isSetClsbdh.value="0";
    paramForm.isHgzh.value=1;
  }
  //      showParameter('clxh',0);
}

function getINSPECTION(barArray){
  qrmarkform.hpzl.value=barArray[1];
  qrmarkform.hphm.value=barArray[2];
  qrmarkform.clsbdh.value=barArray[3];
  qrmarkform.jyjg.value=barArray[4];
  qrmarkform.djrq.value=barArray[5];
  qrmarkform.cjdw.value=barArray[6];
  qrmarkform.bxpzh.value=barArray[7];
  qrmarkform.sxrq.value=barArray[8];
  qrmarkform.bxzzrq.value=barArray[9];
  qrmarkform.bxgs.value=barArray[10];

  tmri.djrq.value=barArray[5];
  tmri.cjdw.value=barArray[6];
  tmri.bxpzh.value=barArray[7];
  tmri.sxrq.value=barArray[8];
  tmri.bxzzrq.value=barArray[9];
  tmri.bxgs.value=barArray[10];
  tmri.djrq.readOnly=true;
  tmri.cjdw.readOnly=true;
  qrmarkform.submit();

  //  tmri.bxpzh.readOnly=true;
  //  tmri.sxrq.readOnly=true;
  //  tmri.bxzzrq.readOnly=true;
  //  tmri.bxgs.readOnly=true;


}


//读4s档案移交条码
/*
function readArchBarCode(){
	  
	  var barcodetemp ="";
	  var strbarcode=vehPrinter.GetQrText();

	  if (strbarcode==""){
	    return 0;
	  }
	  if (strbarcode=="-1") {
	    alert("条码信息有误!");
	    return 0;
	  }
	  parseArchBarCode(strbarcode);
}

//初步解析4s移交条码
function parseArchBarCode(strbarcode){

	var pos=strbarcode.substr(16,1);
	//alert(pos);
	//alert(strbarcode);
	if(pos=='1'){
		//条码信息1 OK
		formArch.code1xx.value="已扫描";
		formArch.code1.value=strbarcode;
		
	}
	if(pos=='2'){
		//条码信息2 OK
		formArch.code2xx.value="已扫描";
		formArch.code2.value=strbarcode;
		
	}
	//符合条件，提交
	if(formArch.code1.value!="" && formArch.code2.value!=""){
		formArch.submit();
	}
	
}
*/