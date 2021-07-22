'use strict'
console.log("hellothere");

function doSomething() {
  var x = document.getElementById("txtSearch").value;
  document.getElementById("dis").innerHTML = x;
};
function searchKeyPress(e)
{
    e = e || window.event;
    if (e.keyCode == 13)
    {
        document.getElementById('btnSearch').click();
        return false;
    }
    return true;
}

var countt =0

var oppsit="Dislike";
function changeState(id) {
const buttonOne=document.getElementById('button')
const buttonTow=document.getElementById(id)
const nam=buttonTow.className
const spann=document.querySelector('.'+nam)
    if(oppsit.localeCompare("Like")==0){
        spann.textContent="Dislike";
        oppsit="DisLike";
    }else{
        spann.textContent="like";
        oppsit="Like";

    }
}
