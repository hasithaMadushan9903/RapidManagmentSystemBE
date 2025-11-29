function inputFeildValidation(feild , pattern){
    const value = feild.value;
    const regPattern = new RegExp(pattern);
    if(regPattern.test(value)){
        document.getElementById(feild.id).style.border = '2px solid green';
        return "true";
    }else{
        document.getElementById(feild.id).style.border = '2px solid red';
        return "false";
    }
}

function calllingNameValidation(feild){
    callingName = feild.value;
    
    const extIndex = callingNames.map((element) => element).indexOf(callingName);
    if(extIndex!=-1){
        document.getElementById(feild.id).style.border = '2px solid green';
    }else{
        document.getElementById(feild.id).style.border = '2px solid red';
    }
}

function birtdayValidation(feild){
    if(feild.value){
        document.getElementById(feild.id).style.border = '2px solid green';
    }else{
        document.getElementById(feild.id).style.border = '2px solid red';
    }
}