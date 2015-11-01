/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#formList\\:tblList\\:btnCreate").click(function() {
        return  doBeforeCreateMaster(this);
    });
    $("#formList\\:tblList\\:0\\:btnDelete").click(function() {
        return doBeforeDeleteMaster(this);
    });
});

function doBeforeConfirm() {
    if (confirm('Are you sure?')) {
        return true;
    } else {
        return false;
    }
}

function doBeforeCreateMaster(Obj) {
    if (confirm('Are you sure?')) {
        return true;
    } else {
        return false;
    }
}


function doBeforeDeleteMaster() {
    if (confirm('Are you sure?')) {
        alert('T');
        return true;
    } else {
        return false;
    }
}



