/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//function handleComplete(xhr, status, args){
//   var dept =args.dept;
//   alert(dept);
//}

function doBeforeDeleteMaster() {
    if (confirm('Are you sure?')) {
        alert('T');
        return true;
    } else {
        alert('F');
        return false;
    }
}


function doWhenFormViewShow(detailCount, serviceCount) {
    //alert(count);
    if (detailCount < 1) {
        document.getElementById("formView:btnVerify").disabled = true;
        document.getElementById("formView:btnVerify").className = "ui-button ui-widget ui-state-disabled ui-corner-all ui-button-text-icon-left ";
    }
    if (serviceCount > 0) {
        document.getElementById("formView:btnUnverify").disabled = true;
        document.getElementById("formView:btnUnverify").className = "ui-button ui-widget ui-state-disabled ui-corner-all ui-button-text-icon-left ";
    }
}

