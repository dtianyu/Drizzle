/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//itemMasterList JavaScript Begin

//
function setItemProperty(formid){
    untitype=document.getElementById(formid+':unittype_input');
    //alert(untitype.value);
    if (untitype.value==='2'){
        document.getElementById(formid+':unit2').disabled=false;
        document.getElementById(formid+':unit2').className='ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all';
        document.getElementById(formid+':unitexch').disabled=false;
        document.getElementById(formid+':unitexch').className='ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all';
    }
    else{
        document.getElementById(formid+':unit2').value='';    
        document.getElementById(formid+':unit2').disabled=true;
        document.getElementById(formid+':unit2').className='ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all ui-state-disabled';
        document.getElementById(formid+':unitexch').value='';
        document.getElementById(formid+':unitexch').disabled=true;
        document.getElementById(formid+':unitexch').className='ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all ui-state-disabled';
        document.getElementById(formid+':unitsales').value='';   
        document.getElementById(formid+':unitpurchase').value='';
        document.getElementById(formid+':unitcode').value='00';
    }
    document.getElementById(formid+':unitcode').disabled=true;
}

//设置属性为不可维护
function setItemPropertyReadOnly(formid){
    document.getElementById(formid+':unitcode').disabled=true;
    untitype=document.getElementById(formid+':unittype_input');
    if (untitype.value==='1'){
        document.getElementById(formid+':unit2').disabled=true;
        document.getElementById(formid+':unit2').className='ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all ui-state-disabled';
        document.getElementById(formid+':unitexch').disabled=true;
        document.getElementById(formid+':unitexch').className='ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all ui-state-disabled';
    }
}

//设置属性为可已更新
function setItemPropertyUpdatable(formid){
    document.getElementById(formid+':unit2').disabled=false;
    document.getElementById(formid+':unitexch').disabled=false;
    document.getElementById(formid+':unitcode').disabled=false;
}

//根据输入的销售单位和采购单位,设置单位管理码
function setItemUnitCode(formid){
    unit=document.getElementById(formid+':unit');
    unit2=document.getElementById(formid+':unit2');
    unitsales=document.getElementById(formid+':unitsales');
    unitpurchase=document.getElementById(formid+':unitpurchase');
    unitcode= document.getElementById(formid+':unitcode');
    unitcode.disabled=false;
    if (document.getElementById(formid+':unittype_input').value==='1'){
        if (unitsales.value.toString().length>0 &&　unitsales.value===unit.value){
            unitcode.value="1" + unitcode.value.substr(1, 1);
        }else if(unitsales.value.toString().length>0 &&　unitsales.value!==unit.value){
            alert("单单位时,销售单位应与基本单位相同！");
            unitsales.value='';
        }
        if (unitpurchase.value.toString().length>0 &&　unitpurchase.value===unit.value){
            unitcode.value= unitcode.value.substr(0, 1)+'1';
        }else if(unitpurchase.value.toString().length>0 &&　unitpurchase.value!==unit.value){
            alert("单单位时,采购单位应与基本单位相同！");
            unitpurchase.value='';
        }
    }else if(document.getElementById(formid+':unittype_input').value==='2') {
        if (unit2.value.toString().length===0 || document.getElementById(formid+':unitexch').value.toString().length===0){
            alert("请先设置第二单位和单位换算率！");
            unitsales.value='';
            unitpurchase.value='';
        }
        if(unitsales.value.toString().length>0 && unitsales.value!==unit.value &&　unitsales.value!==nit2.value){
            alert("双单位时,销售单位应是基本单位或第二单位之一！");
            unitsales.value='';
        } else if (unitsales.value.toString().length>0 && unitsales.value===unit.value){
            unitcode.value="1" + unitcode.value.substr(1, 1);
        }else if(unitsales.value.toString().length>0 && unitsales.value===unit2.value){
            unitcode.value="2" + unitcode.value.substr(1, 1);
        } 
        if(unitpurchase.value.toString().length>0 &&　unitpurchase.value!==unit.value &&　unitpurchase.value!==unit2.value){
            alert("双单位时,采购单位应是基本单位或第二单位之一！");
            unitpurchase.value='';
        } else if (unitpurchase.value.toString().length>0 && unitpurchase.value===unit.value){
            unitcode.value= unitcode.value.substr(0, 1)+'1';
        }else if(unitpurchase.value.toString().length>0 && unitpurchase.value===unit2.value){
            unitcode.value= unitcode.value.substr(0, 1)+'2';
        } 
    }
    unitcode.disabled=true;
}

//itemMasterList JavaScript End


