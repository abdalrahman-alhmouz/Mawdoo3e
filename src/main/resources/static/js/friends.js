'use strict'
console.log("hellothere");
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
