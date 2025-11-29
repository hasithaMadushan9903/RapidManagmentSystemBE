window.addEventListener('load', () =>{

    // making the content of first button is visible and others not visible for the very first time
    document.getElementById("studentDetails").style.display="inline";
    document.getElementById("studentLoginDetails").style.display="none";
    document.getElementById("parentsDetails").style.display="none";
    document.getElementById("studentIdCard").style.display="none";

    //taking the today date
    today = new Date();
    
    maxLastYearToday = new Date(today)
    maxLastYearToday.setFullYear(today.getFullYear()-5);
    maxdate = maxLastYearToday.toISOString().split("T")[0];
    document.getElementById("birthDay").max=maxdate;
    
})

function inputFeildValidationAndCallingNameBinding(feild,pattern){
    isvalidated=inputFeildValidation(feild,pattern);

    if(isvalidated=="true"){
        fullName = document.getElementById(feild.id).value;
        callingNames = fullName.split(" ");

        document.getElementById("callingNameOptions").innerHTML = '';
        callingNames.forEach(element => {
            let option = document.createElement("option");
            option.value=element;
            option.innerHTML=element;
            callingNameOptions.appendChild(option);
        });
        
    }
}