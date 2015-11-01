/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {
    alert("123");
});


function handleComplete(xhr, status, args) {
    //alert(status);
    document.getElementById(args.update + ":formQuery:deptid").value = args.entity.id.toString();
    document.getElementById(args.update + ":formQuery:dept").value = args.entity.dept;
}